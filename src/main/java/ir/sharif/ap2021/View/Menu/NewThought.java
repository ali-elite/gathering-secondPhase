package ir.sharif.ap2021.View.Menu;

import ir.sharif.ap2021.Controller.StaticController;
import ir.sharif.ap2021.Event.ShareThoughtEvent;
import ir.sharif.ap2021.Listener.ShareThoughtListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class NewThought {

    ShareThoughtListener shareThoughtListener;

    @FXML
    private TextArea text;
    @FXML
    private Button shareBtn, backBtn, photoBtn;

    public void share(ActionEvent event) throws IOException {

        ShareThoughtEvent e = new ShareThoughtEvent(this, text.getText(), StaticController.getMyUser().getId());
        ShareThoughtListener shareThoughtListener = new ShareThoughtListener();
        shareThoughtListener.eventOccurred(e);

        Stage stage = (Stage) backBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmls/mainmenu.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

    public void putPhoto(ActionEvent event) {
    }

    public void back(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmls/mainmenu.fxml")));
        Scene scene = new Scene(root);
        StaticController.getMyStage().setScene(scene);

    }
}
