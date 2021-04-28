package ir.sharif.ap2021.Controller;

import ir.sharif.ap2021.DB.Context;
import ir.sharif.ap2021.Event.OutProfileEvent;
import ir.sharif.ap2021.Model.Notification.Notification;
import ir.sharif.ap2021.Model.User.User;
import ir.sharif.ap2021.Validation.RepeatActionException;

public class OutProfileController {

    Context context = new Context();

    public void control(OutProfileEvent event) throws RepeatActionException {

        if (event.getOrder().equals("block")) {

            User user = StaticController.getMyUser();
            User follower = event.getUser();

            if (!user.getBlackList().contains(follower.getId())) {
                user.getBlackList().add(follower.getId());
            }


        }

        if (event.getOrder().equals("follow")) {

            User user = StaticController.getMyUser();
            User follower = context.Users.get(event.getUser().getId());

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


    }

}
