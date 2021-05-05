package ir.sharif.ap2021.View.ModelView;

import ir.sharif.ap2021.Controller.StaticController;
import ir.sharif.ap2021.Event.MessageEvent;
import ir.sharif.ap2021.Listener.MessageListener;
import ir.sharif.ap2021.Model.Chat.Message;
import ir.sharif.ap2021.Model.User.User;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MessageView implements Initializable {

    MessageListener messageListener = new MessageListener();

    private Message message;
    private User sender;

    @FXML
    private Circle avatar;
    @FXML
    private Label textLabel,forwardLabel,userLabel;
    @FXML
    private Polygon triangle;
    @FXML
    private AnchorPane textPane;
    @FXML
    private ImageView messageImg;
    @FXML
    private MenuBar menuBar;


    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }





    @Override
    public void initialize(URL location, ResourceBundle resources) {

        textLabel.setText(message.getText());
        userLabel.setText(sender.getUserName());
        forwardLabel.setVisible(message.isForwarded());

        if(sender.getId() == StaticController.getMyUser().getId()){
            textPane.setStyle("-fx-background-color: lime");
            menuBar.setStyle("-fx-background-color: lime");
            triangle.setFill(Color.LIME);
            triangle.setStroke(Color.LIME);
        }

        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File("src/main/resources" + sender.getAvatar()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert bufferedImage != null;
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);

        avatar.setFill(new ImagePattern(image));


        BufferedImage bI = null;
        try {
            bI = ImageIO.read(new File("src/main/resources" + message.getImage()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert bI != null;
        Image im = SwingFXUtils.toFXImage(bI, null);

        messageImg.setImage(im);

    }

    public void seen(MouseEvent mouseEvent) {

        messageListener.eventOccurred(new MessageEvent(this,"seen",message));

    }




    public void editMessage(ActionEvent event) {
    }
    public void deleteMessage(ActionEvent event) {
    }


}
