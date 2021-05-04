package ir.sharif.ap2021.View.Menu;

import ir.sharif.ap2021.Controller.StaticController;
import ir.sharif.ap2021.Event.MainMenuEvent;
import ir.sharif.ap2021.Listener.MainMenuListener;
import ir.sharif.ap2021.Model.Thought.Thought;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ForwardSelection implements Initializable {

    MainMenuListener mainMenuListener = new MainMenuListener(null);
    private static Thought thought;
    private final static ArrayList<Pane> chats = new ArrayList<>();

    @FXML
    private ScrollPane chatScroll;


    public static Thought getThought() {
        return thought;
    }

    public static void setThought(Thought thought) {
        ForwardSelection.thought = thought;
    }

    public static ArrayList<Pane> getChats() {
        return chats;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            mainMenuListener.eventOccurred(new MainMenuEvent(this, "forwards", null));
        } catch (IOException e) {
            e.printStackTrace();
        }

        VBox vbox = new VBox();

        for (Pane p : chats) {
            vbox.getChildren().add(p);
        }

        chatScroll.setContent(vbox);
    }

    public void back(ActionEvent event) throws IOException {
        StaticController.getMyMainMenu().show();
    }
}
