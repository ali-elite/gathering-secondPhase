package ir.sharif.ap2021.View.ModelView;

import ir.sharif.ap2021.Controller.StaticController;
import ir.sharif.ap2021.Event.ChatEvent;
import ir.sharif.ap2021.Listener.ChatListener;
import ir.sharif.ap2021.Model.Chat.Chat;
import ir.sharif.ap2021.Model.Thought.Thought;
import ir.sharif.ap2021.Model.User.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChatForwardView implements Initializable {

    ChatListener chatListener = new ChatListener(null);

    private Chat chat;
    private User user;
    private int unseen;
    private static Thought thought;

    @FXML
    private Circle avatar;
    @FXML
    private Label label, unseenText;


    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getUnseen() {
        return unseen;
    }

    public void setUnseen(int unseen) {
        this.unseen = unseen;
    }

    public static Thought getThought() {
        return thought;
    }

    public static void setThought(Thought thought) {
        ChatForwardView.thought = thought;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (chat.isGroup()) {
            avatar.setFill(new ImagePattern(new Image("/images/group.png")));
            label.setText(chat.getName());
        } else {

            if (user != null) {
                avatar.setFill(new ImagePattern(new Image(user.getAvatar())));
                label.setText(user.getUserName());
            }else {
                avatar.setFill(new ImagePattern(new Image(StaticController.getMyUser().getAvatar())));
                label.setText("Saved Messages");
            }

        }

        unseenText.setText(String.valueOf(unseen));
    }



    public void forwardChat(MouseEvent mouseEvent) throws IOException {

        chatListener.eventOccurred(new ChatEvent(this, "forward", chat, null));
        StaticController.getMyMainMenu().show();

    }



}
