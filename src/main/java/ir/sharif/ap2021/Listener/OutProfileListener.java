package ir.sharif.ap2021.Listener;

import ir.sharif.ap2021.Controller.OutProfileController;
import ir.sharif.ap2021.Event.OutProfileEvent;
import ir.sharif.ap2021.Validation.RepeatActionException;


import java.io.IOException;

public class OutProfileListener {

    OutProfileController outProfileController = new OutProfileController();

    public OutProfileListener() throws IOException {
    }

    public void eventOccurred(OutProfileEvent event) throws IOException, RepeatActionException {
        outProfileController.control(event);
    }


}
