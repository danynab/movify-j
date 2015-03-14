package controllers;

import infrastructure.Data;
import org.apache.commons.codec.digest.DigestUtils;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {

    public static final String SESSION_ID_KEY = "session_id";
    public static final String USERNAME_KEY = "username";
    public static final String PAYPAL_ID_KEY = "paypal_id";
    public static final String CAJASTUR_ID_KEY = "cajastur_id";
    public static final String MONTHS_KEY = "months";

    public static String calculateSessionId(Http.Request request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null) ip = "127.0.0.1";
        String userAgent = request().getHeader("User-Agent");
        Logger.debug("IP" + ": " + ip + ", User-Agent: " + userAgent);
        String sessionIdStr = ip + userAgent;
        return DigestUtils.md5Hex(sessionIdStr);
    }

    public static Result index() {
        String userName = session(USERNAME_KEY);
        return ok(index.render(userName));
    }

    public static Result init() {
        Data.getInstance().init();
        return redirect(controllers.routes.Application.index());
    }

}
