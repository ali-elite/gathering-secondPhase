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
import javafx.scene.control.*;
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
    private TabPane mainTabPane;
    @FXML
    private Tab gatherTab, timelineTab, exploreTab, chatsTab, setingTab;
    @FXML
    private Button editBtn, blackListBtn, newThoughtBtn, notifBtn;
    @FXML
    private ScrollPane gatherScroll, timeLineScroll, exploreScroll;
    @FXML
    private ToolBar bar;

    private static Tab selected;

    private static final ArrayList<Pane> thoughts = new ArrayList<>();
    private MainMenuListener mainMenuListener = new MainMenuListener(this);


    public Tab getSelected() {
        return selected;
    }

    public static void setSelected(Tab selected) {
        Mainmenu.selected = selected;

    }

    public ScrollPane getGatherScroll() {
        return gatherScroll;
    }

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

        if (selected == null) {
            setSelected(gatherTab);
        }

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

    public void showNotif(ActionEvent event) throws IOException {
        mainMenuListener.eventOccurred(new MainMenuEvent(this, "notif", null));
    }



    public void timeLineUpdate(Event event) {

        setSelected(timelineTab);


        try {
            mainMenuListener.eventOccurred(new MainMenuEvent(this, "timeLineThought", null));
        } catch (IOException e) {
            e.printStackTrace();
        }

        VBox vbox = new VBox();

        for (Pane p : thoughts) {
            vbox.getChildren().add(p);
        }

        timeLineScroll.setContent(vbox);

    }

    public void exploreUpdate(Event event) {

        setSelected(exploreTab);


        try {
            mainMenuListener.eventOccurred(new MainMenuEvent(this, "exploreThought", null));
        } catch (IOException e) {
            e.printStackTrace();
        }

        VBox vbox = new VBox();

        for (Pane p : thoughts) {
            vbox.getChildren().add(p);
        }

        exploreScroll.setContent(vbox);

    }

    public void gatherUpdate(Event event) {

        setSelected(gatherTab);


        try {
            mainMenuListener.eventOccurred(new MainMenuEvent(this, "gatherThought", null));
        } catch (IOException e) {
            e.printStackTrace();
        }

        VBox gatherVBox = new VBox();

        gatherVBox.getChildren().add(bar);

        try {
            gatherVBox.getChildren().add(shapeProfile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Pane p : thoughts) {
            gatherVBox.getChildren().add(p);
        }

        gatherScroll.setContent(gatherVBox);

    }




}
