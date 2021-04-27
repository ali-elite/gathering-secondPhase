package ir.sharif.ap2021;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {



    @FXML
    private Button signBtn,loginBtn;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmls/app.fxml")));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("/images/logo1.jpg"));
        primaryStage.show();
    }


    public void handleButtonAction(ActionEvent event) throws IOException {

        Stage stage;
        Parent root;

        if(event.getSource()==signBtn){

            stage = (Stage) signBtn.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/fxmls/signup.fxml"));
        }
        else{
            stage = (Stage) loginBtn.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/fxmls/login.fxml"));
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

}
