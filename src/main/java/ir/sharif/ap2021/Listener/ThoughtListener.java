package ir.sharif.ap2021.Listener;

import ir.sharif.ap2021.Controller.ThoughtController;
import ir.sharif.ap2021.Event.ThoughtEvent;

import java.io.IOException;


public class ThoughtListener {

    private final ThoughtController thoughtController = new ThoughtController();

    public void eventOccurred(ThoughtEvent formEvent) throws IOException {
        thoughtController.change(formEvent);
    }
}
