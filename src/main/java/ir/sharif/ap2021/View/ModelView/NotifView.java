package ir.sharif.ap2021.View.ModelView;

import ir.sharif.ap2021.Model.Notification.Notification;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class NotifView implements Initializable {

    private Notification notification;

    @FXML
    private Label textLabel;
    @FXML
    private Button acceptBtn,rejectBtn,removeBtn;

    public void accept(ActionEvent event) {


//        initialize(null,null);
    }

    public void reject(ActionEvent event) {

//        initialize(null,null);
    }

    public void remove(ActionEvent event) {

//        initialize(null,null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(notification.isRequest() || notification.isAnswered()){

            acceptBtn = null;
            removeBtn = null;
            rejectBtn = null;

        }

        textLabel.setText(notification.getText());

    }


}
