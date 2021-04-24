package ir.sharif.ap2021.View;

import ir.sharif.ap2021.DB.Context;
import ir.sharif.ap2021.Model.Thought.Thought;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;


public class ThoughtView implements Initializable {

    private Context context = new Context();
    private Thought thought;
    @FXML
    private Label text;
    @FXML
    private Circle avatar;
    @FXML
    private Label likes, rets, opinions;

    public void setThought(Thought thought) {
        this.thought = thought;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        thought = context.Thoughts.get(thought.getId());

        text.setText(thought.getText());
        likes.setText(String.valueOf(thought.getLikes()));
        rets.setText(String.valueOf(thought.getRethought()));
        opinions.setText(String.valueOf(thought.getOpinions().size()));

    }

    public void like(MouseEvent mouseEvent) {

        if (thought.getLikers().contains(Mainmenu.getMyUser().getId())) {
            thought.minusLike();
            thought.getLikers().remove((Integer) Mainmenu.getMyUser().getId());
        } else {
            thought.addLike();
            thought.getLikers().add(Mainmenu.getMyUser().getId());
        }

         context.Thoughts.update(thought);

    }

    public void ret(MouseEvent mouseEvent) {

        if (thought.getRethoughters().contains(Mainmenu.getMyUser().getId())) {
            thought.minusRethought();
            thought.getRethoughters().remove(Mainmenu.getMyUser().getId());
        } else {
            thought.addRethought();
            thought.getRethoughters().add(Mainmenu.getMyUser().getId());
        }

    }

    public void mention(MouseEvent mouseEvent) {
    }
}
