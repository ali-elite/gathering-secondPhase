package ir.sharif.ap2021.Controller;

import ir.sharif.ap2021.DB.Context;
import ir.sharif.ap2021.Event.ThoughtEvent;
import ir.sharif.ap2021.Model.Thought.Thought;
import ir.sharif.ap2021.Model.User.User;
import ir.sharif.ap2021.View.ModelView.ThoughtView;

import java.io.IOException;

public class ThoughtController {

    Context context;

    public ThoughtController() {
        context = new Context();
    }


    public void change(ThoughtEvent e, ThoughtView t) throws IOException {

        Thought thought = e.getThought();
        User myUser = StaticController.getMyUser();
        User user = context.Users.get(e.getThought().getUser());

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
                myUser.getThoughts().remove((Integer) thought.getId());

            } else {
                thought.addRethought();
                thought.getRethoughters().add(StaticController.getMyUser().getId());
                myUser.getThoughts().add(thought.getId());
            }
        }

        if (e.getType().equals("mention")) {

        }

        if (e.getType().equals("muteAuthor")) {

            myUser.getMuteList().add(user.getId());

        }

        if (e.getType().equals("spam")) {
            thought.addSpam();
        }

        if (e.getType().equals("profile")) {

        }

        if (e.getType().equals("opinions")) {

        }


        context.Thoughts.update(thought);
        context.Users.update(user);
        context.Users.update(myUser);
        t.reload();

    }

}
