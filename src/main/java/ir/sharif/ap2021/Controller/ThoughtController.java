package ir.sharif.ap2021.Controller;

import ir.sharif.ap2021.DB.Context;
import ir.sharif.ap2021.Event.ThoughtEvent;
import ir.sharif.ap2021.Model.Chat.Chat;
import ir.sharif.ap2021.Model.Chat.Message;
import ir.sharif.ap2021.Model.Thought.Thought;
import ir.sharif.ap2021.Model.User.User;
import ir.sharif.ap2021.View.Menu.Opinions;
import ir.sharif.ap2021.View.ModelView.ChatForwardView;
import ir.sharif.ap2021.View.ModelView.OutProfile;
import ir.sharif.ap2021.View.ModelView.ThoughtView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

public class ThoughtController {

    Context context;

    public ThoughtController() {
        context = new Context();
    }


    public void change(ThoughtEvent e, ThoughtView t) throws IOException {

        Thought thought = e.getThought();
        User myUser = StaticController.getMyUser();
        User user = context.Users.get(e.getThought().getUser());

        if (e.getType().equals("like")) {

            if (thought.getLikers().contains(StaticController.getMyUser().getId())) {
                thought.minusLike();
                thought.getLikers().remove((Integer) StaticController.getMyUser().getId());
            } else {
                thought.addLike();
                thought.getLikers().add(StaticController.getMyUser().getId());
            }

        }

        if (e.getType().equals("ret")) {

            if (thought.getRethoughters().contains(StaticController.getMyUser().getId())) {

                thought.minusRethought();
                thought.getRethoughters().remove((Integer) StaticController.getMyUser().getId());
                myUser.getThoughts().remove((Integer) thought.getId());

            } else {
                thought.addRethought();
                thought.getRethoughters().add(StaticController.getMyUser().getId());
                myUser.getThoughts().add(thought.getId());
            }
        }

        if (e.getType().equals("mention")) {


            Thought opinion = new Thought("o", myUser, user, e.getMentionText(), LocalDateTime.now());
            thought.getOpinions().add(opinion.getId());
            opinion.setParent(thought.getId());

            if (e.getMentionImg().equals("changed")) {
                opinion.setImage("/ThoughtImages/" + opinion.getId() + ".png");


                File old = new File("src/main/resources/ThoughtImages/" + "31" + ".png");
                File notOld = new File("src/main/resources/ThoughtImages/" + opinion.getId() + ".png");

                old.renameTo(notOld);
            }

            //didnt add replies to main user but it can be

            context.Thoughts.add(opinion);
            context.Thoughts.add(thought);

        }

        if (e.getType().equals("muteAuthor")) {
            myUser.getMuteList().add(user.getId());
        }

        if (e.getType().equals("spam")) {
            thought.addSpam();
        }

        if (e.getType().equals("profile")) {

            if (user.getId() != myUser.getId()) {
                OutProfile.setUser(user);
                OutProfile.setFrom(null);
                OutProfile outProfile = new OutProfile();
                outProfile.show();
            }

        }

        if (e.getType().equals("saveMessage")) {

            Chat chat = null;

            for (Integer i : myUser.getChats()) {
                if (context.Chats.get(i).getName().equals("savedMessages")) {
                    chat = context.Chats.get(i);
                }
            }

            if (chat == null) {
                chat = new Chat("savedMessages", false);
                chat.getUsers().add(myUser.getId());
                myUser.getChats().add(chat.getId());
                context.Chats.add(chat);
            }

            Message message = new Message(myUser.getId(), true, e.getThought().getText());

            if(e.getThought().getImage() != null){
                message.setImage(e.getThought().getImage());
            }

            context.Messages.add(message);

            chat.getMessages().add(message.getId());
            context.Chats.update(chat);
        }


        if (e.getType().equals("forwardMessage")) {

            ChatForwardView.setThought(e.getThought());
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmls/forwardSelection.fxml")));
            Scene scene = new Scene(root);
            StaticController.getMyStage().setScene(scene);

        }

        context.Thoughts.update(thought);
        context.Users.update(user);
        context.Users.update(myUser);
        t.reload();
    }

    public void opinion(ThoughtEvent e) throws IOException {

        Thought thought = e.getThought();
        Thought parent = context.Thoughts.get(thought.getParent());
        StaticController.setLastThought(parent);

        Opinions.getOpinions().clear();

        for (int i = thought.getOpinions().size() - 1; i > -1; i--) {

            ThoughtView thoughtView = new ThoughtView();
            Thought opinion = context.Thoughts.get(thought.getOpinions().get(i));

            thoughtView.setThought(opinion);
            thoughtView.setOwnerUser(context.Users.get(opinion.getUser()));
            thoughtView.setMainUser(StaticController.getMyUser());
            thoughtView.setDoedUser(context.Users.get(opinion.getDoed()));


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/thought.fxml"));
            loader.setController(thoughtView);

            Opinions.getOpinions().add((Pane) loader.load());
        }

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmls/opinions.fxml")));
        Scene scene = new Scene(root);
        StaticController.getMyStage().setScene(scene);

    }

}
