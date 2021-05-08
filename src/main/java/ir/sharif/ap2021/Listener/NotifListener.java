package ir.sharif.ap2021.Listener;

import ir.sharif.ap2021.Controller.NotifController;
import ir.sharif.ap2021.Event.NotifEvent;
import ir.sharif.ap2021.Model.Notification.Notification;

import java.io.IOException;

public class NotifListener {

     NotifController notifController = new NotifController();
     Notification notification;

    public NotifListener(Notification notification) throws IOException {
        this.notification = notification;
    }

    public void eventOccurred(NotifEvent event) throws IOException {

        if (event.getOrder().equals("load")) {
            notifController.load();
        }

        if(event.getOrder().equals("accept")){
            notifController.accept(notification);
        }

        if(event.getOrder().equals("reject")){
            notifController.reject(notification);
        }

        if(event.getOrder().equals("remove")){
            notifController.remove(notification);
        }



    }

}

