package ir.sharif.ap2021.View.Menu;

import ir.sharif.ap2021.Controller.StaticController;
import ir.sharif.ap2021.Event.LoginFormEvent;
import ir.sharif.ap2021.Event.MainMenuEvent;
import ir.sharif.ap2021.Listener.LoginFormListener;
import ir.sharif.ap2021.Listener.MainMenuListener;
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

public class Login {


    @FXML
    private TextField usernameTF, passwordTF;

    @FXML
    private Label myLabel;

    @FXML
    private Button subBtn, signBtn;


    public void submit(ActionEvent event) throws IOException {

        boolean isReady = true;

        if (usernameTF.getText().equals("")) {
            isReady = false;
            myLabel.setText("please enter your username!!");
        }
        if (passwordTF.getText().equals("")) {
            isReady = false;
            myLabel.setText("please enter your password!!");
        }

        boolean check;
        if (isReady) {
            try {
                LoginFormEvent loginFormEvent =
                        new LoginFormEvent(this, usernameTF.getText(), passwordTF.getText());


                LoginFormListener loginFormListener = new LoginFormListener();
                loginFormListener.eventOccurred(loginFormEvent);
                check = true;

            } catch (AuthenticationException e) {
                myLabel.setText(e.getMessage());
                check = false;
            }

            if (check) {

                StaticController.setMyStage(((Stage) subBtn.getScene().getWindow()));
                Mainmenu mainmenu = new Mainmenu();
                StaticController.setMyMainMenu(mainmenu);
                MainMenuListener mainMenuListener = new MainMenuListener(mainmenu);
                mainMenuListener.eventOccurred(new MainMenuEvent(this, "load", usernameTF.getText()));

            }
        }
    }

    public void SignUp(ActionEvent event) throws IOException {

        Stage stage = (Stage) signBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmls/signup.fxml")));
        Scene scene = new Scene(root);

        stage.setScene(scene);
    }
}

