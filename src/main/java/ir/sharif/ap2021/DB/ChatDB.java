package ir.sharif.ap2021.DB;

import com.google.gson.Gson;
import ir.sharif.ap2021.Config.MainConfig;
import ir.sharif.ap2021.Model.Chat.Chat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class ChatDB implements DBSet<Chat> {

    MainConfig mainConfig = new MainConfig();
    private static final Logger logger = LogManager.getLogger(ChatDB.class);
    private static String chatAddress;
    private static final Gson gson = new Gson();

    public ChatDB() throws IOException {
        chatAddress = mainConfig.getResourcesPath() + "/Chats/";
    }

    @Override
    public Chat get(int id) {
        File us = new File(chatAddress);

        for (File f : Objects.requireNonNull(us.listFiles())) {
            try {
                Chat chat = gson.fromJson(new FileReader(f), Chat.class);
                if (chat.getId() == id) {
                    logger.info("chat " + id + "loaded from DBFile");
                    return chat;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }

        }

        return null;
    }

    @Override
    public ArrayList<Chat> all() {
        return null;
    }

    @Override
    public void add(Chat chat) {

        File chatFile = new File(chatAddress + chat.getId() + ".txt");
        if (!chatFile.getParentFile().exists()) chatFile.getParentFile().mkdirs();

        try {
            chatFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        try {
            FileWriter fileWriter = new FileWriter(chatFile);
            gson.toJson(chat, fileWriter);
            fileWriter.flush();
            fileWriter.close();
            logger.info("chat " + chat.getId() + "added");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

    }

    @Override
    public void remove(Chat chat) {
    }

    @Override
    public void update(Chat chat) {

        File us = new File(chatAddress);

        for (File f : Objects.requireNonNull(us.listFiles())) {

            if (f.getName().equals(chat.getId() + ".txt")) {
                try {
                    FileWriter fileWriter = new FileWriter(f);
                    gson.toJson(chat, fileWriter);
                    fileWriter.flush();
                    fileWriter.close();
                    logger.info("chat " + chat.getId() + "updated");
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error(e.getMessage());
                }

            }

        }

    }


    @Override
    public Chat getByName(String username) {
        return null;
    }
}
