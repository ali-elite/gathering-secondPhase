package ir.sharif.ap2021.View.Menu;

import ir.sharif.ap2021.Config.ErrorConfig;
import ir.sharif.ap2021.Controller.StaticController;
import ir.sharif.ap2021.Event.EditProfileEvent;
import ir.sharif.ap2021.Listener.EditProfileListener;
import ir.sharif.ap2021.Validation.AuthenticationException;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class EditProfile implements Initializable {


    ErrorConfig errorConfig = new ErrorConfig();

    @FXML
    private TextField firstNameTF, lastNameTf, userNameTF,
            phoneTF, emailTF;
    @FXML
    private Label myLabel;
    @FXML
    private Button subBtn, chooseBtn, backBtn;
    @FXML
    private Circle avatar;
    @FXML
    private TextArea bioText;
    @FXML
    private DatePicker birthday;


    private boolean isChanged = false;
    private EditProfileListener editProfileListener = new EditProfileListener();

    public EditProfile() throws IOException {
    }

    public void submit(ActionEvent event) throws IOException, InterruptedException {

        boolean isReady = true;

        if (firstNameTF.getText().equals("")) {
            isReady = false;
            myLabel.setText(errorConfig.getEnterFirst());
        }
        if (lastNameTf.getText().equals("")) {
            isReady = false;
            myLabel.setText(errorConfig.getEnterLast());
        }
        if (userNameTF.getText().equals("")) {
            isReady = false;
            myLabel.setText(errorConfig.getEnterUser());
        }

        if (emailTF.getText().equals("")) {
            isReady = false;
            myLabel.setText(errorConfig.getEnterEmail());
        }

        if (!(emailTF.getText().length() > 6) || !emailTF.getText().contains("@") || !emailTF.getText().endsWith(".com")) {
            isReady = false;
            myLabel.setText(errorConfig.getValidEmail());
        }

//        if (!phoneTF.getText().equals("") && (phoneTF.getText().length() > 12 || !checkNumber(phoneTF.getText()))) {
//            isReady = false;
//            myLabel.setText(errorConfig.getValidPhone());
//        }

        String s = "";
        if (isChanged) {
            s = "change";
            isChanged = false;
        }

        EditProfileEvent editProfileEvent = new EditProfileEvent(this, firstNameTF.getText(),
                lastNameTf.getText(), userNameTF.getText(), phoneTF.getText(), emailTF.getText(),
                bioText.getText(), s, birthday.getValue()
        );

        try {
            editProfileListener.eventOccurred(editProfileEvent);
        } catch (AuthenticationException e) {
            myLabel.setText(e.getMessage());
            isReady = false;
        }

        if (isReady) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(errorConfig.getProfileChanged());
            alert.showAndWait();

            back(null);

        }

    }

    public void chooseFile(ActionEvent event) throws IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png"));
        File file = fileChooser.showOpenDialog(StaticController.getMyStage());


        if (file != null) {

            Image img = new Image(file.toURI().toString());

            saveToFile(img, String.valueOf(StaticController.getMyUser().getId()));
            avatar.setFill(new ImagePattern(img));

            isChanged = true;

        } else isChanged = false;


    }

    public void back(ActionEvent event) throws IOException {
        StaticController.getMyMainMenu().show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        firstNameTF.setText(StaticController.getMyUser().getFirstName());
        lastNameTf.setText(StaticController.getMyUser().getLastName());
        userNameTF.setText(StaticController.getMyUser().getUserName());
        phoneTF.setText(StaticController.getMyUser().getPhoneNumber());
        emailTF.setText(StaticController.getMyUser().getEmail());
        bioText.setText(StaticController.getMyUser().getBiography());
        birthday.setValue(StaticController.getMyUser().getBirthday());

        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File(errorConfig.getMainConfig().getResourcesPath() + StaticController.getMyUser().getAvatar()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert bufferedImage != null;
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);

        avatar.setFill(new ImagePattern(image));

    }

    public void saveToFile(Image image, String name) throws IOException {

        File fileOutput = new File(errorConfig.getMainConfig().getResourcesPath() + "/Avatars/" + name + ".png");

        if (fileOutput.exists()) {
            fileOutput.delete();
        }

        BufferedImage Bi = SwingFXUtils.fromFXImage(image, null);
        ImageIO.write(Bi, "png", fileOutput);

    }

    public boolean checkNumber(String s) {

        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }

        return true;
    }

}
