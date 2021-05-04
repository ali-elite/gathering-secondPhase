package ir.sharif.ap2021.Event;

import java.util.EventObject;

public class PrivacyEvent extends EventObject {

    private String order;
    private String password;
    private String lastSeen;
    private boolean diactive;

    public PrivacyEvent(Object source, String order, String password, String lastSeen, boolean diactive) {
        super(source);
        this.order = order;
        this.password = password;
        this.lastSeen = lastSeen;
        this.diactive = diactive;
    }

    public String getOrder() {
        return order;
    }

    public String getPassword() {
        return password;
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public boolean isDiactive() {
        return diactive;
    }
}
