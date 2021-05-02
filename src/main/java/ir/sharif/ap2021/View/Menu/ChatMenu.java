package ir.sharif.ap2021.View.Menu;

import ir.sharif.ap2021.Controller.StaticController;
import ir.sharif.ap2021.Event.ChatEvent;
import ir.sharif.ap2021.Listener.ChatListener;
import ir.sharif.ap2021.Model.Chat.Chat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class ChatMenu implements Initializable {


    ChatListener chatListener = new ChatListener(null);

    private static Chat chat;

    @FXML
    private ScrollPane screenScroll;
    @FXML
    private TextArea textField;
    @FXML
    private ToolBar bar;

    private static final ArrayList<Pane> messages = new ArrayList<>();


    public void send(ActionEvent event) throws IOException {

        chatListener.eventOccurred(new ChatEvent(this, "send", chat, textField.getText()));
        textField.setText(null);
        initialize(null, null);


    }

    public void attach(ActionEvent event) {


    }

    public Chat getChat() {
        return chat;
    }

    public static void setChat(Chat chat) {
        ChatMenu.chat = chat;
    }

    public static ArrayList<Pane> getMessages() {
        return messages;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            chatListener.eventOccurred(new ChatEvent(this, "loadChats", chat, null));
        } catch (IOException e) {
            e.printStackTrace();
        }

        VBox vbox = new VBox();
        vbox.getChildren().add(bar);

        for (Pane p : messages) {
            vbox.getChildren().add(p);
        }

        screenScroll.setContent(vbox);

    }


    public void back(ActionEvent event) throws IOException {


        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmls/mainmenu.fxml")));
        Scene scene = new Scene(root);
        StaticController.getMyStage().setScene(scene);


    }
}
