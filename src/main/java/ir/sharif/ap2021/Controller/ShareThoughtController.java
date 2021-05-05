package ir.sharif.ap2021.Controller;

import ir.sharif.ap2021.DB.Context;
import ir.sharif.ap2021.Event.ShareThoughtEvent;
import ir.sharif.ap2021.Model.Thought.Thought;
import ir.sharif.ap2021.Model.User.User;

import java.io.File;
import java.time.LocalDateTime;

public class ShareThoughtController {

    Context context = new Context();

    public ShareThoughtController() {
        context = new Context();
    }

    public void share(ShareThoughtEvent formEvent) {

        User user = StaticController.getMyUser();

        Thought thought = new Thought("t", user, user, formEvent.getText(), LocalDateTime.now());

        if (formEvent.getChange().equals("changed")) {
            thought.setImage("/ThoughtImages/" + thought.getId() + ".png");


            File old = new File("src/main/resources/ThoughtImages/" + "733" + ".png");
            File notOld = new File("src/main/resources/ThoughtImages/" + thought.getId() + ".png");

            old.renameTo(notOld);
        }

        context.Thoughts.add(thought);
        user.getThoughts().add(thought.getId());
        context.Users.update(user);
    }

}
