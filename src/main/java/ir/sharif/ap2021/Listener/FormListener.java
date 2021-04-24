package ir.sharif.ap2021.Listener;

import ir.sharif.ap2021.Event.LoginFormEvent;
import ir.sharif.ap2021.Event.RegistrationFormEvent;
import ir.sharif.ap2021.Validation.AuthenticationException;

public interface FormListener {

    void eventOccurred(RegistrationFormEvent formEvent) throws AuthenticationException;
    void eventOccurred(LoginFormEvent formEvent) throws AuthenticationException;

}