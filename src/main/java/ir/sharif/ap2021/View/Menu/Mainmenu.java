package ir.sharif.ap2021.View.Menu;

import ir.sharif.ap2021.Controller.StaticController;
import ir.sharif.ap2021.Event.MainMenuEvent;
import ir.sharif.ap2021.Event.UserSelectionEvent;
import ir.sharif.ap2021.Listener.MainMenuListener;
import ir.sharif.ap2021.Listener.UserSelectionListener;
import ir.sharif.ap2021.Validation.AuthenticationException;
import ir.sharif.ap2021.View.ModelView.Profile;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


public class Mainmenu {

    @FXML
    private TabPane mainTabPane;
    @FXML
    private Tab gatherTab, timelineTab, exploreTab, chatsTab, setingTab;
    @FXML
    private Button editBtn, blackListBtn, newThoughtBtn, notifBtn;
    @FXML
    private ScrollPane gatherScroll, timeLineScroll, exploreScroll,chatScroll;
    @FXML
    private ToolBar bar,categoryBar;
    @FXML
    private AnchorPane searchPane;
    @FXML
    private TextField searchTextField;


    private static Tab selected;

    private static final ArrayList<Pane> thoughts = new ArrayList<>();
    private MainMenuListener mainMenuListener = new MainMenuListener(this);
    private UserSelectionListener userSelectionListener = new UserSelectionListener();


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

        vbox.getChildren().add(searchPane);

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

    public void chatUpdate(Event event) {

        setSelected(chatsTab);

        try {
            mainMenuListener.eventOccurred(new MainMenuEvent(this, "chats", null));
        } catch (IOException e) {
            e.printStackTrace();
        }

        VBox vbox = new VBox();
        vbox.getChildren().add(categoryBar);

        for (Pane p : thoughts) {
            vbox.getChildren().add(p);
        }

        chatScroll.setContent(vbox);


    }


    public void doSearch(ActionEvent event) {

        if (searchTextField.getText().equals(StaticController.getMyUser().getUserName())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("You cannot search yourself");
            alert.showAndWait();
        } else {

            try {
                userSelectionListener.eventOccurred(new UserSelectionEvent(this, "load", searchTextField.getText(), null));

            } catch (AuthenticationException | IOException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }

    public void categoryMessage(ActionEvent event) {
    }

    public void makeGroup(ActionEvent event) throws IOException {

        if (StaticController.getMyUser().getFollowers().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Your follower list is empty!");
            alert.showAndWait();
        } else {
            mainMenuListener.eventOccurred(new MainMenuEvent(this,"group",null));
        }


    }


    public void makeCategory(ActionEvent event) throws IOException {


    }
}
