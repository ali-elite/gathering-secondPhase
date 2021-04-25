package ir.sharif.ap2021.Controller;

import ir.sharif.ap2021.DB.Context;
import ir.sharif.ap2021.Event.ThoughtChangeEvent;
import ir.sharif.ap2021.Listener.ThoughtChangeListener;
import ir.sharif.ap2021.Model.Thought.Thought;
import ir.sharif.ap2021.View.Mainmenu;

import java.io.IOException;

public class ThoughtChangeController {

    Context context;
    ReloadController reloadController = new ReloadController();

    public ThoughtChangeController() {
        context = new Context();
    }

    public void change(ThoughtChangeEvent e) throws IOException {

        Thought thought = e.getThought();

        if (e.getType().equals("like")) {

            if (thought.getLikers().contains(Mainmenu.getMyUser().getId())) {
                thought.minusLike();
                thought.getLikers().remove((Integer) Mainmenu.getMyUser().getId());
            } else {
                thought.addLike();
                thought.getLikers().add(Mainmenu.getMyUser().getId());
            }

        }

        if (e.getType().equals("ret")) {

            if (thought.getRethoughters().contains(Mainmenu.getMyUser().getId())) {
                thought.minusRethought();
                thought.getRethoughters().remove((Integer) Mainmenu.getMyUser().getId());
            } else {
                thought.addRethought();
                thought.getRethoughters().add(Mainmenu.getMyUser().getId());
            }
        }


        context.Thoughts.update(thought);
        reloadController.reloadMain(e.getStage());


    }

}
