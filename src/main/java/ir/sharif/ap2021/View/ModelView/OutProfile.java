package ir.sharif.ap2021.View.ModelView;


import ir.sharif.ap2021.Config.ErrorConfig;
import ir.sharif.ap2021.Config.FxmlConfig;
import ir.sharif.ap2021.Config.ImageConfig;
import ir.sharif.ap2021.Controller.StaticController;
import ir.sharif.ap2021.Event.OutProfileEvent;
import ir.sharif.ap2021.Event.UserSelectionEvent;
import ir.sharif.ap2021.Listener.OutProfileListener;
import ir.sharif.ap2021.Listener.UserSelectionListener;
import ir.sharif.ap2021.Model.User.User;
import ir.sharif.ap2021.Validation.AuthenticationException;
import ir.sharif.ap2021.Validation.RepeatActionException;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class OutProfile implements Initializable {

    ErrorConfig errorConfig = new ErrorConfig();
    ImageConfig imageConfig = new ImageConfig();
    FxmlConfig fxmlConfig = new FxmlConfig();

    private static User user;
    private static String from;

    private OutProfileListener outProfileListener = new OutProfileListener();
    @FXML
    private Circle avatar;
    @FXML
    private Label bioLabel, statusLabel, followerNumberLabel, nicknameLabel, idLabel, lastseenLabel, followingNumberLabel;
    @FXML
    private ImageView followIMG, lockIMG, muteIMG;
    @FXML
    private MenuItem message, block, mute, report, back;
    @FXML
    private MenuBar menu;

    public OutProfile() throws IOException {
    }


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

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlConfig.getOutProfile())));
        Scene scene = new Scene(root);
        StaticController.getMyStage().setScene(scene);

    }


    public void doMessage(ActionEvent event) throws RepeatActionException, IOException {

        if (!user.getFollowers().contains(StaticController.getMyUser().getId())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(errorConfig.getFollowBefore());
            alert.showAndWait();

        } else {
            outProfileListener.eventOccurred(new OutProfileEvent(this, user, "message"));
        }

    }

    public void doBlock(ActionEvent event) throws IOException, RepeatActionException {

        outProfileListener.eventOccurred(new OutProfileEvent(this, user, "block"));

        initialize(null, null);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(errorConfig.getBlocked());
        alert.showAndWait();

    }

    public void doMute(ActionEvent event) throws IOException, RepeatActionException {

        outProfileListener.eventOccurred(new OutProfileEvent(this, user, "mute"));
        initialize(null, null);
    }

    public void doReport(ActionEvent event) throws IOException, RepeatActionException {

        outProfileListener.eventOccurred(new OutProfileEvent(this, user, "report"));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(errorConfig.getReported());
        alert.showAndWait();
    }

    public void doFollow(MouseEvent mouseEvent) throws IOException {

        boolean rep = false;
        try {

            outProfileListener.eventOccurred(new OutProfileEvent(this, user, "follow"));


        } catch (RepeatActionException e) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(e.getMessage());
            alert.showAndWait();

            rep = true;

        }

        if (!rep && user.isPrivate()) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(errorConfig.getRequestSent());
            alert.showAndWait();

        }

        initialize(null, null);

    }

    public void doBack(ActionEvent event) throws IOException, AuthenticationException {

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


        if (!user.isActive()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(errorConfig.getDeactiveUser());
            alert.show();
            followIMG.setVisible(false);
            menu.setVisible(false);
            followingNumberLabel.setVisible(false);
            followerNumberLabel.setVisible(false);
            lockIMG.setVisible(false);
            muteIMG.setVisible(false);

        } else {

            followerNumberLabel.setText(String.valueOf(user.getFollowers().size()));
            followingNumberLabel.setText(String.valueOf(user.getFollowings().size()));
            nicknameLabel.setText(user.getFirstName() + " " + user.getLastName());
            idLabel.setText("@" + user.getUserName());


            if (user.getLastSeenPrivacy().equals("Public")) {
                lastseenLabel.setText(String.valueOf(user.getLastSeen()));
            } else if (user.getLastSeenPrivacy().equals("SemiPrivate") && StaticController.getMyUser().getFollowers().contains(user.getId())) {
                lastseenLabel.setText(String.valueOf(user.getLastSeen()));
            } else if (user.getLastSeenPrivacy().equals("Private")) {
                lastseenLabel.setText(errorConfig.getLastSeenRecently());
            }


            BufferedImage bufferedImage = null;
            try {
                bufferedImage = ImageIO.read(new File(errorConfig.getMainConfig().getResourcesPath() + user.getAvatar()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            assert bufferedImage != null;
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);

            avatar.setFill(new ImagePattern(image));

            if (StaticController.getMyUser().getBlackList().contains(user.getId())) {
                followIMG.setVisible(false);
                menu.setVisible(false);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText(errorConfig.getAlreadyBlocked());
                alert.show();

            } else {
                if (StaticController.getMyUser().getFollowings().contains(user.getId())) {
                    followIMG.setImage(new Image(imageConfig.getUnfollow()));
                } else {
                    followIMG.setImage(new Image(imageConfig.getFollow()));
                }
            }

            lockIMG.setVisible(user.isPrivate());
            muteIMG.setVisible(StaticController.getMyUser().getMuteList().contains(user.getId()));

            if (user.getBiography() == null) {
                bioLabel.setText(":)))");
            } else {
                bioLabel.setText(user.getBiography());
            }

            if (StaticController.getMyUser().getFollowers().contains(user.getId())) {
                statusLabel.setText(errorConfig.getFollowsYou());
            } else statusLabel.setText(" ");


        }

    }
}

