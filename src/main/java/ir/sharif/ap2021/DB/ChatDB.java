package ir.sharif.ap2021.DB;

import com.google.gson.Gson;
import ir.sharif.ap2021.Model.Chat.Chat;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class ChatDB implements DBSet<Chat>{

    private static final String chatAddress = "./src/main/resources/Chats/";
    private static final Gson gson = new Gson();

    @Override
    public Chat get(int id) {
        File us = new File(chatAddress);

        for (File f : Objects.requireNonNull(us.listFiles())) {
            try {
                Chat chat = gson.fromJson(new FileReader(f), Chat.class);
                if (chat.getId() == id) {
                    return chat;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
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
        }

        try {
            FileWriter fileWriter = new FileWriter(chatFile);
            gson.toJson(chat, fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
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
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }

    }



    @Override
    public Chat getByName(String username) {
        return null;
    }
}
