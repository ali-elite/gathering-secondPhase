package ir.sharif.ap2021.Listener;

import ir.sharif.ap2021.Event.LoginFormEvent;
import ir.sharif.ap2021.Event.RegistrationFormEvent;
import ir.sharif.ap2021.Validation.AuthenticationException;

import java.io.IOException;

public interface FormListener {

    void eventOccurred(RegistrationFormEvent formEvent) throws AuthenticationException, IOException;
    void eventOccurred(LoginFormEvent formEvent) throws AuthenticationException;

}