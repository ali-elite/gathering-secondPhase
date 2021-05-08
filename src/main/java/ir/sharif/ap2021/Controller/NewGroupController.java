package ir.sharif.ap2021.Controller;

import ir.sharif.ap2021.Config.MainConfig;
import ir.sharif.ap2021.DB.Context;
import ir.sharif.ap2021.Event.NewGroupEvent;
import ir.sharif.ap2021.Model.Chat.Chat;
import ir.sharif.ap2021.Model.Chat.Message;
import ir.sharif.ap2021.Model.User.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class NewGroupController {

    Context context = new Context();
    MainConfig mainConfig = new MainConfig();

    public NewGroupController() throws IOException {
    }


    public void makeGroup(NewGroupEvent formEvent) {

        ArrayList<User> users = new ArrayList<>();
        User myUser = StaticController.getMyUser();

        for (String s : formEvent.getUsers()) {
            users.add(context.Users.getByName(s));
        }

        Chat gp = new Chat(formEvent.getName(), true);

        for (User user : users) {

            gp.getUsers().add(user.getId());
            user.getChats().add(gp.getId());

            context.Users.update(user);
        }

        gp.getUsers().add(myUser.getId());
        myUser.getChats().add(gp.getId());

        context.Chats.add(gp);
        context.Users.update(myUser);
    }


    public void groupMessage(NewGroupEvent formEvent) {

        ArrayList<User> users = new ArrayList<>();
        User myUser = StaticController.getMyUser();

        for (String s : formEvent.getUsers()) {
            users.add(context.Users.getByName(s));
        }

        Chat gp = new Chat("This is a group message", true);

        Message message = new Message(StaticController.getMyUser().getId(), false, formEvent.getName());

        if (formEvent.getChanged().equals("changed")) {
            message.setImage("/MessageImages/" + message.getId() + ".png");
            File old = new File(mainConfig.getResourcesPath() + "/MessageImages/" + "311" + ".png");
            File notOld = new File(mainConfig.getResourcesPath() + "/MessageImages/" + message.getId() + ".png");

            old.renameTo(notOld);
        }

        context.Messages.add(message);

        gp.getMessages().add(message.getId());

        for (User user : users) {

            gp.getUsers().add(user.getId());
            user.getChats().add(gp.getId());

            context.Users.update(user);
        }

        gp.getUsers().add(myUser.getId());
        myUser.getChats().add(gp.getId());

        context.Chats.add(gp);
        context.Users.update(myUser);

    }
}
