package ir.sharif.ap2021.Event;

import ir.sharif.ap2021.Model.Thought.Thought;
import javafx.stage.Stage;

import java.util.EventObject;

public class ThoughtChangeEvent extends EventObject {

    private String type;
    private Thought thought;
    private Stage stage;

    public ThoughtChangeEvent(Object source, String type, Thought thought,Stage stage) {
        super(source);
        this.thought = thought;
        this.type = type;
        this.stage = stage;
    }

    public String getType() {
        return type;
    }

    public Thought getThought() {
        return thought;
    }

    public Stage getStage() {
        return stage;
    }
}
