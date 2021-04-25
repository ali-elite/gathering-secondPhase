package ir.sharif.ap2021.View;

import ir.sharif.ap2021.DB.Context;
import ir.sharif.ap2021.Model.User.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class Profile implements Initializable {

    private User user;
    private Context context = new Context();

    @FXML
    private Circle avatar;
    @FXML
    private Label bioLabel, statusLabel, followerNumberLabel, nicknameLabel, idLabel, lastseenLabel, followingNumberLabel;

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        user = context.Users.get(user.getId());

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

        if (Mainmenu.getMyUser().getFollowers().contains(user.getId())) {
            statusLabel.setText("Follows you");
        } else statusLabel.setText(" ");
    }
}
