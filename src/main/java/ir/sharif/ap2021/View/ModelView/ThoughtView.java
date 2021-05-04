package ir.sharif.ap2021.View.ModelView;

import ir.sharif.ap2021.Controller.StaticController;
import ir.sharif.ap2021.Event.ThoughtEvent;
import ir.sharif.ap2021.Listener.ThoughtListener;
import ir.sharif.ap2021.Model.Thought.Thought;
import ir.sharif.ap2021.Model.User.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class ThoughtView implements Initializable {


    ThoughtListener thoughtListener = new ThoughtListener(this);

    private User mainUser;
    private User ownerUser;
    private User doedUser;
    private Thought thought;


    @FXML
    private Label text;
    @FXML
    private Circle avatar;
    @FXML
    private Label likes, rets, opinions, statusLabel, timeLabel;
    @FXML
    private ImageView likeIMG, retIMG;
    @FXML
    private TextArea replyText;
    @FXML
    private Button replyBtn, insertBtn;
    @FXML
    private AnchorPane replyPane;


    private final ArrayList<Pane> comments = new ArrayList<>();


    public ArrayList<Pane> getComments() {
        return comments;
    }

    public User getMainUser() {
        return mainUser;
    }

    public void setMainUser(User mainUser) {
        this.mainUser = mainUser;
    }

    public User getOwnerUser() {
        return ownerUser;
    }

    public void setOwnerUser(User ownerUser) {
        this.ownerUser = ownerUser;
    }

    public User getDoedUser() {
        return doedUser;
    }

    public void setDoedUser(User doedUser) {
        this.doedUser = doedUser;
    }

    public Thought getThought() {
        return thought;
    }

    public void setThought(Thought thought) {
        this.thought = thought;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (thought.getLikers().contains(StaticController.getMyUser().getId())) {
            likeIMG.setImage(new Image("/images/fillLike.png"));
        } else {
            likeIMG.setImage(new Image("/images/like.png"));
        }

        if (thought.getRethoughters().contains(StaticController.getMyUser().getId())) {
            retIMG.setImage(new Image("/images/fillRet.png"));
        } else {
            retIMG.setImage(new Image("/images/ret.png"));
        }

        timeLabel.setText(String.valueOf(thought.getLocalDateTime()));
        text.setText(thought.getText());
        likes.setText(String.valueOf(thought.getLikes()));
        rets.setText(String.valueOf(thought.getRethought()));
        opinions.setText(String.valueOf(thought.getOpinions().size()));
        avatar.setFill(new ImagePattern(new Image(ownerUser.getAvatar())));

        if (thought.getType().equals("t")) {
            statusLabel.setText(" Shared his thought:");
        } else {
            statusLabel.setText(" Replied to: " + "@" + doedUser.getUserName());
        }

        replyBtn.setVisible(false);
        replyText.setVisible(false);
        insertBtn.setVisible(false);


    }

    public void reload() {
        initialize(null, null);
    }


    public void like(MouseEvent mouseEvent) throws IOException {


        ThoughtEvent thoughtChangeEvent = new ThoughtEvent(this, "like", thought);
        thoughtListener.eventOccurred(thoughtChangeEvent);


    }

    public void ret(MouseEvent mouseEvent) throws IOException {

        ThoughtEvent thoughtChangeEvent = new ThoughtEvent(this, "ret", thought);
        thoughtListener.eventOccurred(thoughtChangeEvent);

    }

    public void mention(MouseEvent mouseEvent) throws IOException {

        replyBtn.setVisible(!replyBtn.isVisible());
        replyText.setVisible(!replyText.isVisible());
        insertBtn.setVisible(!insertBtn.isVisible());

    }


    public void saveMessage(ActionEvent event) throws IOException {

        ThoughtEvent thoughtChangeEvent = new ThoughtEvent(this, "saveMessage", thought);
        thoughtListener.eventOccurred(thoughtChangeEvent);

    }

    public void forwardMessage(ActionEvent event) throws IOException {

        ThoughtEvent thoughtChangeEvent = new ThoughtEvent(this, "forwardMessage", thought);
        thoughtListener.eventOccurred(thoughtChangeEvent);
    }

    public void muteAuthor(ActionEvent event) throws IOException {

        if (StaticController.getMyUser().getMuteList().contains(thought.getUser())) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("You have already muted the author");
            alert.showAndWait();

        } else {

            ThoughtEvent thoughtChangeEvent = new ThoughtEvent(this, "muteAuthor", thought);
            thoughtListener.eventOccurred(thoughtChangeEvent);
        }

    }

    public void reportSpam(ActionEvent event) throws IOException {

        ThoughtEvent thoughtChangeEvent = new ThoughtEvent(this, "spam", thought);
        thoughtListener.eventOccurred(thoughtChangeEvent);

    }

    public void authorProfile(ActionEvent event) throws IOException {

        if (mainUser.getId() == ownerUser.getId()) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("This is your thought");
            alert.showAndWait();

        }

        ThoughtEvent thoughtChangeEvent = new ThoughtEvent(this, "profile", thought);
        thoughtListener.eventOccurred(thoughtChangeEvent);

    }

    public void showOpinions(ActionEvent event) throws IOException {

        if (thought.getOpinions().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("No Opinion");
            alert.showAndWait();

        } else {

            ThoughtEvent thoughtChangeEvent = new ThoughtEvent(this, "opinions", thought);
            thoughtListener.eventOccurred(thoughtChangeEvent);
        }
    }



    public void reply(ActionEvent event) throws IOException {

        ThoughtEvent thoughtChangeEvent = new ThoughtEvent(this, "mention", thought);
        thoughtChangeEvent.setMentionText(replyText.getText());
        thoughtListener.eventOccurred(thoughtChangeEvent);

    }

}
