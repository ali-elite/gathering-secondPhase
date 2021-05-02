package ir.sharif.ap2021.View.ModelView;

import ir.sharif.ap2021.Controller.StaticController;
import ir.sharif.ap2021.Event.MessageEvent;
import ir.sharif.ap2021.Listener.MessageListener;
import ir.sharif.ap2021.Model.Chat.Message;
import ir.sharif.ap2021.Model.User.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

import java.net.URL;
import java.util.ResourceBundle;

public class MessageView implements Initializable {

    MessageListener messageListener = new MessageListener();

    private Message message;
    private User sender;

    @FXML
    private Circle avatar;
    @FXML
    private Label textLabel,forwardLabel;
    @FXML
    private Polygon triangle;
    @FXML
    private AnchorPane textPane;


    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void editMessage(ActionEvent event) {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        textLabel.setText(message.getText());
        avatar.setFill(new ImagePattern(new Image(sender.getAvatar())));
        forwardLabel.setVisible(message.isForwarded());

        if(sender.getId() == StaticController.getMyUser().getId()){
            textPane.setStyle("-fx-background-color: green");
        }


    }

    public void seen(MouseEvent mouseEvent) {

        messageListener.eventOccurred(new MessageEvent(this,"seen",message));

    }


}
