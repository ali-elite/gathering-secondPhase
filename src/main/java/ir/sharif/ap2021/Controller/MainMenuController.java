package ir.sharif.ap2021.Controller;

import ir.sharif.ap2021.Config.ErrorConfig;
import ir.sharif.ap2021.Config.FxmlConfig;
import ir.sharif.ap2021.Config.ItemConfig;
import ir.sharif.ap2021.DB.Context;
import ir.sharif.ap2021.Event.MainMenuEvent;
import ir.sharif.ap2021.Model.Chat.Chat;
import ir.sharif.ap2021.Model.Chat.Message;
import ir.sharif.ap2021.Model.Thought.Thought;
import ir.sharif.ap2021.Model.User.User;
import ir.sharif.ap2021.View.Menu.*;
import ir.sharif.ap2021.View.ModelView.ChatForwardView;
import ir.sharif.ap2021.View.ModelView.ChatView;
import ir.sharif.ap2021.View.ModelView.ThoughtView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class MainMenuController {

    ErrorConfig errorConfig = new ErrorConfig();
    FxmlConfig fxmlConfig = new FxmlConfig();
    ItemConfig itemConfig = new ItemConfig();

    private static final Logger logger = LogManager.getLogger(MainMenuController.class);
    private Context context = new Context();

    public MainMenuController() throws IOException {
    }


    public void load(Mainmenu mainmenu, String username) throws IOException {

        User user = context.Users.getByName(username);
        StaticController.setMyUser(user);

        logger.info("user " + user.getId() + " logged in");
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

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlConfig.getThought()));
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


                    FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlConfig.getThought()));
                    loader.setController(thoughtView);


                    Mainmenu.getThoughts().add((Pane) loader.load());

                    thoughts.add(thought.getId());
                }


            }
        }

        for (User follower : followers) {

            if (!mainUser.getMuteList().contains(follower.getId()) && follower.isActive() &&
                    !mainUser.getBlackList().contains(follower.getId()) && !follower.isDeleted()) {

                for (int i = follower.getThoughts().size() - 1; i > -1; i--) {

                    Thought thought1 = context.Thoughts.get(follower.getThoughts().get(i));

                    if (thought1.getLocalDateTime().isAfter(LocalDateTime.now().minusDays(1))) {

                        ThoughtView thoughtView = new ThoughtView();

                        thoughtView.setThought(thought1);
                        thoughtView.setOwnerUser(context.Users.get(thought1.getUser()));
                        thoughtView.setMainUser(StaticController.getMyUser());
                        thoughtView.setDoedUser(context.Users.get(thought1.getDoed()));


                        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlConfig.getThought()));
                        loader.setController(thoughtView);


                        Mainmenu.getThoughts().add((Pane) loader.load());

                        thoughts.add(thought1.getId());
                    }


                }
            }
        }

        for (User following : followings) {

            if (!mainUser.getMuteList().contains(following.getId()) && following.isActive() &&
                    !mainUser.getBlackList().contains(following.getId()) && !following.isDeleted()) {

                for (int i = following.getThoughts().size() - 1; i > -1; i--) {

                    Thought thought2 = context.Thoughts.get(following.getThoughts().get(i));

                    if (!thoughts.contains(thought2.getId())
                            && thought2.getLocalDateTime().isAfter(LocalDateTime.now().minusDays(1))) {

                        ThoughtView thoughtView = new ThoughtView();

                        thoughtView.setThought(thought2);
                        thoughtView.setOwnerUser(context.Users.get(thought2.getUser()));
                        thoughtView.setMainUser(StaticController.getMyUser());
                        thoughtView.setDoedUser(context.Users.get(thought2.getDoed()));


                        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlConfig.getThought()));
                        loader.setController(thoughtView);


                        Mainmenu.getThoughts().add((Pane) loader.load());
                    }

                }
            }
        }


    }

    public void exploreThought(Mainmenu mainmenu) throws IOException {

        User myUser = StaticController.getMyUser();

        Mainmenu.getThoughts().clear();
        int j = 0;
        for (int i = context.Thoughts.all().size() - 1; i > -1; i--) {

            ThoughtView thoughtView = new ThoughtView();

            Thought thought = context.Thoughts.all().get(i);
            User u = context.Users.get(thought.getUser());

            if (thought.getType().equals("t") && !myUser.getBlackList().contains(u.getId())
                    && !myUser.getMuteList().contains(u.getId()) && u.isActive() && !u.isDeleted()) {

                thoughtView.setThought(thought);
                thoughtView.setOwnerUser(context.Users.get(thought.getUser()));
                thoughtView.setMainUser(StaticController.getMyUser());
                thoughtView.setDoedUser(context.Users.get(thought.getDoed()));


                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlConfig.getThought()));
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

            if (seenCount != 0) {
                ChatView chatView = new ChatView();
                chatView.setChat(chat);
                chatView.setUnseen(seenCount);

                for (Integer j : chat.getUsers()) {
                    if (j != StaticController.getMyUser().getId()) {
                        chatView.setUser(context.Users.get(j));
                    }
                }


                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlConfig.getChat()));
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

            if (seenCount == 0) {
                ChatView chatView = new ChatView();
                chatView.setChat(chat);
                chatView.setUnseen(seenCount);

                for (Integer j : chat.getUsers()) {
                    if (j != StaticController.getMyUser().getId()) {
                        chatView.setUser(context.Users.get(j));
                    }
                }


                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlConfig.getChat()));
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
        NewGroup newGroup = new NewGroup();
        newGroup.show();

    }


    public void changePassword(String password) {

        User theUser = StaticController.getMyUser();

        theUser.setPassword(password);

        context.Users.update(theUser);

    }

    public void lastSeen(String lastSeen) {

        User iUser = StaticController.getMyUser();

        if (lastSeen.equals(itemConfig.getAnyOne())) {
            iUser.setLSPublic();
        }
        if (lastSeen.equals(itemConfig.getNoOne())) {
            iUser.setLSPrivate();
        }
        if (lastSeen.equals(itemConfig.getJustFollowers())) {
            iUser.setLSSemiPrivate();
        }

        context.Users.update(iUser);
    }

    public void changeActivity(boolean diactive) {

        User u = StaticController.getMyUser();
        u.setActive(!diactive);

        context.Users.update(u);

    }

    public void changePrivacy(boolean isPrivate) {

        User ur = StaticController.getMyUser();
        ur.setPrivate(isPrivate);

        context.Users.update(ur);

    }

    public void deleteUser(Mainmenu mainmenu) throws IOException {

        User us = StaticController.getMyUser();
        us.setDeleted(true);

        context.Users.update(us);

        logOut(null);
    }

    public void logOut(Mainmenu mainmenu) throws IOException {

        Stage stage = StaticController.getMyStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlConfig.getApp())));
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

    public void forwards(Mainmenu mainmenu) throws IOException {

        ForwardSelection.getChats().clear();

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
                ChatForwardView chatView = new ChatForwardView();
                chatView.setChat(chat);
                chatView.setUnseen(seenCount);

                for (Integer j : chat.getUsers()) {
                    if (j != StaticController.getMyUser().getId()) {
                        chatView.setUser(context.Users.get(j));
                    }
                }


                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlConfig.getChatForward()));
                loader.setController(chatView);

                ForwardSelection.getChats().add((Pane) loader.load());
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
                ChatForwardView chatView = new ChatForwardView();
                chatView.setChat(chat);
                chatView.setUnseen(seenCount);

                for (Integer j : chat.getUsers()) {
                    if (j != StaticController.getMyUser().getId()) {
                        chatView.setUser(context.Users.get(j));
                    }
                }


                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlConfig.getChatForward()));
                loader.setController(chatView);

                ForwardSelection.getChats().add((Pane) loader.load());
            }
        }

    }

    public void unblock(MainMenuEvent mainmenu) {

        User unblocked = context.Users.getByName(mainmenu.getUsername());

        User myUser = StaticController.getMyUser();

        myUser.getBlackList().remove((Integer) unblocked.getId());

        context.Users.update(myUser);

    }

    public void blacklist(MainMenuEvent event) throws IOException {

        Blacklist.getUsers().clear();

        for (Integer i : StaticController.getMyUser().getBlackList()) {
            User u = context.Users.get(i);
            Blacklist.getUsers().add(u.getUserName());
        }


        Stage stage = StaticController.getMyStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlConfig.getBlacklist())));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void groupMessage(Mainmenu mainmenu) throws IOException {

        User myUser = StaticController.getMyUser();

        ArrayList<String> userItems = new ArrayList<>();

        for (int i = 0; i < myUser.getFollowers().size(); i++) {
            userItems.add(context.Users.get(myUser.getFollowers().get(myUser.getFollowers().size() - 1 - i)).getUserName());
        }

        GroupMessage.setUsers(userItems);

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlConfig.getGroupMessage())));
        Scene scene = new Scene(root);
        StaticController.getMyStage().setScene(scene);

    }

}
