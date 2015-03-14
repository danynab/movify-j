package filters;

import controllers.Application;
import infrastructure.Factories;
import models.User;
import play.libs.F;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

/**
 * Created by Dani on 14/3/15.
 */
public class LoginRequiredAction extends Action.Simple {

    @Override
    public F.Promise<Result> call(Http.Context context) throws Throwable {
        Http.Session session = context.session();
        if (session.containsKey(Application.SESSION_ID_KEY) && session.containsKey(Application.USERNAME_KEY)) {
            String sessionId = session.get(Application.SESSION_ID_KEY);
            String username = session.get(Application.USERNAME_KEY);
            String sessionCalculated = Application.calculateSessionId(context.request());
            if (sessionId.equals(sessionCalculated)) {
                User user = Factories.businessFactory.getUserService().get(username);
                if (user != null)
                    return delegate.call(context);
            }
        }
        return F.Promise.promise(new F.Function0<Result>() {
            @Override
            public Result apply() throws Throwable {
                return redirect(controllers.routes.UserController.showLogin());
            }
        });
    }
}
