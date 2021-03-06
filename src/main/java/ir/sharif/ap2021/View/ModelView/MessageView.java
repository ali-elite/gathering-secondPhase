package ir.sharif.ap2021.View.ModelView;

import ir.sharif.ap2021.Config.ErrorConfig;
import ir.sharif.ap2021.Config.ItemConfig;
import ir.sharif.ap2021.Controller.StaticController;
import ir.sharif.ap2021.Event.MessageEvent;
import ir.sharif.ap2021.Listener.MessageListener;
import ir.sharif.ap2021.Model.Chat.Message;
import ir.sharif.ap2021.Model.User.User;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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

    ItemConfig itemConfig = new ItemConfig();
    ErrorConfig errorConfig = new ErrorConfig();
    MessageListener messageListener = new MessageListener();

    private Message message;
    private User sender;

    @FXML
    private Circle avatar;
    @FXML
    private Label textLabel, forwardLabel, userLabel;
    @FXML
    private Polygon triangle;
    @FXML
    private AnchorPane textPane;
    @FXML
    private ImageView messageImg;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Button editBtn;
    @FXML
    private TextArea editTextArea;

    public MessageView() throws IOException {
    }


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

        userLabel.setText(sender.getUserName());
        forwardLabel.setVisible(message.isForwarded());

        if (message.isDeleted()) {
            textLabel.setText(itemConfig.getDeleteText());
            textPane.setStyle(itemConfig.getDeleteStyle());
            menuBar.setStyle(itemConfig.getDeleteStyle());
            triangle.setFill(Color.valueOf(itemConfig.getDeleteColor()));
            triangle.setStroke(Color.valueOf(itemConfig.getDeleteColor()));
            menuBar.setVisible(false);
            messageImg.setVisible(false);
        } else {
            textLabel.setText(message.getText());
            if (sender.getId() == StaticController.getMyUser().getId()) {
                textPane.setStyle(itemConfig.getOwnStyle());
                menuBar.setStyle(itemConfig.getOwnStyle());
                triangle.setFill(Color.valueOf(itemConfig.getOwnColor()));
                triangle.setStroke(Color.valueOf(itemConfig.getOwnColor()));
                messageImg.setVisible(true);
            }
        }

        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File(errorConfig.getMainConfig().getResourcesPath() + sender.getAvatar()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert bufferedImage != null;
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);

        avatar.setFill(new ImagePattern(image));


        if (message.getImage() != null) {
            BufferedImage bI = null;
            try {
                bI = ImageIO.read(new File(errorConfig.getMainConfig().getResourcesPath() + message.getImage()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            assert bI != null;
            Image im = SwingFXUtils.toFXImage(bI, null);

            messageImg.setImage(im);
        }


        editBtn.setVisible(false);
        editTextArea.setVisible(false);
    }


    public void seen(MouseEvent mouseEvent) {

        messageListener.eventOccurred(new MessageEvent(this, "seen", message));

    }

    public void editMessage(ActionEvent event) {

        if (message.isForwarded()) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(errorConfig.getEditForwarded());
            alert.showAndWait();


        } else {

            editBtn.setVisible(!editBtn.isVisible());
            editTextArea.setVisible(!editTextArea.isVisible());
            editTextArea.setText(textLabel.getText());


        }

    }

    public void deleteMessage(ActionEvent event) {

        MessageEvent messageEvent = new MessageEvent(this, "delete", message);
        messageListener.eventOccurred(messageEvent);
        initialize(null, null);

    }

    public void edit(ActionEvent event) {

        MessageEvent messageEvent = new MessageEvent(this, "edit", message);
        messageEvent.setEditedText(editTextArea.getText());
        messageListener.eventOccurred(messageEvent);

        textLabel.setText(editTextArea.getText());
        editTextArea.setVisible(false);
        editBtn.setVisible(false);

    }

}
