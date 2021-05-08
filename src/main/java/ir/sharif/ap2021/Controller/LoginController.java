package ir.sharif.ap2021.Controller;

import ir.sharif.ap2021.Config.ErrorConfig;
import ir.sharif.ap2021.DB.Context;
import ir.sharif.ap2021.Event.LoginFormEvent;
import ir.sharif.ap2021.Model.User.User;
import ir.sharif.ap2021.Validation.AuthenticationException;

import java.io.IOException;

public class LoginController {

ErrorConfig errorConfig = new ErrorConfig();
    private Context context;

    public LoginController() throws IOException {

        context = new Context();
    }

    public void login(LoginFormEvent e) throws AuthenticationException {

        if (!exist(e.getUsername())) {
            throw new AuthenticationException(errorConfig.getNoUserExists());
        }

        if (!match(e.getUsername(), e.getPassword())) {
            throw new AuthenticationException(errorConfig.getIncorrectPass());
        }

    }

    public boolean match(String username, String password) {

        String get = "";
        for (User user :
                context.Users.all()) {
            if (user.getUserName().equals(username)) {
                get = user.getPassword();
            }
        }

        return get.equals(password);
    }

    public boolean exist(String username) {

        boolean check = false;

        for (User user : context.Users.all()) {
            if (user.getUserName().equals(username) && !user.isDeleted()) {
                check = true;
                break;
            }
        }

        return check;

    }
}
