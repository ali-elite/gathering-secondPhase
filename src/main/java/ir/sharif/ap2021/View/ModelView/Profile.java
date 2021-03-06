package ir.sharif.ap2021.View.ModelView;

import ir.sharif.ap2021.Config.ErrorConfig;
import ir.sharif.ap2021.Controller.StaticController;
import ir.sharif.ap2021.Event.UserSelectionEvent;
import ir.sharif.ap2021.Listener.UserSelectionListener;
import ir.sharif.ap2021.Model.User.User;
import ir.sharif.ap2021.Validation.AuthenticationException;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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

public class Profile implements Initializable {

    ErrorConfig errorConfig = new ErrorConfig();

    private User user;
    private UserSelectionListener userSelectionListener = new UserSelectionListener();
    @FXML
    private Circle avatar;
    @FXML
    private Label bioLabel, statusLabel, followerNumberLabel, nicknameLabel, idLabel, lastseenLabel, followingNumberLabel;

    public Profile() throws IOException {
    }

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

        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File(errorConfig.getMainConfig().getResourcesPath() + user.getAvatar()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert bufferedImage != null;
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);

        avatar.setFill(new ImagePattern(image));


        if (user.getBiography() == null) {
            bioLabel.setText(":)))");
        } else {
            bioLabel.setText(user.getBiography());
        }

        if (StaticController.getMyUser().getFollowers().contains(user.getId())) {
            statusLabel.setText(errorConfig.getFollowsYou());
        } else statusLabel.setText(" ");

    }

    public void selectFollower(MouseEvent mouseEvent) throws IOException, AuthenticationException {


        if (StaticController.getMyUser().getFollowers().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(errorConfig.getEmptyFollower());
            alert.showAndWait();
        } else {
            userSelectionListener.eventOccurred(new UserSelectionEvent(this,"follower",null,null));
        }


    }

    public void selectFollowing(MouseEvent mouseEvent) throws IOException, AuthenticationException {

        if (StaticController.getMyUser().getFollowings().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(errorConfig.getEmptyFollowing());
            alert.showAndWait();
        } else {
            userSelectionListener.eventOccurred(new UserSelectionEvent(this,"following",null,null));
        }

    }

}
