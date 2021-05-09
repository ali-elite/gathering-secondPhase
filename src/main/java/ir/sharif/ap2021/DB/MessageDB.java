package ir.sharif.ap2021.DB;

import com.google.gson.Gson;
import ir.sharif.ap2021.Config.MainConfig;
import ir.sharif.ap2021.Model.Chat.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;


public class MessageDB implements DBSet<Message>{

    MainConfig mainConfig = new MainConfig();
    private static final Logger logger = LogManager.getLogger(MessageDB.class);
    private static  String messageAddress ;
    private static final Gson gson = new Gson();

    public MessageDB() throws IOException {
        messageAddress = mainConfig.getResourcesPath() + "/Messages/";
    }

    @Override
    public Message get(int id) {
        File us = new File(messageAddress);

        for (File f : Objects.requireNonNull(us.listFiles())) {
            try {
                Message message = gson.fromJson(new FileReader(f), Message.class);
                if (message.getId() == id) {
                    logger.info("message " + id + "loaded from DBFile");
                    return message;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }

        }

        return null;
    }

    @Override
    public ArrayList<Message> all() {
        return null;
    }

    @Override
    public void add(Message message) {

        File messageFile = new File(messageAddress + message.getId() + ".txt");
        if (!messageFile.getParentFile().exists()) messageFile.getParentFile().mkdirs();

        try {
            messageFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        try {
            FileWriter fileWriter = new FileWriter(messageFile);
            gson.toJson(message, fileWriter);
            fileWriter.flush();
            fileWriter.close();
            logger.info("message " + message.getId() + "added");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    @Override
    public void remove(Message message) {

    }

    @Override
    public void update(Message message) {

        File us = new File(messageAddress);

        for (File f : Objects.requireNonNull(us.listFiles())) {

            if (f.getName().equals(message.getId() + ".txt")) {
                try {
                    FileWriter fileWriter = new FileWriter(f);
                    gson.toJson(message, fileWriter);
                    fileWriter.flush();
                    fileWriter.close();
                    logger.info("message " + message.getId() + "updated");
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error(e.getMessage());
                }

            }

        }
    }

    @Override
    public Message getByName(String username) {
        return null;
    }
}
