package ir.sharif.ap2021.View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class EditProfile implements Initializable {

    @FXML
    private TextField firstNameTF, lastNameTf, userNameTF,
            phoneTF, emailTF;
    @FXML
    private Label myLabel;
    @FXML
    private Button subBtn, chooseBtn, backBtn;
    @FXML
    private Circle avatar;
    @FXML
    private TextArea bioText;


    public void submit(ActionEvent event) {



    }

    public void chooseFile(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files",".png"));
        File file = fileChooser.showOpenDialog(null);

        //file abs path image
    }

    public void back(ActionEvent event) throws IOException {

        Stage stage = (Stage) backBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmls/mainmenu.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        firstNameTF.setText(Mainmenu.getMyUser().getFirstName());
        lastNameTf.setText(Mainmenu.getMyUser().getLastName());
        userNameTF.setText(Mainmenu.getMyUser().getUserName());
        phoneTF.setText(Mainmenu.getMyUser().getPhoneNumber());
        emailTF.setText(Mainmenu.getMyUser().getEmail());
        bioText.setText(Mainmenu.getMyUser().getBiography());

    }


}
