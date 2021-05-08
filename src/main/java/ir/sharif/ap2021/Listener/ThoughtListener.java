package ir.sharif.ap2021.Listener;


import ir.sharif.ap2021.Controller.ThoughtController;
import ir.sharif.ap2021.Event.ThoughtEvent;
import ir.sharif.ap2021.View.ModelView.ThoughtView;

import java.io.IOException;


public class ThoughtListener {


    ThoughtView thoughtView;
    ThoughtController thoughtController = new ThoughtController();


    public ThoughtListener(ThoughtView thoughtView) throws IOException {
        this.thoughtView = thoughtView;
    }

    public void eventOccurred(ThoughtEvent formEvent) throws IOException {

        if (formEvent.getType().equals("opinions")) {
            thoughtController.opinion(formEvent);
        } else {
            thoughtController.change(formEvent, thoughtView);
        }

    }


}
