package ir.sharif.ap2021.View.Menu;

import ir.sharif.ap2021.Controller.StaticController;
import ir.sharif.ap2021.Event.MainMenuEvent;
import ir.sharif.ap2021.Listener.MainMenuListener;
import ir.sharif.ap2021.View.ModelView.Profile;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;


public class Mainmenu implements Initializable {

    @FXML
    private Tab gatherTab, timelineTab, exploreTab, chatsTab, setingTab;
    @FXML
    private Button editBtn, blackListBtn, newThoughtBtn,notifBtn;
    @FXML
    private ScrollPane gatherScroll;
    @FXML
    private ToolBar bar;


    private static final ArrayList<Pane> thoughts = new ArrayList<>();
    private MainMenuListener mainMenuListener = new MainMenuListener();



    public static ArrayList<Pane> getThoughts() {
        return thoughts;
    }

    public Pane shapeProfile() throws IOException {

        Profile profile = new Profile();
        profile.setUser(StaticController.getMyUser());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/profile.fxml"));
        loader.setController(profile);

        return loader.load();
    }

    public void show() throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmls/mainmenu.fxml")));
        Scene scene = new Scene(root);
        StaticController.getMyStage().setScene(scene);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        VBox vBox = new VBox();
        vBox.getChildren().add(bar);

        try {
            mainMenuListener.eventOccurred(new MainMenuEvent(this, "thought", null, this));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            vBox.getChildren().add(shapeProfile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Pane p : thoughts) {
            vBox.getChildren().add(p);
        }

        gatherScroll.setContent(vBox);
    }

    public void update(Event event) throws IOException {
        initialize(null,null);
    }

    public void makeThought(ActionEvent event) throws IOException {

        Stage stage = (Stage) newThoughtBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmls/newThought.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

    public void editProfile(ActionEvent event) throws IOException {

        Stage stage = (Stage) editBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmls/editProfile.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

    public void showNotif(ActionEvent event) {

    }

}
