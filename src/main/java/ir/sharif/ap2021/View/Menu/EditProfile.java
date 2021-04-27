package ir.sharif.ap2021.View.Menu;

import ir.sharif.ap2021.Controller.StaticController;
import ir.sharif.ap2021.Event.EditProfileEvent;
import ir.sharif.ap2021.Listener.EditProfileListener;
import ir.sharif.ap2021.Validation.AuthenticationException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
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
    @FXML
    private DatePicker birthday;
    private boolean isChanged = false;
    private EditProfileListener editProfileListener = new EditProfileListener();


    public void submit(ActionEvent event) throws IOException {

        boolean isReady = true;

        if (firstNameTF.getText().equals("")) {
            isReady = false;
            myLabel.setText("please enter your firstname!!");
        }
        if (lastNameTf.getText().equals("")) {
            isReady = false;
            myLabel.setText("please enter your lastname!!");
        }
        if (userNameTF.getText().equals("")) {
            isReady = false;
            myLabel.setText("please enter your username!");
        }

        if (emailTF.getText().equals("")) {
            isReady = false;
            myLabel.setText("please enter your email address!");
        }

        if (!(emailTF.getText().length() > 6) || !emailTF.getText().contains("@") || !emailTF.getText().endsWith(".com")) {
            isReady = false;
            myLabel.setText("please enter a valid email address!");
        }

        String s = "";
        if (isChanged) {
            s = "change";
        }

        EditProfileEvent editProfileEvent = new EditProfileEvent(this, firstNameTF.getText(),
                lastNameTf.getText(), userNameTF.getText(), phoneTF.getText(), emailTF.getText(),
                bioText.getText(), s, birthday.getValue()
        );

        try {
            editProfileListener.eventOccurred(editProfileEvent);
        } catch (AuthenticationException e) {
            myLabel.setText(e.getMessage());
            isReady = false;
        }

        if(isReady){
            myLabel.setText("Your Profile Edited Successfully ! Press Back if you are done");
        }

    }

    public void chooseFile(ActionEvent event) throws IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png"));
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {

            File avatarFile = new File("src/main/resources/Avatars/" + StaticController.getMyUser().getId() + ".png");

            if (avatarFile.exists()) {
                avatarFile.delete();
            }

            Files.copy(file.toPath(), avatarFile.toPath());
            isChanged = true;

        } else isChanged = false;


    }

    public void back(ActionEvent event) throws IOException {

        Stage stage = (Stage) backBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmls/mainmenu.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        firstNameTF.setText(StaticController.getMyUser().getFirstName());
        lastNameTf.setText(StaticController.getMyUser().getLastName());
        userNameTF.setText(StaticController.getMyUser().getUserName());
        phoneTF.setText(StaticController.getMyUser().getPhoneNumber());
        emailTF.setText(StaticController.getMyUser().getEmail());
        bioText.setText(StaticController.getMyUser().getBiography());
        birthday.setValue(StaticController.getMyUser().getBirthday());
        avatar.setFill(new ImagePattern(new Image(StaticController.getMyUser().getAvatar())));


    }


}
