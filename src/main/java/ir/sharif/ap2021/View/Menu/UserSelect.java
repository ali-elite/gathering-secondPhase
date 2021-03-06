package ir.sharif.ap2021.View.Menu;

import ir.sharif.ap2021.Config.FxmlConfig;
import ir.sharif.ap2021.Controller.StaticController;
import ir.sharif.ap2021.Event.UserSelectionEvent;
import ir.sharif.ap2021.Listener.UserSelectionListener;
import ir.sharif.ap2021.Validation.AuthenticationException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class UserSelect implements Initializable {

    FxmlConfig fxmlConfig = new FxmlConfig();

    private static String[] users;
    private static String type;
    private String selectedUser;
    private UserSelectionListener userSelectionListener = new UserSelectionListener();

    @FXML
    private ListView<String> listview;

    @FXML
    private Button backBtn;

    public UserSelect() throws IOException {
    }

    public void show() throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlConfig.getUserSelection())));
        Scene scene = new Scene(root);
        StaticController.getMyStage().setScene(scene);
    }

    public String[] getUsers() {
        return users;
    }

    public static void setUsers(String[] users) {
        UserSelect.users = users;
    }

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        UserSelect.type = type;
    }


    public void back(ActionEvent event) throws IOException {
        StaticController.getMyMainMenu().show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        listview.getItems().addAll(users);

        listview.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                selectedUser = listview.getSelectionModel().getSelectedItem();
                try {
                    userSelectionListener.eventOccurred(new UserSelectionEvent(this,"load",selectedUser,type));
                } catch (IOException | AuthenticationException e) {
                    e.printStackTrace();
                }

            }
        });

    }
}
