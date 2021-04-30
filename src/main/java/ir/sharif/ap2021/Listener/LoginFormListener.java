package ir.sharif.ap2021.Listener;

import ir.sharif.ap2021.Controller.LoginController;
import ir.sharif.ap2021.Event.LoginFormEvent;
import ir.sharif.ap2021.Event.RegistrationFormEvent;
import ir.sharif.ap2021.Validation.AuthenticationException;

public class LoginFormListener implements FormListener {

    LoginController loginController = new LoginController();

    @Override
    public void eventOccurred(RegistrationFormEvent formEvent) throws AuthenticationException {

    }

    @Override
    public void eventOccurred(LoginFormEvent formEvent) throws AuthenticationException {
        loginController.login(formEvent);
    }

}
