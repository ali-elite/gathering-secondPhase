package ir.sharif.ap2021.Controller;

import ir.sharif.ap2021.DB.Context;
import ir.sharif.ap2021.Event.ThoughtEvent;
import ir.sharif.ap2021.Model.Thought.Thought;


import java.io.IOException;

public class ThoughtController {

    Context context;

    public ThoughtController() {
        context = new Context();
    }


    public void change(ThoughtEvent e) throws IOException {

        Thought thought = e.getThought();

        if (e.getType().equals("like")) {

            if (thought.getLikers().contains(StaticController.getMyUser().getId())) {
                thought.minusLike();
                thought.getLikers().remove((Integer) StaticController.getMyUser().getId());
            } else {
                thought.addLike();
                thought.getLikers().add(StaticController.getMyUser().getId());
            }

        }

        if (e.getType().equals("ret")) {

            if (thought.getRethoughters().contains(StaticController.getMyUser().getId())) {
                thought.minusRethought();
                thought.getRethoughters().remove((Integer) StaticController.getMyUser().getId());
            } else {
                thought.addRethought();
                thought.getRethoughters().add(StaticController.getMyUser().getId());
            }
        }

        context.Thoughts.update(thought);
        e.getThoughtView().reload();

    }

}
