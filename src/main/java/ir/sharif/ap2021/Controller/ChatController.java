package ir.sharif.ap2021.Controller;

import ir.sharif.ap2021.DB.Context;
import ir.sharif.ap2021.Event.ChatEvent;
import ir.sharif.ap2021.Model.Chat.Chat;
import ir.sharif.ap2021.Model.Chat.Message;
import ir.sharif.ap2021.Model.User.User;
import ir.sharif.ap2021.View.Menu.ChatMenu;
import ir.sharif.ap2021.View.ModelView.ChatForwardView;
import ir.sharif.ap2021.View.ModelView.MessageView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ChatController {

    Context context = new Context();


    public void loadChats(ChatEvent formEvent) throws IOException {

        Chat chat = formEvent.getChat();

        ChatMenu.getMessages().clear();
        int i = 0;
        for (Integer m : chat.getMessages()) {

            Message message = context.Messages.get(m);
            User sender = context.Users.get(message.getSender());

            if (sender.isActive() && !sender.isDeleted()) {

                MessageView messageView = new MessageView();
                messageView.setMessage(message);
                messageView.setSender(context.Users.get(message.getSender()));

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/message.fxml"));
                loader.setController(messageView);
                ChatMenu.getMessages().add((Pane) loader.load());
                i++;
            }

            if (i == 5) {
                break;
            }


        }

    }


    public void send(ChatEvent formEvent) {

        Message message = new Message(StaticController.getMyUser().getId(), false, formEvent.getPm());

        if (formEvent.getChanged().equals("changed")) {
            message.setImage("/MessageImages/" + message.getId() + ".png");
            File old = new File("src/main/resources/MessageImages/" + "311" + ".png");
            File notOld = new File("src/main/resources/MessageImages/" + message.getId() + ".png");

            old.renameTo(notOld);
        }

        context.Messages.add(message);

        formEvent.getChat().getMessages().add(message.getId());
        context.Chats.update(formEvent.getChat());

    }

    public void open(ChatEvent formEvent) throws IOException {

        ChatMenu.setChat(formEvent.getChat());

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmls/chatmenu.fxml")));
        Scene scene = new Scene(root);
        StaticController.getMyStage().setScene(scene);

    }

    public void forward(ChatEvent formEvent) {

        Chat theChat = formEvent.getChat();

        Message theMessage = new Message(StaticController.getMyUser().getId(),
                true, ChatForwardView.getThought().getText());

        if (ChatForwardView.getThought().getImage() != null) {
            theMessage.setImage(ChatForwardView.getThought().getImage());
        }

        context.Messages.add(theMessage);

        theChat.getMessages().add(theMessage.getId());

        context.Chats.update(theChat);
    }
}
