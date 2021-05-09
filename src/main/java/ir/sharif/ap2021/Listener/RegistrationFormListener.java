package ir.sharif.ap2021.Listener;

import ir.sharif.ap2021.Controller.RegistrationController;
import ir.sharif.ap2021.Event.LoginFormEvent;
import ir.sharif.ap2021.Event.RegistrationFormEvent;
import ir.sharif.ap2021.Validation.AuthenticationException;

import java.io.IOException;

public class RegistrationFormListener implements FormListener {

    RegistrationController registrationController = new RegistrationController();

    public RegistrationFormListener() throws IOException {
    }

    @Override
    public void eventOccurred(RegistrationFormEvent formEvent) throws AuthenticationException, IOException {
        registrationController.register(formEvent);
    }

    @Override
    public void eventOccurred(LoginFormEvent formEvent) throws AuthenticationException {

    }
}
