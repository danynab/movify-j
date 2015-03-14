package infrastructure;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by Dani on 14/3/15.
 */
public class CajasturPayment {

    public static final String MERCHANT_ID = "082108630";
    public static final String ACQUIRER_BIN = "0000554002";
    public static final String TERMINAL_ID = "00000003";
    public static final String CLAVE_ENCRIPTACION = "87401456";
    public static final String TIPO_MONEDA = "978";
    public static final String EXPONENTE = "2";

    private String operation;
    private int price;
    private String description;
    private String urlOk;
    private String urlError;
    private String signature;

    public CajasturPayment(String operation, Double price, String description, String urlOk, String urlError) {
        this.operation = operation;
        this.price = new Double(price * 100).intValue();
        this.description = description;
        this.urlOk = urlOk;
        this.urlError = urlError;
        this.signature = computeSignature();
    }

    private String computeSignature() {
        String message = CLAVE_ENCRIPTACION
                + MERCHANT_ID
                + ACQUIRER_BIN
                + TERMINAL_ID
                + operation
                + price
                + TIPO_MONEDA
                + EXPONENTE
                + ""
                + "SHA1"
                + urlOk
                + urlError;

        String sha1 = DigestUtils.sha1Hex(message);
        return sha1;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlOk() {
        return urlOk;
    }

    public void setUrlOk(String urlOk) {
        this.urlOk = urlOk;
    }

    public String getUrlError() {
        return urlError;
    }

    public void setUrlError(String urlError) {
        this.urlError = urlError;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}