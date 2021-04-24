package ir.sharif.ap2021.Event;

import java.util.EventObject;

public class UserPassEvent extends EventObject {

    String username;

    public UserPassEvent(Object source,String username) {
        super(source);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
