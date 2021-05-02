package ir.sharif.ap2021.View.Menu;

import ir.sharif.ap2021.Controller.StaticController;
import ir.sharif.ap2021.Event.NotifEvent;
import ir.sharif.ap2021.Listener.NotifListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;


public class Notifications implements Initializable {

    private NotifListener notifListener = new NotifListener(null);
    private static final ArrayList<Pane> notifViews = new ArrayList<>();

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private ToolBar bar;
    @FXML
    private Button backBtn;

    public static ArrayList<Pane> getNotifViews() {
        return notifViews;
    }

    public void show() throws IOException {

        Stage stage = (Stage) StaticController.getMyStage().getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmls/notifications.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }


    public void back(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmls/mainmenu.fxml")));
        Scene scene = new Scene(root);
        StaticController.getMyStage().setScene(scene);


    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        VBox vBox = new VBox();
        vBox.getChildren().add(bar);

        try {
            notifListener.eventOccurred(new NotifEvent(this, "load"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Pane p : notifViews) {
            vBox.getChildren().add(p);
        }

        scrollPane.setContent(vBox);

    }
}
