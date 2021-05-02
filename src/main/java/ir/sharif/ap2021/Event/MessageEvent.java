package ir.sharif.ap2021.Event;


import ir.sharif.ap2021.Model.Chat.Message;

import java.util.EventObject;

public class MessageEvent extends EventObject {

    String order;
    Message message;

    public MessageEvent(Object source, String order, Message message) {
        super(source);
        this.order = order;
        this.message = message;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
