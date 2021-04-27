package ir.sharif.ap2021.Event;

import ir.sharif.ap2021.Model.Thought.Thought;
import ir.sharif.ap2021.View.ModelView.ThoughtView;

import java.util.EventObject;

public class ThoughtEvent extends EventObject {

    private String type;
    private Thought thought;
    private ThoughtView thoughtView;


    public ThoughtEvent(Object source, String type, Thought thought,ThoughtView thoughtView) {
        super(source);
        this.thought = thought;
        this.type = type;
        this.thoughtView = thoughtView;
    }

    public String getType() {
        return type;
    }

    public Thought getThought() {
        return thought;
    }

    public ThoughtView getThoughtView() {
        return thoughtView;
    }
}
