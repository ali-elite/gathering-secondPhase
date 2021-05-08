package ir.sharif.ap2021.Controller;

import ir.sharif.ap2021.Config.ErrorConfig;
import ir.sharif.ap2021.Config.FxmlConfig;
import ir.sharif.ap2021.DB.Context;
import ir.sharif.ap2021.Model.Notification.Notification;
import ir.sharif.ap2021.Model.User.User;
import ir.sharif.ap2021.View.Menu.Notifications;
import ir.sharif.ap2021.View.ModelView.NotifView;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class NotifController {

    FxmlConfig fxmlConfig = new FxmlConfig();
    ErrorConfig errorConfig = new ErrorConfig();
    private Context context = new Context();

    public NotifController() throws IOException {
    }


    public void load() throws IOException {

        Notifications.getNotifViews().clear();

        for (int i = StaticController.getMyUser().getNotifications().size() - 1; i > -1; i--) {

            NotifView notifView = new NotifView();

            Notification notification = context.Notifications.get(StaticController.getMyUser().getNotifications().get(i));
            notifView.setNotification(notification);
            notifView.setUser(context.Users.get(notification.getSender()));

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlConfig.getNotif()));
            loader.setController(notifView);

            Notifications.getNotifViews().add((Pane) loader.load());
        }
    }


    public void accept(Notification notification) {

        User user = StaticController.getMyUser();
        User sender = context.Users.get(notification.getSender());

        user.getFollowers().add(sender.getId());
        sender.getFollowings().add(user.getId());

        notification.setText(sender.getUserName() + errorConfig.getFollowedYou());

        Notification myNotif = new Notification(false, user, sender, errorConfig.getYouFollowed()+ user.getUserName());
        sender.getNotifications().add(myNotif.getId());

        notification.setAnswered(true);

        context.Notifications.add(myNotif);
        context.Notifications.update(notification);
        context.Users.update(user);
        context.Users.update(sender);
    }


    public void reject(Notification notification) {

        User user = StaticController.getMyUser();
        User sender = context.Users.get(notification.getSender());


        notification.setText(errorConfig.getYouRejected()+ sender.getUserName());

        Notification myNotif = new Notification(false, user, sender, user.getUserName() + errorConfig.getRejectedYou());
        sender.getNotifications().add(myNotif.getId());

        notification.setAnswered(true);

        context.Notifications.add(myNotif);
        context.Notifications.update(notification);
        context.Users.update(user);
        context.Users.update(sender);


    }


    public void remove(Notification notification) {

        User user = StaticController.getMyUser();
        User sender = context.Users.get(notification.getSender());


        notification.setText(errorConfig.getYouSilentRejected() + sender.getUserName());

        notification.setAnswered(true);

        context.Notifications.update(notification);
        context.Users.update(user);
    }
}
