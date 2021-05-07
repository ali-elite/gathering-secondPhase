package ir.sharif.ap2021.Config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ImageConfig {

    private MainConfig mainConfig = new MainConfig();

    private String logo;



    public ImageConfig() throws IOException {
        setProperties();
    }

    private void setProperties() throws IOException {

        Properties properties = new Properties();
        FileReader fileReader = new FileReader(mainConfig.getImageConfigPath());
        properties.load(fileReader);

        logo = properties.getProperty("logo");

    }

    public String getLogo() {
        return logo;
    }
}
