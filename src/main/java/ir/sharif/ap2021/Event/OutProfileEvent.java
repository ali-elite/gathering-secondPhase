package ir.sharif.ap2021.Event;

import ir.sharif.ap2021.Model.User.User;

import java.util.EventObject;

public class OutProfileEvent extends EventObject {

    User user;
    String order;


    public OutProfileEvent(Object source, User user, String order) {
        super(source);
        this.user = user;
        this.order = order;
    }

    public User getUser() {
        return user;
    }

    public String getOrder() {
        return order;
    }


}
