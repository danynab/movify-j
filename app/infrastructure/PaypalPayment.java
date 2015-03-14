package infrastructure;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dani on 14/3/15.
 */
public class PaypalPayment {

    private String paymentId;
    private String approvalURL;

    public PaypalPayment(double price, int quantity, String name, String description, String sku, String returnUrl, String cancelUrl) throws PayPalRESTException {
        Map<String, String> sdkConfig = new HashMap<>();
        sdkConfig.put("mode", "sandbox");

        String accessToken = new OAuthTokenCredential(
                "AQkquBDf1zctJOWGKWUEtKXm6qVhueUEMvXO_-MCI4DQQ4-LWvkDLIN2fGsd",
                "EL1tVxAjhT7cJimnz5-Nsx9k2reTKSVfErNQF-CmrwJgxRtylkGTKlU4RvrX", sdkConfig).getAccessToken();
        APIContext apiContext = new APIContext(accessToken);
        apiContext.setConfigurationMap(sdkConfig);

        Amount amount = new Amount();
        amount.setCurrency("EUR");
        amount.setTotal(String.valueOf(price));

        Item item = new Item();
        item.setQuantity(String.valueOf(quantity));
        item.setName(name);
        item.setPrice(String.valueOf(price));
        item.setSku(sku);
        item.setCurrency("EUR");
        List<Item> items = new ArrayList<>();
        items.add(item);

        ItemList itemList = new ItemList();
        itemList.setItems(items);

        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amount);
        transaction.setItemList(itemList);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(returnUrl);
        payment.setRedirectUrls(redirectUrls);

        Payment createdPayment = payment.create(apiContext);
        paymentId = createdPayment.getId();

        for (Links link : createdPayment.getLinks()) {
            if (link.getRel().equals("approval_url")) {
                approvalURL = link.getHref();
                return;
            }
        }
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getApprovalURL() {
        return approvalURL;
    }

    public void setApprovalURL(String approvalURL) {
        this.approvalURL = approvalURL;
    }

    @Override
    public String toString() {
        return "PaypalPayment{" +
                "paymentId='" + paymentId + '\'' +
                ", approvalURL='" + approvalURL + '\'' +
                '}';
    }
}
