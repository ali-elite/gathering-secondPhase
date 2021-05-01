package ir.sharif.ap2021.Event;

import ir.sharif.ap2021.Model.Thought.Thought;
import java.util.EventObject;

public class ThoughtEvent extends EventObject {

    private String type;
    private Thought thought;
    private String mentionText;



    public ThoughtEvent(Object source, String type, Thought thought) {
        super(source);
        this.thought = thought;
        this.type = type;

    }

    public String getType() {
        return type;
    }

    public Thought getThought() {
        return thought;
    }

    public String getMentionText() {
        return mentionText;
    }

    public void setMentionText(String mentionText) {
        this.mentionText = mentionText;
    }
}
