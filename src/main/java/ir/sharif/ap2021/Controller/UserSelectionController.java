package ir.sharif.ap2021.Controller;

import ir.sharif.ap2021.DB.Context;
import ir.sharif.ap2021.Event.UserSelectionEvent;
import ir.sharif.ap2021.Model.User.User;
import ir.sharif.ap2021.Validation.AuthenticationException;
import ir.sharif.ap2021.View.Menu.UserSelect;
import ir.sharif.ap2021.View.ModelView.OutProfile;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

public class UserSelectionController {

    Context context = new Context();

    public void set(UserSelectionEvent event) throws IOException, AuthenticationException {

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

            if (!exist(event.getUsername())) {
                throw new AuthenticationException("Username doesn't exist");
            }

            OutProfile.setUser(context.Users.getByName(event.getUsername()));
            OutProfile.setFrom(event.getType());
            OutProfile outProfile = new OutProfile();
            outProfile.show();

        }

        if (event.getOrder().equals("normal")) {

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmls/mainmenu.fxml")));
            Scene scene = new Scene(root);
            StaticController.getMyStage().setScene(scene);

        }


    }

    public boolean exist(String username) {

        boolean check = false;

        for (User user : context.Users.all()) {
            if (user.getUserName().equals(username)) {
                check = true;
                break;
            }
        }

        return check;

    }
}
