package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.paypal.base.rest.PayPalRESTException;
import infrastructure.CajasturPayment;
import infrastructure.Factories;
import infrastructure.PaypalPayment;
import models.Subscription;
import models.User;
import org.apache.commons.codec.digest.DigestUtils;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.account;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Dani on 14/3/15.
 */
public class AccountController extends Controller {

    private static String generateProductName(int months) {
        Calendar today = Calendar.getInstance();
        int year = today.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH) + 1;
        int day = today.get(Calendar.DAY_OF_MONTH);
        int hour = today.get(Calendar.HOUR);
        int minute = today.get(Calendar.MINUTE);
        int second = today.get(Calendar.SECOND);
        String todayStr = "" + year + month + day + hour + minute + second;
        return "Movify" + months + "m" + todayStr;
    }

    public static Result showAccount() {
        List<Subscription> subscriptions = Factories.businessFactory.getSubscriptionService().getAllOrderByMonths();
        String userName = session(Application.USERNAME_KEY);
        User user = Factories.businessFactory.getUserService().get(userName);
        long expiration = user.getExpiration();
        Calendar expirationCalendar = Calendar.getInstance();
        expirationCalendar.setTimeInMillis(expiration);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        String expirationStr = dateFormat.format(expirationCalendar.getTime());
        boolean expired = System.currentTimeMillis() > expiration;

        return ok(account.render(subscriptions, userName, expired, expirationStr));
    }

    public static Result generatePaypalPayment() {
        int months = Integer.parseInt(request().getQueryString("months"));
        Subscription subscription = Factories.businessFactory.getSubscriptionService().getByMonths(months);
        if (subscription == null)
            return badRequest();
        double price = subscription.getPrice();
        int quantity = 1;
        String name = "Movify " + subscription.getName() + " subscription";
        String description = subscription.getDescription();
        String sku = generateProductName(months);
        String returnUrl = "http://156.35.95.67/movifyj/account/subscription/paypal";
        String cancelUrl = "http://156.35.95.67/movifyj/account";
        try {
            PaypalPayment paypalPayment = new PaypalPayment(price, quantity, name, description, sku, returnUrl, cancelUrl);
            ObjectNode result = Json.newObject();
            result.put("url", paypalPayment.getApprovalURL());
            session(Application.MONTHS_KEY, String.valueOf(months));
            String idHash = DigestUtils.md5Hex(paypalPayment.getPaymentId());
            session(Application.PAYPAL_ID_KEY, idHash);
            return ok(result);
        } catch (PayPalRESTException e) {
            return badRequest();
        }
    }

    public static Result processPaypalPayment() {
        String paymentId = request().getQueryString("paymentId");
        String paymentIdHash = session(Application.PAYPAL_ID_KEY);
        int months = Integer.parseInt(session(Application.MONTHS_KEY));
        session().remove(Application.PAYPAL_ID_KEY);
        session().remove(Application.MONTHS_KEY);
        String username = session(Application.USERNAME_KEY);
        if (paymentIdHash != null && paymentIdHash.equals(DigestUtils.md5Hex(paymentId))) {
            Factories.businessFactory.getUserService().increaseExpiration(username, months);
        }
        return redirect(routes.AccountController.showAccount());
    }

    public static Result generateCajasturPayment() {
        int months = Integer.parseInt(request().getQueryString("months"));
        Subscription subscription = Factories.businessFactory.getSubscriptionService().getByMonths(months);
        if (subscription == null)
            return badRequest();
        String operation = generateProductName(months);
        double price = subscription.getPrice();
        String description = "Movify " + subscription.getName() + " subscription";
        String returnUrl = "http://156.35.95.67/movifyj/account/subscription/cajastur";
        String cancelUrl = "http://156.35.95.67/movifyj/account";
        CajasturPayment cajasturPayment = new CajasturPayment(operation, price, description, returnUrl, cancelUrl);
        session(Application.MONTHS_KEY, String.valueOf(months));
        session(Application.CAJASTUR_ID_KEY, DigestUtils.md5Hex(cajasturPayment.getSignature()));
        ObjectNode result = Json.newObject();
        result.put("operation", cajasturPayment.getOperation());
        result.put("price", cajasturPayment.getPrice());
        result.put("description", cajasturPayment.getDescription());
        ObjectNode commerceData = Json.newObject();
        commerceData.put("MERCHANT_ID", CajasturPayment.MERCHANT_ID);
        commerceData.put("ACQUIRER_BIN", CajasturPayment.ACQUIRER_BIN);
        commerceData.put("TERMINAL_ID", CajasturPayment.TERMINAL_ID);
        commerceData.put("CLAVE_ENCRIPTACION", CajasturPayment.CLAVE_ENCRIPTACION);
        commerceData.put("TIPO_MONEDA", CajasturPayment.TIPO_MONEDA);
        commerceData.put("EXPONENTE", CajasturPayment.EXPONENTE);
        result.put("commerce_data", commerceData);
        result.put("url_ok", cajasturPayment.getUrlOk());
        result.put("url_error", cajasturPayment.getUrlError());
        result.put("signature", cajasturPayment.getSignature());
        return ok(result);
    }

    public static Result processCajasturPayment() {
        String cajasturId = session(Application.CAJASTUR_ID_KEY);
        int months = Integer.parseInt(session(Application.MONTHS_KEY));
        session().remove(Application.CAJASTUR_ID_KEY);
        session().remove(Application.MONTHS_KEY);
        String username = session(Application.USERNAME_KEY);
        if (cajasturId != null)
            Factories.businessFactory.getUserService().increaseExpiration(username, months);
        return redirect(routes.AccountController.showAccount());
    }
}
