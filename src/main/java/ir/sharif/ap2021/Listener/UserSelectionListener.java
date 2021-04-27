package ir.sharif.ap2021.Listener;

import ir.sharif.ap2021.Controller.UserSelectionController;
import ir.sharif.ap2021.Event.UserSelectionEvent;

import java.io.IOException;

public class UserSelectionListener {

    UserSelectionController userSelectionController = new UserSelectionController();

    public void eventOccurred(UserSelectionEvent event) throws IOException {
        userSelectionController.set(event);
    }

}
