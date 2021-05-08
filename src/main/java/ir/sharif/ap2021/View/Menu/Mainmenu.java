package ir.sharif.ap2021.View.Menu;

import ir.sharif.ap2021.Config.ErrorConfig;
import ir.sharif.ap2021.Config.FxmlConfig;
import ir.sharif.ap2021.Config.ItemConfig;
import ir.sharif.ap2021.Controller.StaticController;
import ir.sharif.ap2021.Event.MainMenuEvent;
import ir.sharif.ap2021.Event.PrivacyEvent;
import ir.sharif.ap2021.Event.UserSelectionEvent;
import ir.sharif.ap2021.Listener.MainMenuListener;
import ir.sharif.ap2021.Listener.UserSelectionListener;
import ir.sharif.ap2021.Validation.AuthenticationException;
import ir.sharif.ap2021.View.ModelView.Profile;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;


public class Mainmenu implements Initializable {

    ItemConfig itemConfig = new ItemConfig();
    ErrorConfig errorConfig = new ErrorConfig();
    FxmlConfig fxmlConfig = new FxmlConfig();


    @FXML
    private TabPane mainTabPane;
    @FXML
    private Tab gatherTab, timelineTab, exploreTab, chatsTab, setingTab;
    @FXML
    private Button editBtn, blackListBtn, newThoughtBtn, notifBtn;
    @FXML
    private ScrollPane gatherScroll, timeLineScroll, exploreScroll, chatScroll;
    @FXML
    private ToolBar bar, categoryBar;
    @FXML
    private AnchorPane searchPane;
    @FXML
    private TextField searchTextField, password, repeat;
    @FXML
    private ChoiceBox<String> privacy;
    @FXML
    private CheckBox diactive, privateCheck;

    private static int selected;

    private static final ArrayList<Pane> thoughts = new ArrayList<>();
    private MainMenuListener mainMenuListener = new MainMenuListener(this);
    private UserSelectionListener userSelectionListener = new UserSelectionListener();


    public Mainmenu() throws IOException {
    }


    public static int getSelected() {
        return selected;
    }

    public static void setSelected(int selected) {
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

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlConfig.getProfile()));
        loader.setController(profile);

        return loader.load();
    }

    public void show() throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlConfig.getMainmenu())));
        Scene scene = new Scene(root);
        StaticController.getMyStage().setScene(scene);

    }


    public void timeLineUpdate(Event event) {

        setSelected(1);

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

        setSelected(2);

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

        setSelected(0);


        try {
            mainMenuListener.eventOccurred(new MainMenuEvent(this, "gatherThought", null));
        } catch (IOException e) {
            e.printStackTrace();
        }

        VBox gatherVBox = new VBox();

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

        setSelected(3);

        try {
            mainMenuListener.eventOccurred(new MainMenuEvent(this, "chats", null));
        } catch (IOException e) {
            e.printStackTrace();
        }

        VBox vbox = new VBox();

        for (Pane p : thoughts) {
            vbox.getChildren().add(p);
        }

        chatScroll.setContent(vbox);


    }

    public void settingUpdate(Event event) {

        setSelected(4);

        String[] items = {itemConfig.getAnyOne(), itemConfig.getNoOne(), itemConfig.getJustFollowers()};
        privacy.getItems().addAll(items);

        diactive.setSelected(!StaticController.getMyUser().isActive());
    }


    public void makeThought(ActionEvent event) throws IOException {

        Stage stage = (Stage) newThoughtBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlConfig.getNewThought())));
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

    public void editProfile(ActionEvent event) throws IOException {

        Stage stage = (Stage) editBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlConfig.getEditProfile())));
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

    public void showNotif(ActionEvent event) throws IOException {
        mainMenuListener.eventOccurred(new MainMenuEvent(this, "notif", null));
    }

    public void showBlackList(ActionEvent event) throws IOException {

        if (StaticController.getMyUser().getBlackList().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(errorConfig.getEmptyBlacklist());
            alert.showAndWait();

        } else {
            mainMenuListener.eventOccurred(new MainMenuEvent(this, "blacklist", null));
        }


    }


    public void doSearch(ActionEvent event) {

        if (searchTextField.getText().equals(StaticController.getMyUser().getUserName())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(errorConfig.getSearchYourself());
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


    public void makeGroup(ActionEvent event) throws IOException {

        if (StaticController.getMyUser().getFollowers().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(errorConfig.getEmptyFollower());
            alert.showAndWait();
        } else {
            mainMenuListener.eventOccurred(new MainMenuEvent(this, "group", null));
        }


    }

    public void groupMessage(ActionEvent event) throws IOException {

        if (StaticController.getMyUser().getFollowers().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(errorConfig.getEmptyFollower());
            alert.showAndWait();
        } else {
            mainMenuListener.eventOccurred(new MainMenuEvent(this, "groupMessage", null));
        }

    }


    public void changePassword(ActionEvent event) throws IOException {

        if (!password.getText().equals(repeat.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(errorConfig.getMatchPassword());
            alert.showAndWait();
        } else if (password.getText().equals("") | repeat.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(errorConfig.getEnterBoth());
            alert.showAndWait();
        } else {
            mainMenuListener.privacyEventOccurred(new PrivacyEvent(this, "changePassword", password.getText()
                    , null, false));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(errorConfig.getPasswordChanged());
            alert.showAndWait();
        }

    }

    public void confirmLsPrivacy(ActionEvent event) throws IOException {
        mainMenuListener.privacyEventOccurred(new PrivacyEvent(this, "lastSeen", null
                , privacy.getValue(), false));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(errorConfig.getLastSeenChanged());
        alert.showAndWait();
    }

    public void confirmActivity(ActionEvent event) throws IOException {
        mainMenuListener.privacyEventOccurred(new PrivacyEvent(this, "changeActivity", null
                , null, diactive.isSelected()));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(errorConfig.getActivityChanged());
        alert.showAndWait();
    }

    public void confirmPrivacy(ActionEvent event) throws IOException {
        mainMenuListener.privacyEventOccurred(new PrivacyEvent(this, "changePrivacy", null
                , null, privateCheck.isSelected()));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(errorConfig.getPrivacyChanged());
        alert.showAndWait();
    }

    public void deleteUser(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(errorConfig.getSureDelete());

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    mainMenuListener.eventOccurred(new MainMenuEvent(this, "delete", null));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    public void logOut(ActionEvent event) throws IOException {
        mainMenuListener.eventOccurred(new MainMenuEvent(this, "logOut", null));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (getSelected() == 0)
            mainTabPane.getSelectionModel().select(gatherTab);

        if (getSelected() == 1)
            mainTabPane.getSelectionModel().select(timelineTab);

        if (getSelected() == 2)
            mainTabPane.getSelectionModel().select(exploreTab);

        if (getSelected() == 3)
            mainTabPane.getSelectionModel().select(chatsTab);

        if (getSelected() == 4)
            mainTabPane.getSelectionModel().select(setingTab);

    }


}