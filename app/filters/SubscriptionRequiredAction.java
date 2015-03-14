package filters;

import controllers.Application;
import infrastructure.Factories;
import models.User;
import play.libs.F;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.With;

/**
 * Created by Dani on 14/3/15.
 */
public class SubscriptionRequiredAction extends Action.Simple {

    @Override
    public F.Promise<Result> call(Http.Context context) throws Throwable {
        String username = context.session().get(Application.USERNAME_KEY);
        User user = Factories.businessFactory.getUserService().get(username);
        if (user.getExpiration() > System.currentTimeMillis())
            return delegate.call(context);
        else
            return F.Promise.promise(new F.Function0<Result>() {
                @Override
                public Result apply() throws Throwable {
                    return redirect(controllers.routes.AccountController.showAccount());
                }
            });

    }
}