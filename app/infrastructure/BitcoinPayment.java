package infrastructure;

import com.fasterxml.jackson.databind.JsonNode;
import play.Logger;
import play.libs.Json;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Dani on 14/3/15.
 */
public class BitcoinPayment {

    private String paymentId;
    private String url;

    public BitcoinPayment(double price, String orderNumber, String url) {
        Client client = ClientBuilder.newClient();
        Entity payload = Entity.json("" +
                "{ \"settled_currency\": \"BTC\"," +
                "  \"return_url\": \"" + url + "\"," +
                "  \"price\": " + price + "," +
                "  \"currency\": \"EUR\"," +
                "  \"reference\": {" +
                "    \"customer_name\": \"Movify\"," +
                "    \"order_number\": \"" + orderNumber + "\"," +
                "    \"customer_email\": \"movify@movify.com\"  }}");

        Response response = client.target("https://private-anon-2b8feed1a-bitcoinpaycom.apiary-proxy.com/api/v1")
                .path("/payment/btc")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Token fmzGW06lF3hQAqPZyAY8dymz")
                .post(payload);

        String jsonResponse = response.readEntity(String.class);
        JsonNode body = Json.parse(jsonResponse);
        this.paymentId = body.get("data").get("payment_id").textValue();
        this.url = body.get("data").get("payment_url").textValue();
    }

    public static String checkPayment(String paymentId) {
        Client client = ClientBuilder.newClient();
        Response response = client.target("https://private-anon-2b8feed1a-bitcoinpaycom.apiary-proxy.com/api/v1")
                .path("/payment/btc/" + paymentId)
                .request(MediaType.TEXT_PLAIN_TYPE)
                .header("Authorization", "Token fmzGW06lF3hQAqPZyAY8dymz")
                .get();

        String jsonResponse = response.readEntity(String.class);
        JsonNode body = Json.parse(jsonResponse);
        String status = body.get("data").get("status").textValue();
        return status;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "BitcoinPayment{" +
                "paymentId='" + paymentId + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
