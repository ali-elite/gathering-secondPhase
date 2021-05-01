package ir.sharif.ap2021.View.ModelView;


import ir.sharif.ap2021.Controller.StaticController;
import ir.sharif.ap2021.Event.OutProfileEvent;
import ir.sharif.ap2021.Event.UserSelectionEvent;
import ir.sharif.ap2021.Listener.OutProfileListener;
import ir.sharif.ap2021.Listener.UserSelectionListener;
import ir.sharif.ap2021.Model.User.User;
import ir.sharif.ap2021.Validation.RepeatActionException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class OutProfile implements Initializable {

    private static User user;
    private static String from;

    private OutProfileListener outProfileListener = new OutProfileListener();
    @FXML
    private Circle avatar;
    @FXML
    private Label bioLabel, statusLabel, followerNumberLabel, nicknameLabel, idLabel, lastseenLabel, followingNumberLabel;
    @FXML
    private ImageView followIMG, lockIMG;
    @FXML
    private MenuItem message, block, mute, report, back;


    public static String getFrom() {
        return from;
    }

    public static void setFrom(String from) {
        OutProfile.from = from;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        OutProfile.user = user;
    }


    public void show() throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmls/outProfile.fxml")));
        Scene scene = new Scene(root);
        StaticController.getMyStage().setScene(scene);

    }


    public void doMessage(ActionEvent event) {

        if (user.isPrivate() && !user.getFollowers().contains(StaticController.getMyUser().getId())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("You need to follow this user before starting a conversation!");
            alert.showAndWait();
        } else {
            // will done after
        }


    }

    public void doBlock(ActionEvent event) throws IOException, RepeatActionException {

        outProfileListener.eventOccurred(new OutProfileEvent(this, user, "block"));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("User blocked Successfully!");
        alert.showAndWait();

    }

    public void doMute(ActionEvent event) throws IOException, RepeatActionException {

        outProfileListener.eventOccurred(new OutProfileEvent(this, user, "mute"));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("User muted or Unmuted Successfully!");
        alert.showAndWait();
    }

    public void doReport(ActionEvent event) throws IOException, RepeatActionException {

        outProfileListener.eventOccurred(new OutProfileEvent(this, user, "report"));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("User reported Successfully!");
        alert.showAndWait();
    }

    public void doFollow(MouseEvent mouseEvent) throws IOException {

        try {

            outProfileListener.eventOccurred(new OutProfileEvent(this, user, "follow"));
            initialize(null, null);

        } catch (RepeatActionException e) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(e.getMessage());
            alert.showAndWait();

        }

        if (user.isPrivate()) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Your follow request has sent successfully!");
            alert.showAndWait();

        }


    }

    public void doBack(ActionEvent event) throws IOException {

        UserSelectionListener userSelectionListener = new UserSelectionListener();


        if (from == null) {
            userSelectionListener.eventOccurred(new UserSelectionEvent(this, "normal", null, null));
        } else {

            if (from.equals("follower")) {
                userSelectionListener.eventOccurred(new UserSelectionEvent(this, "follower", null, null));
            }
            if (from.equals("following")) {
                userSelectionListener.eventOccurred(new UserSelectionEvent(this, "following", null, null));
            }
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        followerNumberLabel.setText(String.valueOf(user.getFollowers().size()));
        followingNumberLabel.setText(String.valueOf(user.getFollowings().size()));
        nicknameLabel.setText(user.getFirstName() + " " + user.getLastName());
        idLabel.setText("@" + user.getUserName());
        lastseenLabel.setText(String.valueOf(user.getLastSeen()));
        avatar.setFill(new ImagePattern(new Image(user.getAvatar())));

        if (StaticController.getMyUser().getFollowings().contains(user.getId())) {
            followIMG.setImage(new Image("/images/unfollow.png"));
        } else {
            followIMG.setImage(new Image("/images/follow.png"));
        }

        if (!user.isPrivate()) {
            lockIMG.setImage(null);
        }

        if (user.getBiography() == null) {
            bioLabel.setText(" No BiO ");
        } else {
            bioLabel.setText(user.getBiography());
        }

        if (StaticController.getMyUser().getFollowers().contains(user.getId())) {
            statusLabel.setText("Follows you");
        } else statusLabel.setText(" ");


    }


}

