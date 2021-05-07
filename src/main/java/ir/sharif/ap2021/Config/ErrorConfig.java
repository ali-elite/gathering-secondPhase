package ir.sharif.ap2021.Config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ErrorConfig {

    MainConfig mainConfig = new MainConfig();


    private String messageLength;
    private String enterFirst;
    private String enterLast;
    private String enterUser;
    private String enterEmail;
    private String validEmail;
    private String validPhone;
    private String profileChanged;

    public ErrorConfig() throws IOException {
        setProperties();
    }


    private void setProperties() throws IOException {

        Properties properties = new Properties();
        FileReader fileReader = new FileReader(mainConfig.getErrorConfigPath());
        properties.load(fileReader);

        messageLength = properties.getProperty("messageLength");
        enterFirst = properties.getProperty("enterFirst");
        enterLast = properties.getProperty("enterLast");
        enterUser = properties.getProperty("enterUser");
        enterEmail = properties.getProperty("enterEmail");
        validEmail = properties.getProperty("validEmail");
        validPhone = properties.getProperty("validPhone");
        profileChanged = properties.getProperty("profileChanged");

    }

    public MainConfig getMainConfig() {
        return mainConfig;
    }

    public String getMessageLength() {
        return messageLength;
    }

    public String getEnterFirst() {
        return enterFirst;
    }

    public String getEnterLast() {
        return enterLast;
    }

    public String getEnterUser() {
        return enterUser;
    }

    public String getEnterEmail() {
        return enterEmail;
    }

    public String getValidEmail() {
        return validEmail;
    }

    public String getValidPhone() {
        return validPhone;
    }

    public String getProfileChanged() {
        return profileChanged;
    }
}
