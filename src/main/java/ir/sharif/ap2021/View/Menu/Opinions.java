package ir.sharif.ap2021.View.Menu;

import ir.sharif.ap2021.Config.ErrorConfig;
import ir.sharif.ap2021.Config.FxmlConfig;
import ir.sharif.ap2021.Controller.StaticController;
import ir.sharif.ap2021.Event.ThoughtEvent;
import ir.sharif.ap2021.Listener.ThoughtListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class Opinions implements Initializable {

    ThoughtListener thoughtListener = new ThoughtListener(null);
    ErrorConfig errorConfig = new ErrorConfig();
    FxmlConfig fxmlConfig = new FxmlConfig();

    @FXML
    private ScrollPane Scroll;
    @FXML
    private Button mainBackBtn, backBtn;

    private static final ArrayList<Pane> opinions = new ArrayList<>();

    public Opinions() throws IOException {
    }


    public static ArrayList<Pane> getOpinions() {
        return opinions;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        VBox vbox = new VBox();

        for (Pane p : opinions) {
            vbox.getChildren().add(p);
        }

        Scroll.setContent(vbox);

    }


    public void mainBack(ActionEvent event) throws IOException {
        StaticController.getMyMainMenu().show();
    }

    public void back(ActionEvent event) throws IOException {


        if (StaticController.getLastThought() == null) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(errorConfig.getNoMoreBack());
            alert.showAndWait();

        } else {

            ThoughtEvent thoughtChangeEvent = new ThoughtEvent(this, "opinions", StaticController.getLastThought());
            thoughtListener.eventOccurred(thoughtChangeEvent);

        }
    }


    public void show() throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlConfig.getOpinions())));
        Scene scene = new Scene(root);
        StaticController.getMyStage().setScene(scene);

    }

}
