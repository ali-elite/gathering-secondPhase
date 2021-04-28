package ir.sharif.ap2021.Controller;

import ir.sharif.ap2021.DB.Context;
import ir.sharif.ap2021.Model.Notification.Notification;
import ir.sharif.ap2021.Model.User.User;
import ir.sharif.ap2021.View.Menu.Mainmenu;
import ir.sharif.ap2021.View.Menu.Notifications;
import ir.sharif.ap2021.View.ModelView.NotifView;
import ir.sharif.ap2021.View.ModelView.ThoughtView;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class NotifController {

    private Context context = new Context();


    public void load() throws IOException {

        Notifications.getNotifViews().clear();

        for (int i = StaticController.getMyUser().getNotifications().size() - 1; i > -1; i--) {

            NotifView notifView = new NotifView();

            Notification notification = context.Notifications.get(StaticController.getMyUser().getNotifications().get(i));
            notifView.setNotification(notification);
            notifView.setUser(context.Users.get(notification.getSender()));

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/notif.fxml"));
            loader.setController(notifView);

            Notifications.getNotifViews().add((Pane) loader.load());
        }
    }


    public void accept(Notification notification) {

        User user = StaticController.getMyUser();
        User sender = context.Users.get(notification.getSender());

        user.getFollowers().add(sender.getId());
        sender.getFollowings().add(user.getId());

        notification.setText("User " + sender.getUserName() + " followed you!");

        Notification myNotif = new Notification(false, user, sender, "You followed " + user.getUserName());
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


        notification.setText("You rejected " + sender.getUserName() + "'s offer to follow you'");

        Notification myNotif = new Notification(false, user, sender, user.getUserName() + "Has rejected your request to follow");
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


        notification.setText("You silently rejected " + sender.getUserName() + "'s offer to follow you'");

        notification.setAnswered(true);

        context.Notifications.update(notification);
        context.Users.update(user);
    }
}
