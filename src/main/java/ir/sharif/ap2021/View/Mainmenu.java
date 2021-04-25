package ir.sharif.ap2021.View;

import ir.sharif.ap2021.DB.Context;
import ir.sharif.ap2021.Event.ReloadEvent;
import ir.sharif.ap2021.Listener.ReloadListener;
import ir.sharif.ap2021.Model.Thought.Thought;
import ir.sharif.ap2021.Model.User.User;
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
import java.util.Objects;
import java.util.ResourceBundle;

public class Mainmenu implements Initializable {

    private static User myUser;
    private ReloadListener reloadListener = new ReloadListener();

    @FXML
    private Tab gatherTab, timelineTab, exploreTab, chatsTab, setingTab;
    @FXML
    private Button editBtn, blackListBtn, newThoughtBtn;
    @FXML
    private ScrollPane gatherScroll;
    @FXML
    private ToolBar bar;


    public static User getMyUser() {
        return myUser;
    }


    public static void setMyUser(User myUser) {
        Mainmenu.myUser = myUser;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refresh();
    }

    public Pane shapeThought(int i) throws IOException {

        Context context = new Context();
        Thought thought = context.Thoughts.get(i);

        ThoughtView thoughtView = new ThoughtView();
        thoughtView.setThought(thought);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/thought.fxml"));
        loader.setController(thoughtView);


        return loader.load();
    }

    public Pane shapeProfile() throws IOException {

        Profile profile = new Profile();
        profile.setUser(Mainmenu.getMyUser());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/profile.fxml"));
        loader.setController(profile);


        return loader.load();

    }

    public void update(Event event) {
        refresh();
    }

    public void refresh() {

        reloadListener.eventOccurred(new ReloadEvent(this,myUser));

        VBox vBox = new VBox();
        vBox.getChildren().add(bar);

        try {
            vBox.getChildren().add(shapeProfile());
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (int i = 0; i < myUser.getThoughts().size(); i++) {

            try {
                Pane p = shapeThought(myUser.getThoughts().get(myUser.getThoughts().size() - 1 - i));
                vBox.getChildren().add(p);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        gatherScroll.setContent(vBox);
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
}
