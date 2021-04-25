package ir.sharif.ap2021.Controller;

import ir.sharif.ap2021.DB.Context;
import ir.sharif.ap2021.Event.ReloadEvent;
import ir.sharif.ap2021.View.Mainmenu;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ReloadController {

    Context context;


    public ReloadController() {
        context = new Context();
    }

    public void reload(ReloadEvent e){

        if(e.getType().equals("thoughtView")){
            e.getThoughtView().setThought(context.Thoughts.get(e.getThoughtView().getThought().getId()));
        }

        if(e.getType().equals("userMain")){
            Mainmenu.setMyUser(context.Users.get(Mainmenu.getMyUser().getId()));
        }

    }

    public void reloadMain(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmls/mainmenu.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }
}
