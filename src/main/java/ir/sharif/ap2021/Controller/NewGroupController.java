package ir.sharif.ap2021.Controller;

import ir.sharif.ap2021.DB.Context;
import ir.sharif.ap2021.Event.NewGroupEvent;
import ir.sharif.ap2021.Model.Chat.Chat;
import ir.sharif.ap2021.Model.User.User;

import java.util.ArrayList;

public class NewGroupController {

    Context context = new Context();


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


}
