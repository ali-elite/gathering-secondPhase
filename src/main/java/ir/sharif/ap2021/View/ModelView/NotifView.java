package ir.sharif.ap2021.View.ModelView;

import ir.sharif.ap2021.Event.NotifEvent;
import ir.sharif.ap2021.Listener.NotifListener;
import ir.sharif.ap2021.Model.Notification.Notification;
import ir.sharif.ap2021.Model.User.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NotifView implements Initializable {

    private Notification notification;
    private User user;
    private NotifListener notifListener = new NotifListener();

    @FXML
    private Label textLabel;
    @FXML
    private Button acceptBtn, rejectBtn, removeBtn;
    @FXML
    private Circle avatar;


    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void accept(ActionEvent event) throws IOException {

        notifListener.eventOccurred(new NotifEvent(this, "accept", notification));
        initialize(null, null);

    }

    public void reject(ActionEvent event) throws IOException {

        notifListener.eventOccurred(new NotifEvent(this, "reject", notification));
        initialize(null, null);
    }

    public void remove(ActionEvent event) throws IOException {

        notifListener.eventOccurred(new NotifEvent(this, "remove", notification));
        initialize(null, null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (!notification.isRequest() || notification.isAnswered()) {

            acceptBtn.setDisable(true);
            rejectBtn.setDisable(true);
            removeBtn.setDisable(true);

        }

        textLabel.setText(notification.getText());
        avatar.setFill(new ImagePattern(new Image(user.getAvatar())));

    }


}
