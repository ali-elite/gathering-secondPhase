package ir.sharif.ap2021.Event;

import ir.sharif.ap2021.Model.Notification.Notification;

import java.util.EventObject;

public class NotifEvent extends EventObject {

    String oreder;
    Notification notification;

    public NotifEvent(Object source, String oreder, Notification notification) {
        super(source);
        this.oreder = oreder;
        this.notification = notification;
    }

    public String getOreder() {
        return oreder;
    }

    public Notification getNotification() {
        return notification;
    }
}
