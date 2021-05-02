package ir.sharif.ap2021.Controller;

import ir.sharif.ap2021.DB.Context;
import ir.sharif.ap2021.Model.Chat.Chat;
import ir.sharif.ap2021.Model.Chat.Message;
import ir.sharif.ap2021.Model.Thought.Thought;
import ir.sharif.ap2021.Model.User.User;
import ir.sharif.ap2021.View.Menu.Mainmenu;
import ir.sharif.ap2021.View.Menu.NewGroup;
import ir.sharif.ap2021.View.Menu.Notifications;
import ir.sharif.ap2021.View.ModelView.ChatView;
import ir.sharif.ap2021.View.ModelView.ThoughtView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class MainMenuController {


    private Context context = new Context();


    public void load(Mainmenu mainmenu, String username) throws IOException {

        StaticController.setMyUser(context.Users.getByName(username));
        mainmenu.show();

    }


    public void gatherThought(Mainmenu mainmenu) throws IOException {


        Mainmenu.getThoughts().clear();


        for (int i = StaticController.getMyUser().getThoughts().size() - 1; i > -1; i--) {

            ThoughtView thoughtView = new ThoughtView();
            Thought thought = context.Thoughts.get(StaticController.getMyUser().getThoughts().get(i));

            thoughtView.setThought(thought);
            thoughtView.setOwnerUser(context.Users.get(thought.getUser()));
            thoughtView.setMainUser(StaticController.getMyUser());

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/thought.fxml"));
            loader.setController(thoughtView);
            Mainmenu.getThoughts().add((Pane) loader.load());
        }

    }

    public void timeLineThought(Mainmenu mainmenu) throws IOException {

        User mainUser = StaticController.getMyUser();

        ArrayList<User> followers = new ArrayList<>();
        for (Integer i : mainUser.getFollowers()) {
            followers.add(context.Users.get(i));
        }

        ArrayList<User> followings = new ArrayList<>();
        for (Integer i : mainUser.getFollowings()) {
            followings.add(context.Users.get(i));
        }

        ArrayList<Integer> thoughts = new ArrayList<>();

        Mainmenu.getThoughts().clear();

        //mainUserThoughts
        if (!mainUser.getMuteList().contains(mainUser.getId()) && mainUser.isActive()) {

            for (int i = mainUser.getThoughts().size() - 1; i > -1; i--) {

                Thought thought = context.Thoughts.get(mainUser.getThoughts().get(i));

                if (thought.getLocalDateTime().isAfter(LocalDateTime.now().minusDays(1))) {

                    ThoughtView thoughtView = new ThoughtView();

                    thoughtView.setThought(thought);
                    thoughtView.setOwnerUser(context.Users.get(thought.getUser()));
                    thoughtView.setMainUser(StaticController.getMyUser());
                    thoughtView.setDoedUser(context.Users.get(thought.getDoed()));


                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/thought.fxml"));
                    loader.setController(thoughtView);


                    Mainmenu.getThoughts().add((Pane) loader.load());

                    thoughts.add(thought.getId());
                }


            }
        }

        for (User follower : followers) {

            if (!mainUser.getMuteList().contains(follower.getId()) && follower.isActive()) {

                for (int i = follower.getThoughts().size() - 1; i > -1; i--) {

                    Thought thought1 = context.Thoughts.get(follower.getThoughts().get(i));

                    if (thought1.getLocalDateTime().isAfter(LocalDateTime.now().minusDays(1))) {

                        ThoughtView thoughtView = new ThoughtView();

                        thoughtView.setThought(thought1);
                        thoughtView.setOwnerUser(context.Users.get(thought1.getUser()));
                        thoughtView.setMainUser(StaticController.getMyUser());
                        thoughtView.setDoedUser(context.Users.get(thought1.getDoed()));


                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/thought.fxml"));
                        loader.setController(thoughtView);


                        Mainmenu.getThoughts().add((Pane) loader.load());

                        thoughts.add(thought1.getId());
                    }


                }
            }
        }

        for (User following : followings) {

            if (!mainUser.getMuteList().contains(following.getId()) && following.isActive()) {

                for (int i = following.getThoughts().size() - 1; i > -1; i--) {

                    Thought thought2 = context.Thoughts.get(following.getThoughts().get(i));

                    if (!thoughts.contains(thought2.getId())
                            && thought2.getLocalDateTime().isAfter(LocalDateTime.now().minusDays(1))) {

                        ThoughtView thoughtView = new ThoughtView();

                        thoughtView.setThought(thought2);
                        thoughtView.setOwnerUser(context.Users.get(thought2.getUser()));
                        thoughtView.setMainUser(StaticController.getMyUser());
                        thoughtView.setDoedUser(context.Users.get(thought2.getDoed()));


                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/thought.fxml"));
                        loader.setController(thoughtView);


                        Mainmenu.getThoughts().add((Pane) loader.load());

                    }

                }
            }
        }


    }

    public void exploreThought(Mainmenu mainmenu) throws IOException {


        Mainmenu.getThoughts().clear();
        int j = 0;
        for (int i = context.Thoughts.all().size() - 1; i > -1; i--) {

            ThoughtView thoughtView = new ThoughtView();

            Thought thought = context.Thoughts.all().get(i);

            if (thought.getType().equals("t")) {

                thoughtView.setThought(thought);
                thoughtView.setOwnerUser(context.Users.get(thought.getUser()));
                thoughtView.setMainUser(StaticController.getMyUser());
                thoughtView.setDoedUser(context.Users.get(thought.getDoed()));


                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/thought.fxml"));
                loader.setController(thoughtView);


                Mainmenu.getThoughts().add((Pane) loader.load());
                j++;
            }

            if (j == 5) {
                break;
            }

        }
    }


    public void notif(Mainmenu mainmenu) throws IOException {

        Notifications notifications = new Notifications();
        notifications.show();

    }

    public void chats(Mainmenu mainmenu) throws IOException {

        Mainmenu.getThoughts().clear();

        for (int i = StaticController.getMyUser().getChats().size() - 1; i > -1; i--) {

            Chat chat = context.Chats.get(StaticController.getMyUser().getChats().get(i));

            int seenCount = chat.getMessages().size();

            for (Integer m : chat.getMessages()) {

                Message message = context.Messages.get(m);
                if (message.getSeenUsers().contains(StaticController.getMyUser().getId())) {
                    seenCount--;
                }

            }

            if (seenCount == 0) {
                ChatView chatView = new ChatView();
                chatView.setChat(chat);
                chatView.setUnseen(seenCount);

                for (Integer j : chat.getUsers()) {
                    if (j != StaticController.getMyUser().getId()) {
                        chatView.setUser(context.Users.get(j));
                    }
                }


                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/chat.fxml"));
                loader.setController(chatView);

                Mainmenu.getThoughts().add((Pane) loader.load());
            }
        }
        for (int i = StaticController.getMyUser().getChats().size() - 1; i > -1; i--) {

            Chat chat = context.Chats.get(StaticController.getMyUser().getChats().get(i));

            int seenCount = chat.getMessages().size();

            for (Integer m : chat.getMessages()) {

                Message message = context.Messages.get(m);
                if (message.getSeenUsers().contains(StaticController.getMyUser().getId())) {
                    seenCount--;
                }

            }

            if (seenCount != 0) {
                ChatView chatView = new ChatView();
                chatView.setChat(chat);
                chatView.setUnseen(seenCount);

                for (Integer j : chat.getUsers()) {
                    if (j != StaticController.getMyUser().getId()) {
                        chatView.setUser(context.Users.get(j));
                    }
                }


                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/chat.fxml"));
                loader.setController(chatView);

                Mainmenu.getThoughts().add((Pane) loader.load());
            }
        }

    }


    public void group(Mainmenu mainmenu) throws IOException {

        User myUser = StaticController.getMyUser();

        ArrayList<String> userItems = new ArrayList<>();

        for (int i = 0; i < myUser.getFollowers().size(); i++) {
            userItems.add(context.Users.get(myUser.getFollowers().get(myUser.getFollowers().size() - 1 - i)).getUserName());
        }

        NewGroup.setUsers(userItems);

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmls/newGroup.fxml")));
        Scene scene = new Scene(root);
        StaticController.getMyStage().setScene(scene);

    }


}
