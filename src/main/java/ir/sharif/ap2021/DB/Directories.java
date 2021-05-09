package ir.sharif.ap2021.DB;

import ir.sharif.ap2021.Config.MainConfig;

import java.io.File;
import java.io.IOException;

public class Directories {


    MainConfig mainConfig = new MainConfig();

    public Directories() throws IOException {
    }

    public void setDirectoires() throws IOException {

        File chat = new File(mainConfig.getResourcesPath() + "/Chats");
        if (!chat.exists()) {
            chat.mkdirs();
        }
        File message = new File(mainConfig.getResourcesPath() + "/Messages");
        if (!message.exists()) {
            message.mkdirs();
        }
        File notification = new File(mainConfig.getResourcesPath() + "/Notifications");
        if (!notification.exists()) {
            notification.mkdirs();
        }
        File thought = new File(mainConfig.getResourcesPath() + "/Thoughts");
        if (!thought.exists()) {
            thought.mkdirs();
        }
        File user = new File(mainConfig.getResourcesPath() + "/Users");
        if (!user.exists()) {
            user.mkdirs();
        }
        File avatar = new File(mainConfig.getResourcesPath() + "/Avatars");
        if (!avatar.exists()) {
            avatar.mkdirs();
        }
        File messageImg = new File(mainConfig.getResourcesPath() + "/MessageImages");
        if (!messageImg.exists()) {
            messageImg.mkdirs();
        }
        File thoughtImg = new File(mainConfig.getResourcesPath() + "/ThoughtImages");
        if (!thoughtImg.exists()) {
            thoughtImg.mkdirs();
        }

    }

}
