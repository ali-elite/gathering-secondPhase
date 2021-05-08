package ir.sharif.ap2021.Controller;

import ir.sharif.ap2021.Config.MainConfig;
import ir.sharif.ap2021.DB.Context;
import ir.sharif.ap2021.Event.ShareThoughtEvent;
import ir.sharif.ap2021.Model.Thought.Thought;
import ir.sharif.ap2021.Model.User.User;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class ShareThoughtController {

    MainConfig mainConfig = new MainConfig();
    Context context = new Context();

    public ShareThoughtController() throws IOException {
    }

    public void share(ShareThoughtEvent formEvent) {

        User user = StaticController.getMyUser();

        Thought thought = new Thought("t", user, user, formEvent.getText(), LocalDateTime.now());

        if (formEvent.getChange().equals("changed")) {
            thought.setImage("/ThoughtImages/" + thought.getId() + ".png");


            File old = new File(mainConfig.getResourcesPath() + "/ThoughtImages/" + "733" + ".png");
            File notOld = new File(mainConfig.getResourcesPath() + "/ThoughtImages/" + thought.getId() + ".png");

            old.renameTo(notOld);
        }

        context.Thoughts.add(thought);
        user.getThoughts().add(thought.getId());
        context.Users.update(user);
    }

}
