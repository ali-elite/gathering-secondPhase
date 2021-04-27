package ir.sharif.ap2021.Event;

import java.util.EventObject;

public class UserSelectionEvent extends EventObject {

    private String order;
    private String type;
    private String username;

    public UserSelectionEvent(Object source, String order, String username, String type) {
        super(source);
        this.order = order;
        this.username = username;
        this.type = type;
    }

    public String getOrder() {
        return order;
    }

    public String getUsername() {
        return username;
    }

    public String getType() {
        return type;
    }
}
