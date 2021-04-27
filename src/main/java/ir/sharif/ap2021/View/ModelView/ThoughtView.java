package ir.sharif.ap2021.View.ModelView;

import ir.sharif.ap2021.Controller.StaticController;
import ir.sharif.ap2021.Event.ThoughtEvent;
import ir.sharif.ap2021.Listener.ThoughtListener;
import ir.sharif.ap2021.Model.Thought.Thought;
import ir.sharif.ap2021.Model.User.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ThoughtView implements Initializable {


    private ThoughtListener thoughtListener = new ThoughtListener();

    private User mainUser;
    private User ownerUser;
    private Thought thought;

    @FXML
    private Label text;
    @FXML
    private Circle avatar;
    @FXML
    private Label likes, rets, opinions, statusLabel, timeLabel;
    @FXML
    private ImageView likeIMG, retIMG;

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

        if (thought.getText().equals("t")) {
            statusLabel.setText(" Shared his thought:");
        }

    }

    public void like(MouseEvent mouseEvent) throws IOException {


        ThoughtEvent thoughtChangeEvent = new ThoughtEvent(this, "like", thought, this);
        thoughtListener.eventOccurred(thoughtChangeEvent);


    }

    public void ret(MouseEvent mouseEvent) throws IOException {

        ThoughtEvent thoughtChangeEvent = new ThoughtEvent(this, "ret", thought, this);
        thoughtListener.eventOccurred(thoughtChangeEvent);

    }

    public void mention(MouseEvent mouseEvent) {

    }


    public void reload() {
        initialize(null, null);
    }
}