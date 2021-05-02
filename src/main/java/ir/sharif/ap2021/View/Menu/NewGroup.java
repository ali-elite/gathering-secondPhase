package ir.sharif.ap2021.View.Menu;

import ir.sharif.ap2021.Controller.StaticController;
import ir.sharif.ap2021.Event.NewGroupEvent;
import ir.sharif.ap2021.Listener.NewGroupListener;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class NewGroup implements Initializable {

    NewGroupListener newGroupListener = new NewGroupListener();

    private static ArrayList<String> users;
    private String selectedUser;

    @FXML
    private ListView<String> selectedList, followerList;
    @FXML
    private TextField nameText;


    public static void setUsers(ArrayList<String> users) {
        NewGroup.users = users;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        followerList.getItems().addAll(users);


        followerList.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                selectedUser = followerList.getSelectionModel().getSelectedItem();
                if (!selectedList.getItems().contains(selectedUser)) {

                    Platform.runLater(() -> {
                        if (selectedUser != null) {
                            selectedList.getItems().add(selectedUser);
                            followerList.getItems().remove(selectedUser);
                            followerList.getSelectionModel().select(-1);
                        }
                    });
                }


            }
        });


    }


    public void createGroup(ActionEvent event) {

        ArrayList<String> names = new ArrayList<>(selectedList.getItems());

        if (nameText.getText() != null) {
            newGroupListener.eventOccurred(new NewGroupEvent(this, "makeGroup", names, nameText.getText()));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Group Created Successfully");
            alert.showAndWait();

        } else {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Please Enter a name");
            alert.showAndWait();

        }


    }


    public void back(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmls/mainmenu.fxml")));
        Scene scene = new Scene(root);
        StaticController.getMyStage().setScene(scene);


    }

    public void resetList(ActionEvent event) {

        followerList.getItems().clear();
        selectedList.getItems().clear();
        initialize(null, null);
    }

}
