package ir.sharif.ap2021.Event;

import java.util.EventObject;

public class NotifEvent extends EventObject {

    String order;

    public NotifEvent(Object source, String order) {
        super(source);
        this.order = order;
    }

    public String getOrder() {
        return order;
    }

}
