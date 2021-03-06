package ir.sharif.ap2021.View.ModelView;

import ir.sharif.ap2021.Config.ErrorConfig;
import ir.sharif.ap2021.Config.ImageConfig;
import ir.sharif.ap2021.Controller.StaticController;
import ir.sharif.ap2021.Event.ThoughtEvent;
import ir.sharif.ap2021.Listener.ThoughtListener;
import ir.sharif.ap2021.Model.Thought.Thought;
import ir.sharif.ap2021.Model.User.User;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class ThoughtView implements Initializable {

    ErrorConfig errorConfig = new ErrorConfig();
    ImageConfig imageConfig = new ImageConfig();


    ThoughtListener thoughtListener = new ThoughtListener(this);

    private User mainUser;
    private User ownerUser;
    private User doedUser;
    private Thought thought;


    @FXML
    private Label text;
    @FXML
    private Circle avatar;
    @FXML
    private Label likes, rets, opinions, statusLabel, timeLabel,namesLabel;
    @FXML
    private ImageView likeIMG, retIMG, tIMG, oIMG;
    @FXML
    private TextArea replyText;
    @FXML
    private Button replyBtn, insertBtn;
    @FXML
    private AnchorPane replyPane;

    private boolean isChanged;
    private final ArrayList<Pane> comments = new ArrayList<>();

    public ThoughtView() throws IOException {
    }


    public ArrayList<Pane> getComments() {
        return comments;
    }

    public User getMainUser() {
        return mainUser;
    }

    public void setMainUser(User mainUser) {
        this.mainUser = mainUser;
    }

    public User getOwnerUser() {
        return ownerUser;
    }

    public void setOwnerUser(User ownerUser) {
        this.ownerUser = ownerUser;
    }

    public User getDoedUser() {
        return doedUser;
    }

    public void setDoedUser(User doedUser) {
        this.doedUser = doedUser;
    }

    public Thought getThought() {
        return thought;
    }

    public void setThought(Thought thought) {
        this.thought = thought;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (thought.getLikers().contains(StaticController.getMyUser().getId())) {

            likeIMG.setImage(new Image(imageConfig.getFillLike()));
        } else {
            likeIMG.setImage(new Image(imageConfig.getLike()));
        }

        if (thought.getRethoughters().contains(StaticController.getMyUser().getId())) {
            retIMG.setImage(new Image(imageConfig.getFillRet()));
        } else {
            retIMG.setImage(new Image(imageConfig.getRet()));
        }

        namesLabel.setText(ownerUser.getFirstName() + " " + ownerUser.getLastName());
        timeLabel.setText(String.valueOf(thought.getLocalDateTime()));
        text.setText(thought.getText());
        likes.setText(String.valueOf(thought.getLikes()));
        rets.setText(String.valueOf(thought.getRethought()));
        opinions.setText(String.valueOf(thought.getOpinions().size()));
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File(errorConfig.getMainConfig().getResourcesPath() + ownerUser.getAvatar()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert bufferedImage != null;
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);

        avatar.setFill(new ImagePattern(image));

        if (thought.getType().equals("t")) {
            statusLabel.setText(errorConfig.getShareTitle());
        } else {
            statusLabel.setText(errorConfig.getReplyTitle() + doedUser.getUserName());
        }

        if (thought.getImage() != null) {

            BufferedImage bi = null;
            try {
                bi = ImageIO.read(new File(errorConfig.getMainConfig().getResourcesPath() + thought.getImage()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            assert bi != null;
            Image im = SwingFXUtils.toFXImage(bi, null);

            tIMG.setImage(im);

        }


        replyBtn.setVisible(false);
        replyText.setVisible(false);
        insertBtn.setVisible(false);
        oIMG.setVisible(false);


    }


    public void reload() {
        initialize(null, null);
    }


    public void like(MouseEvent mouseEvent) throws IOException {


        ThoughtEvent thoughtChangeEvent = new ThoughtEvent(this, "like", thought);
        thoughtListener.eventOccurred(thoughtChangeEvent);


    }

    public void ret(MouseEvent mouseEvent) throws IOException {

        ThoughtEvent thoughtChangeEvent = new ThoughtEvent(this, "ret", thought);
        thoughtListener.eventOccurred(thoughtChangeEvent);
        StaticController.getMyMainMenu().show();

    }

    public void mention(MouseEvent mouseEvent) throws IOException {

        replyBtn.setVisible(!replyBtn.isVisible());
        replyText.setVisible(!replyText.isVisible());
        insertBtn.setVisible(!insertBtn.isVisible());
        oIMG.setVisible(!oIMG.isVisible());

    }


    public void saveMessage(ActionEvent event) throws IOException {

        ThoughtEvent thoughtChangeEvent = new ThoughtEvent(this, "saveMessage", thought);
        thoughtListener.eventOccurred(thoughtChangeEvent);

    }

    public void forwardMessage(ActionEvent event) throws IOException {

        ThoughtEvent thoughtChangeEvent = new ThoughtEvent(this, "forwardMessage", thought);
        thoughtListener.eventOccurred(thoughtChangeEvent);
    }

    public void muteAuthor(ActionEvent event) throws IOException {

        if (StaticController.getMyUser().getMuteList().contains(thought.getUser())) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(errorConfig.getAlreadyMuted());
            alert.showAndWait();

        } else {

            ThoughtEvent thoughtChangeEvent = new ThoughtEvent(this, "muteAuthor", thought);
            thoughtListener.eventOccurred(thoughtChangeEvent);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(errorConfig.getMuted());
            alert.showAndWait();
        }

    }

    public void reportSpam(ActionEvent event) throws IOException {

        ThoughtEvent thoughtChangeEvent = new ThoughtEvent(this, "spam", thought);
        thoughtListener.eventOccurred(thoughtChangeEvent);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(errorConfig.getSpammed());
        alert.showAndWait();

    }

    public void authorProfile(ActionEvent event) throws IOException {

        if (mainUser.getId() == ownerUser.getId()) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(errorConfig.getYourThought());
            alert.showAndWait();

        }

        ThoughtEvent thoughtChangeEvent = new ThoughtEvent(this, "profile", thought);
        thoughtListener.eventOccurred(thoughtChangeEvent);

    }

    public void showOpinions(ActionEvent event) throws IOException {

        if (thought.getOpinions().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(errorConfig.getYourThought());
            alert.showAndWait();

        } else {

            ThoughtEvent thoughtChangeEvent = new ThoughtEvent(this, "opinions", thought);
            thoughtListener.eventOccurred(thoughtChangeEvent);
        }
    }


    public void reply(ActionEvent event) throws IOException {

        ThoughtEvent thoughtChangeEvent = new ThoughtEvent(this, "mention", thought);
        thoughtChangeEvent.setMentionText(replyText.getText());
        if (isChanged) {
            thoughtChangeEvent.setMentionImg("changed");
            isChanged = false;
        } else {
            thoughtChangeEvent.setMentionImg("no");
        }

        if (replyText.getText().length() <= 300) {
            thoughtListener.eventOccurred(thoughtChangeEvent);
            replyText.setText(null);
        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(errorConfig.getOpinionLength());
            alert.showAndWait();

        }


    }


    public void insert(ActionEvent event) throws IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png"));
        File file = fileChooser.showOpenDialog(StaticController.getMyStage());


        if (file != null) {

            Image img = new Image(file.toURI().toString());

            saveToFile(img, "31");
            oIMG.setImage(img);

            isChanged = true;
        } else isChanged = false;

    }


    public void saveToFile(Image image, String name) throws IOException {

        File fileOutput = new File(errorConfig.getMainConfig().getResourcesPath() + "/ThoughtImages/" + name + ".png");

        if (fileOutput.exists()) {
            fileOutput.delete();
        }

        BufferedImage Bi = SwingFXUtils.fromFXImage(image, null);
        ImageIO.write(Bi, "png", fileOutput);
    }

}
