package ir.sharif.ap2021.Event;

import java.util.EventObject;

public class ShareThoughtEvent extends EventObject {

    private String text;
    private int userId;
    private String change;

    public ShareThoughtEvent(Object source, String text, int userId) {
        super(source);
        this.userId = userId;
        this.text = text;
    }


    public String getText() {
        return text;
    }

    public int getUserId() {
        return userId;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }
}
