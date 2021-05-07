package ir.sharif.ap2021.Controller;

import ir.sharif.ap2021.DB.Context;
import ir.sharif.ap2021.Event.OutProfileEvent;
import ir.sharif.ap2021.Model.Chat.Chat;
import ir.sharif.ap2021.Model.Notification.Notification;
import ir.sharif.ap2021.Model.User.User;
import ir.sharif.ap2021.Validation.RepeatActionException;
import ir.sharif.ap2021.View.Menu.ChatMenu;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

public class OutProfileController {

    Context context = new Context();

    public void control(OutProfileEvent event) throws RepeatActionException, IOException {

        if (event.getOrder().equals("block")) {

            User user = StaticController.getMyUser();
            User follower = event.getUser();

            if (!user.getBlackList().contains(follower.getId())) {

                user.getBlackList().add(follower.getId());

                if (user.getFollowers().contains(follower.getId())) {
                    user.getFollowers().remove((Integer) follower.getId());
                }

                if (user.getFollowings().contains(follower.getId())) {
                    user.getFollowings().remove((Integer) follower.getId());
                }


                if (follower.getFollowers().contains(user.getId())) {
                    follower.getFollowers().remove((Integer) user.getId());
                }

                if (follower.getFollowings().contains(user.getId())) {
                    follower.getFollowings().remove((Integer) user.getId());
                }

            }


            context.Users.update(user);
            context.Users.update(follower);
        }

        if (event.getOrder().equals("follow")) {

            User user = StaticController.getMyUser();
            User follower = event.getUser();

            if (user.getFollowings().contains(follower.getId())) {

                user.getFollowings().remove((Integer) follower.getId());
                follower.getFollowers().remove((Integer) user.getId());

                Notification unfollowedYou = new Notification(false, user, follower, "User " + user.getUserName() + " unfollowed you!");
                follower.getNotifications().add(unfollowedYou.getId());

                Notification youUnfollowed = new Notification(false, follower, user, "You Unfollowed " + follower.getUserName());
                user.getNotifications().add(youUnfollowed.getId());

                context.Notifications.add(unfollowedYou);
                context.Notifications.add(youUnfollowed);

            } else {

                if (follower.isPrivate()) {

                    for (Integer i : follower.getNotifications()) {

                        Notification notification = context.Notifications.get(i);

                        if (notification.getSender() == user.getId()) {
                            throw new RepeatActionException("You have Already Sent a Request to this user!");
                        }

                    }

                    Notification n = new Notification(true, user, follower, "User " + user.getUserName() + " has sent you a request");
                    follower.getNotifications().add(n.getId());
                    context.Notifications.add(n);

                } else {

                    user.getFollowings().add(follower.getId());
                    follower.getFollowers().add(user.getId());


                    Notification followedYou = new Notification(false, user, follower, "User " + user.getUserName() + " followed you!");
                    follower.getNotifications().add(followedYou.getId());

                    Notification youFollowed = new Notification(false, follower, user, "You followed " + follower.getUserName());
                    user.getNotifications().add(youFollowed.getId());

                    context.Notifications.add(followedYou);
                    context.Notifications.add(youFollowed);

                }
            }

            context.Users.update(user);
            context.Users.update(follower);

        }

        if (event.getOrder().equals("mute")) {

            User user = StaticController.getMyUser();
            User follower = event.getUser();

            if (user.getMuteList().contains(follower.getId())) {
                user.getMuteList().remove((Integer) follower.getId());
            } else {
                user.getMuteList().add(follower.getId());
            }

            context.Users.update(user);
            context.Users.update(follower);

        }

        if (event.getOrder().equals("report")) {

            User follower = event.getUser();
            follower.addReport();
            context.Users.update(follower);

        }

        if (event.getOrder().equals("message")) {

            boolean set = false;
            for (Integer z : StaticController.getMyUser().getChats()) {

                Chat chat = context.Chats.get(z);
                if (chat.getUsers().contains(StaticController.getMyUser().getId())
                        && chat.getUsers().contains(event.getUser().getId())) {
                    ChatMenu.setChat(chat);
                    set = true;
                    break;
                }

            }

            if (!set) {

                User user1 = StaticController.getMyUser();
                User user2 = event.getUser();

                Chat chat = new Chat("sag", false);

                chat.getUsers().add(StaticController.getMyUser().getId());
                chat.getUsers().add(event.getUser().getId());
                user1.getChats().add(chat.getId());
                user2.getChats().add(chat.getId());

                ChatMenu.setChat(chat);

                context.Chats.add(chat);
                context.Users.update(user1);
                context.Users.update(user2);
            }


            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmls/chatmenu.fxml")));
            Scene scene = new Scene(root);
            StaticController.getMyStage().setScene(scene);


        }

    }

}
