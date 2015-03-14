import play.GlobalSettings;
import play.libs.F;
import play.mvc.Http;
import play.mvc.Result;

import static play.mvc.Results.*;


/**
 * Created by Dani on 14/3/15.
 */
public class Global extends GlobalSettings {

    @Override
    public F.Promise<Result> onError(Http.RequestHeader requestHeader, Throwable throwable) {
        return F.Promise.<Result>pure(internalServerError(
                views.html.error.render()
        ));
    }

    @Override
    public F.Promise<Result> onHandlerNotFound(Http.RequestHeader requestHeader) {
        return F.Promise.<Result>pure(notFound(
                views.html.notfound.render()
        ));
    }

    @Override
    public F.Promise<Result> onBadRequest(Http.RequestHeader requestHeader, String s) {
        return F.Promise.<Result>pure(badRequest(
                views.html.notfound.render()
        ));
    }
}