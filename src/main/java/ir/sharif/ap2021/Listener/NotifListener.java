package ir.sharif.ap2021.Listener;

import ir.sharif.ap2021.Controller.NotifController;
import ir.sharif.ap2021.Event.NotifEvent;

import java.io.IOException;

public class NotifListener {

    private NotifController notifController = new NotifController();

    public void eventOccurred(NotifEvent event) throws IOException {

        if (event.getOreder().equals("load")) {
            notifController.load();
        }

        if(event.getOreder().equals("accept")){
            notifController.accept(event.getNotification());
        }

        if(event.getOreder().equals("reject")){
            notifController.reject(event.getNotification());
        }

        if(event.getOreder().equals("remove")){
            notifController.remove(event.getNotification());
        }





    }

}

