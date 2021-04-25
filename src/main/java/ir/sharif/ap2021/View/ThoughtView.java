package ir.sharif.ap2021.View;

import ir.sharif.ap2021.DB.Context;
import ir.sharif.ap2021.Event.ReloadEvent;
import ir.sharif.ap2021.Event.ThoughtChangeEvent;
import ir.sharif.ap2021.Listener.ReloadListener;
import ir.sharif.ap2021.Listener.ThoughtChangeListener;
import ir.sharif.ap2021.Model.Thought.Thought;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ThoughtView implements Initializable {

    private Thought thought;
    private ThoughtChangeListener thoughtChangeListener = new ThoughtChangeListener();
    private ReloadListener reloadListener = new ReloadListener();

    @FXML
    private Label text;
    @FXML
    private Circle avatar;
    @FXML
    private Label likes, rets, opinions, statusLabel, timeLabel;
    @FXML
    private ImageView likeIMG, retIMG;

    public void setThought(Thought thought) {
        this.thought = thought;
    }

    public Thought getThought() {
        return thought;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Context context = new Context();

        reloadListener.eventOccurred(new ReloadEvent(this, this));

        if (thought.getLikers().contains(Mainmenu.getMyUser().getId())) {
            likeIMG.setImage(new Image("/images/fillLike.png"));
        }
        if (thought.getRethoughters().contains(Mainmenu.getMyUser().getId())) {
            retIMG.setImage(new Image("/images/fillRet.png"));
        }

        timeLabel.setText(String.valueOf(thought.getLocalDateTime()));
        text.setText(thought.getText());
        likes.setText(String.valueOf(thought.getLikes()));
        rets.setText(String.valueOf(thought.getRethought()));
        opinions.setText(String.valueOf(thought.getOpinions().size()));
        avatar.setFill(new ImagePattern(new Image(context.Users.get(thought.getUser()).getAvatar())));

        if (thought.getText().equals("t")) {
            statusLabel.setText(" Shared his thought:");
        }

    }

    public void like(MouseEvent mouseEvent) throws IOException {


        ThoughtChangeEvent thoughtChangeEvent = new ThoughtChangeEvent(this, "like", thought, (Stage) text.getScene().getWindow());
        thoughtChangeListener.eventOccurred(thoughtChangeEvent);

    }

    public void ret(MouseEvent mouseEvent) throws IOException {

        ThoughtChangeEvent thoughtChangeEvent = new ThoughtChangeEvent(this, "ret", thought, (Stage) text.getScene().getWindow());
        thoughtChangeListener.eventOccurred(thoughtChangeEvent);

    }

    public void mention(MouseEvent mouseEvent) {
    }
}
