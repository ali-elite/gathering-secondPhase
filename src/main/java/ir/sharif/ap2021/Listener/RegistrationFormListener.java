package ir.sharif.ap2021.Listener;

import ir.sharif.ap2021.Controller.RegistrationController;
import ir.sharif.ap2021.Event.LoginFormEvent;
import ir.sharif.ap2021.Event.RegistrationFormEvent;
import ir.sharif.ap2021.Validation.AuthenticationException;

public class RegistrationFormListener implements FormListener {

    private final RegistrationController registrationController = new RegistrationController();

    @Override
    public void eventOccurred(RegistrationFormEvent formEvent) throws AuthenticationException {
        registrationController.register(formEvent);
    }

    @Override
    public void eventOccurred(LoginFormEvent formEvent) throws AuthenticationException {

    }
}
