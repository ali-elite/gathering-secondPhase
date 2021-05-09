package ir.sharif.ap2021;

import ir.sharif.ap2021.Config.FxmlConfig;
import ir.sharif.ap2021.Config.ImageConfig;
import ir.sharif.ap2021.DB.Directories;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {


    private static final Logger logger = LogManager.getLogger(App.class);
    private FxmlConfig fxmlConfig = new FxmlConfig();
    private ImageConfig imageConfig = new ImageConfig();

    @FXML
    private Button signBtn,loginBtn;

    public App() throws IOException {
    }

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws IOException {

        Directories directories = new Directories();
        directories.setDirectoires();

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlConfig.getApp())));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(imageConfig.getLogo()));
        primaryStage.show();

        logger.info("App Started");
    }


    public void handleButtonAction(ActionEvent event) throws IOException {

        Stage stage;
        Parent root;

        if(event.getSource()==signBtn){

            stage = (Stage) signBtn.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlConfig.getSignup())));
        }
        else{
            stage = (Stage) loginBtn.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlConfig.getLogin())));
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

}
