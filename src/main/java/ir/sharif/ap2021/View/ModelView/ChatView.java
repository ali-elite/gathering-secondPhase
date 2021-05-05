package ir.sharif.ap2021.View.ModelView;

import ir.sharif.ap2021.Controller.StaticController;
import ir.sharif.ap2021.Event.ChatEvent;
import ir.sharif.ap2021.Listener.ChatListener;
import ir.sharif.ap2021.Model.Chat.Chat;
import ir.sharif.ap2021.Model.User.User;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChatView implements Initializable {


    ChatListener chatListener = new ChatListener(null);

    private Chat chat;
    private User user;
    private int unseen;

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (chat.isGroup()) {
            avatar.setFill(new ImagePattern(new Image("/images/group.png")));
            label.setText(chat.getName());
        } else {

            if (user != null) {
                BufferedImage bufferedImage = null;
                try {
                    bufferedImage = ImageIO.read(new File("src/main/resources" +user.getAvatar()));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                assert bufferedImage != null;
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);


                avatar.setFill(new ImagePattern(image));
                label.setText(user.getUserName());
            }else {


                BufferedImage bufferedImage = null;
                try {
                    bufferedImage = ImageIO.read(new File("src/main/resources" + StaticController.getMyUser().getAvatar()));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                assert bufferedImage != null;
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);


                avatar.setFill(new ImagePattern(image));
                label.setText("Saved Messages");
            }

        }

        unseenText.setText(String.valueOf(unseen));
    }


    public void openChat(MouseEvent mouseEvent) throws IOException {
        chatListener.eventOccurred(new ChatEvent(this, "open", chat, null));
    }


}

