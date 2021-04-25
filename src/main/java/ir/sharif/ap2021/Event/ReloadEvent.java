package ir.sharif.ap2021.Event;

import ir.sharif.ap2021.Model.User.User;
import ir.sharif.ap2021.View.ThoughtView;

import java.util.EventObject;

public class ReloadEvent extends EventObject {


    String type;
    User user;
    ThoughtView thoughtView;

    public ReloadEvent(Object source, User user) {

        super(source);
        this.user = user;
        type = "user";

    }

    public ReloadEvent(Object source, ThoughtView thoughtView) {

        super(source);
        this.thoughtView = thoughtView;
        type = "thoughtView";

    }

    public String getType() {
        return type;
    }

    public User getUser() {
        return user;
    }

    public ThoughtView getThoughtView() {
        return thoughtView;
    }
}
