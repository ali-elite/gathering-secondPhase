package ir.sharif.ap2021.Event;

import java.util.EventObject;

public class ShareThoughtEvent extends EventObject {

    private String text;
    private String username;

    public ShareThoughtEvent(Object source, String text, String username) {
        super(source);
        this.username = username;
        this.text = text;
    }


    public String getText() {
        return text;
    }

    public String getUsername() {
        return username;
    }
}
