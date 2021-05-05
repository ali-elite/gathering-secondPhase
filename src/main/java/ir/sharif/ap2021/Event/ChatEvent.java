package ir.sharif.ap2021.Event;

import ir.sharif.ap2021.Model.Chat.Chat;

import java.util.EventObject;

public class ChatEvent extends EventObject {

    private String order;
    private Chat chat;
    private String pm;
    private String changed;

    public ChatEvent(Object source, String order, Chat chat, String pm) {
        super(source);
        this.order = order;
        this.chat = chat;
        this.pm = pm;
    }

    public String getChanged() {
        return changed;
    }

    public void setChanged(String changed) {
        this.changed = changed;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }
}
