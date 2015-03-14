package controllers;

import filters.LoginRequiredAction;
import infrastructure.Factories;
import models.User;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import views.html.login;
import views.html.signup;

public class UserController extends Controller {

    public static Result showLogin() {
        return ok(login.render());
    }

    public static Result doLogin() {
        DynamicForm requestData = Form.form().bindFromRequest();
        String username = requestData.get("username");
        String password = requestData.get("password");
        User user = Factories.businessFactory.getUserService().login(username, password);
        if (user != null) {
            String sessionId = Application.calculateSessionId(request());
            session(Application.SESSION_ID_KEY, sessionId);
            session(Application.USERNAME_KEY, username);
            return redirect(controllers.routes.Application.index());
        } else
            return redirect(controllers.routes.UserController.showSignUp());
    }

    @With(LoginRequiredAction.class)
    public static Result doLogout() {
        session().remove(Application.SESSION_ID_KEY);
        session().remove(Application.USERNAME_KEY);
        return redirect(controllers.routes.Application.index());
    }

    public static Result showSignUp() {
        return ok(signup.render());
    }

    public static Result doSignUp() {
        DynamicForm requestData = Form.form().bindFromRequest();
        String username = requestData.get("username");
        String password = requestData.get("password");
        String email = requestData.get("email");
        String confirmEmail = requestData.get("confirm_email");
        if (username.isEmpty())
            return ok("Username can not be empty");
        if (password.isEmpty())
            return ok("Password can not be empty");
        if (email.isEmpty())
            return ok("Email can not be empty");
        if (email.equals(confirmEmail)) {
            User user = Factories.businessFactory.getUserService().signUp(username, password, email);
            if (user != null) {
                String sessionId = Application.calculateSessionId(request());
                session(Application.SESSION_ID_KEY, sessionId);
                session(Application.USERNAME_KEY, username);
                return redirect(routes.Application.index());
            }
            return ok("Username already registered");
        }
        return ok("Emails not equals");
    }

}
