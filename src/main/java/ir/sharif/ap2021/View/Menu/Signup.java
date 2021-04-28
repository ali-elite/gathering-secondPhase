package ir.sharif.ap2021.View.Menu;

import ir.sharif.ap2021.Controller.StaticController;
import ir.sharif.ap2021.Event.MainMenuEvent;
import ir.sharif.ap2021.Event.RegistrationFormEvent;
import ir.sharif.ap2021.Listener.FormListener;
import ir.sharif.ap2021.Listener.MainMenuListener;
import ir.sharif.ap2021.Listener.RegistrationFormListener;
import ir.sharif.ap2021.Validation.AuthenticationException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Signup {

    private FormListener formListener;
    @FXML
    private TextField firstNameTF;
    @FXML
    private TextField lastNameTf;
    @FXML
    private TextField userNameTF;
    @FXML
    private TextField passwordTF;
    @FXML
    private TextField emailTF;
    @FXML
    private TextField repeatTF;
    @FXML
    private Label myLabel;
    @FXML
    private Button subBtn,loginBtn;


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
        if (passwordTF.getText().equals("")) {
            isReady = false;
            myLabel.setText("please enter your password!");
        }
        if (emailTF.getText().equals("")) {
            isReady = false;
            myLabel.setText("please enter your email address!");
        }
        if (repeatTF.getText().equals("")) {
            isReady = false;
            myLabel.setText("please repeat your password!");
        }
        if (!repeatTF.getText().equals(passwordTF.getText())) {
            isReady = false;
            myLabel.setText("passwords don't match!");
        }
        if (!(emailTF.getText().length() > 6) || !emailTF.getText().contains("@") || !emailTF.getText().endsWith(".com")) {
            isReady = false;
            myLabel.setText("please enter a valid email address!");
        }

        boolean check;
        if (isReady) {
            try {
                RegistrationFormEvent registrationFormEvent =
                        new RegistrationFormEvent(this,
                                firstNameTF.getText(),
                                lastNameTf.getText(),
                                userNameTF.getText(),
                                emailTF.getText(),
                                passwordTF.getText());

                formListener = new RegistrationFormListener();
                formListener.eventOccurred(registrationFormEvent);
                check = true;
            } catch (AuthenticationException e) {
                myLabel.setText(e.getMessage());
                check = false;
            }

            if (check) {

                StaticController.setMyStage(((Stage) subBtn.getScene().getWindow()));
                MainMenuListener mainMenuListener = new MainMenuListener();
                mainMenuListener.eventOccurred(new MainMenuEvent(this, "load", userNameTF.getText(), new Mainmenu()));;

            }


        }


    }


    public void login(ActionEvent event) throws IOException {

        Stage stage = (Stage) loginBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmls/login.fxml")));
        Scene scene = new Scene(root);

        stage.setScene(scene);

    }
}



