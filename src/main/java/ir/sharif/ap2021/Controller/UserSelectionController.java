package ir.sharif.ap2021.Controller;

import ir.sharif.ap2021.DB.Context;
import ir.sharif.ap2021.Event.UserSelectionEvent;
import ir.sharif.ap2021.Model.User.User;
import ir.sharif.ap2021.View.ModelView.OutProfile;
import ir.sharif.ap2021.View.Menu.UserSelect;

import java.io.IOException;

public class UserSelectionController {

    Context context = new Context();

    public void set(UserSelectionEvent event) throws IOException {

        if (event.getOrder().equals("follower")) {

            User myUser = StaticController.getMyUser();

            String[] users = new String[myUser.getFollowers().size()];

            for (int i = 0; i < myUser.getFollowers().size(); i++) {
                users[i] = context.Users.get(myUser.getFollowers().get(myUser.getFollowers().size() - 1 - i)).getUserName();
            }

            UserSelect.setType("follower");
            UserSelect.setUsers(users);
            UserSelect userSelect = new UserSelect();
            userSelect.show();

        }



        if (event.getOrder().equals("following")) {

            User myUser = StaticController.getMyUser();

            String[] users = new String[myUser.getFollowings().size()];

            for (int i = 0; i < myUser.getFollowings().size(); i++) {
                users[i] = context.Users.get(myUser.getFollowings().get(myUser.getFollowings().size() - 1 - i)).getUserName();
            }

            UserSelect.setType("following");
            UserSelect.setUsers(users);
            UserSelect userSelect = new UserSelect();
            userSelect.show();

        }


        if (event.getOrder().equals("load")) {

            OutProfile.setUser(context.Users.getByName(event.getUsername()));
            OutProfile.setFrom(event.getType());
            OutProfile outProfile = new OutProfile();
            outProfile.show();

        }


        }
}
