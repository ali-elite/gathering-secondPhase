package ir.sharif.ap2021.Listener;

import ir.sharif.ap2021.Controller.EditProfileController;
import ir.sharif.ap2021.Event.EditProfileEvent;
import ir.sharif.ap2021.Validation.AuthenticationException;


import java.io.IOException;

public class EditProfileListener {


    EditProfileController editProfileController = new EditProfileController();


    public void eventOccurred(EditProfileEvent event) throws IOException, AuthenticationException {
        editProfileController.edit(event);
    }


}






