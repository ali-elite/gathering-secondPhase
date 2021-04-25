package ir.sharif.ap2021.Listener;

import ir.sharif.ap2021.Controller.ThoughtChangeController;
import ir.sharif.ap2021.Event.ThoughtChangeEvent;

import java.io.IOException;


public class ThoughtChangeListener {

    private final ThoughtChangeController thoughtChangeController = new ThoughtChangeController();

    public void eventOccurred(ThoughtChangeEvent formEvent) throws IOException {
        thoughtChangeController.change(formEvent);
    }
}
