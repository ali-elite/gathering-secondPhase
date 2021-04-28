package ir.sharif.ap2021.Controller;

import ir.sharif.ap2021.DB.Context;
import ir.sharif.ap2021.Model.Notification.Notification;
import ir.sharif.ap2021.View.Menu.Mainmenu;
import ir.sharif.ap2021.View.Menu.Notifications;
import ir.sharif.ap2021.View.ModelView.ThoughtView;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MainMenuController {


    private Context context = new Context();


    public void load(Mainmenu mainmenu, String username) throws IOException {

        StaticController.setMyUser(context.Users.getByName(username));
        mainmenu.show();

    }


    public void update(Mainmenu mainmenu) throws IOException {
        mainmenu.update(null);
    }


    public void thought(Mainmenu mainmenu) throws IOException {

        Mainmenu.getThoughts().clear();
        for (int i = StaticController.getMyUser().getThoughts().size() - 1; i > -1; i--) {

            ThoughtView thoughtView = new ThoughtView();

            thoughtView.setThought(context.Thoughts.get(StaticController.getMyUser().getThoughts().get(i)));
            thoughtView.setOwnerUser(context.Users.get(context.Thoughts.get(StaticController.getMyUser().getThoughts().get(i)).getUser()));
            thoughtView.setMainUser(StaticController.getMyUser());

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/thought.fxml"));
            loader.setController(thoughtView);

            Mainmenu.getThoughts().add((Pane) loader.load());
        }
    }

    public void notif(Mainmenu mainmenu) throws IOException {

        Notifications notifications = new Notifications();
        notifications.show();

    }
}
