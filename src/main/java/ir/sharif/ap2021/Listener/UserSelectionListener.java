package ir.sharif.ap2021.Listener;

import ir.sharif.ap2021.Controller.UserSelectionController;
import ir.sharif.ap2021.Event.UserSelectionEvent;
import ir.sharif.ap2021.Validation.AuthenticationException;

import java.io.IOException;

public class UserSelectionListener {

    UserSelectionController userSelectionController = new UserSelectionController();

    public UserSelectionListener() throws IOException {
    }

    public void eventOccurred(UserSelectionEvent event) throws IOException, AuthenticationException {
        userSelectionController.set(event);
    }

}
