package ir.sharif.ap2021.Controller;

import ir.sharif.ap2021.Config.ErrorConfig;
import ir.sharif.ap2021.Config.FxmlConfig;
import ir.sharif.ap2021.DB.Context;
import ir.sharif.ap2021.Event.OutProfileEvent;
import ir.sharif.ap2021.Model.Chat.Chat;
import ir.sharif.ap2021.Model.Notification.Notification;
import ir.sharif.ap2021.Model.User.User;
import ir.sharif.ap2021.Validation.RepeatActionException;
import ir.sharif.ap2021.View.Menu.ChatMenu;

import java.io.IOException;

public class OutProfileController {

    FxmlConfig fxmlConfig = new FxmlConfig();
    ErrorConfig errorConfig = new ErrorConfig();

    Context context = new Context();

    public OutProfileController() throws IOException {
    }

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

                Notification unfollowedYou = new Notification(false, user, follower, user.getUserName() + errorConfig.getUnfollowedYou());
                follower.getNotifications().add(unfollowedYou.getId());

                Notification youUnfollowed = new Notification(false, follower, user, errorConfig.getYouUnfollowed()+ follower.getUserName());
                user.getNotifications().add(youUnfollowed.getId());

                context.Notifications.add(unfollowedYou);
                context.Notifications.add(youUnfollowed);

            } else {

                if (follower.isPrivate()) {

                    for (Integer i : follower.getNotifications()) {

                        Notification notification = context.Notifications.get(i);

                        if (notification.getSender() == user.getId()) {
                            throw new RepeatActionException(errorConfig.getAlreadyRequested());
                        }

                    }

                    Notification n = new Notification(true, user, follower, user.getUserName() + errorConfig.getRequested());
                    follower.getNotifications().add(n.getId());
                    context.Notifications.add(n);

                } else {

                    user.getFollowings().add(follower.getId());
                    follower.getFollowers().add(user.getId());


                    Notification followedYou = new Notification(false, user, follower, user.getUserName() +errorConfig.getFollowedYou());
                    follower.getNotifications().add(followedYou.getId());

                    Notification youFollowed = new Notification(false, follower, user, errorConfig.getYouFollowed()+ follower.getUserName());
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

                Chat chat = new Chat(user1.getUserName()+" with "+user2.getUserName(), false);

                chat.getUsers().add(StaticController.getMyUser().getId());
                chat.getUsers().add(event.getUser().getId());
                user1.getChats().add(chat.getId());
                user2.getChats().add(chat.getId());

                ChatMenu.setChat(chat);

                context.Chats.add(chat);
                context.Users.update(user1);
                context.Users.update(user2);
            }

            ChatMenu chatMenu = new ChatMenu();
            chatMenu.show();

        }

    }

}
