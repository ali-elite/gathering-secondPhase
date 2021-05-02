package ir.sharif.ap2021.View.ModelView;

import ir.sharif.ap2021.Controller.StaticController;
import ir.sharif.ap2021.Event.UserSelectionEvent;
import ir.sharif.ap2021.Listener.UserSelectionListener;
import ir.sharif.ap2021.Model.User.User;
import ir.sharif.ap2021.Validation.AuthenticationException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Profile implements Initializable {

    private User user;
    private UserSelectionListener userSelectionListener = new UserSelectionListener();
    @FXML
    private Circle avatar;
    @FXML
    private Label bioLabel, statusLabel, followerNumberLabel, nicknameLabel, idLabel, lastseenLabel, followingNumberLabel;

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        followerNumberLabel.setText(String.valueOf(user.getFollowers().size()));
        followingNumberLabel.setText(String.valueOf(user.getFollowings().size()));
        nicknameLabel.setText(user.getFirstName() + " " + user.getLastName());
        idLabel.setText("@" + user.getUserName());
        lastseenLabel.setText(String.valueOf(user.getLastSeen()));
        avatar.setFill(new ImagePattern(new Image(user.getAvatar())));


        if (user.getBiography() == null) {
            bioLabel.setText(" No BiO ");
        } else {
            bioLabel.setText(user.getBiography());
        }

        if (StaticController.getMyUser().getFollowers().contains(user.getId())) {
            statusLabel.setText("Follows you");
        } else statusLabel.setText(" ");

    }

    public void selectFollower(MouseEvent mouseEvent) throws IOException, AuthenticationException {


        if (StaticController.getMyUser().getFollowers().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Your follower list is empty!");
            alert.showAndWait();
        } else {
            userSelectionListener.eventOccurred(new UserSelectionEvent(this,"follower",null,null));
        }


    }

    public void selectFollowing(MouseEvent mouseEvent) throws IOException, AuthenticationException {

        if (StaticController.getMyUser().getFollowings().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Your following list is empty!");
            alert.showAndWait();
        } else {
            userSelectionListener.eventOccurred(new UserSelectionEvent(this,"following",null,null));
        }

    }

}
