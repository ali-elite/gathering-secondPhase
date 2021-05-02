package ir.sharif.ap2021.DB;

import com.google.gson.Gson;
import ir.sharif.ap2021.Model.Chat.Message;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;


public class MessageDB implements DBSet<Message>{

    private static final String messageAddress = "./src/main/resources/Messages/";
    private static final Gson gson = new Gson();

    @Override
    public Message get(int id) {
        File us = new File(messageAddress);

        for (File f : Objects.requireNonNull(us.listFiles())) {
            try {
                Message message = gson.fromJson(new FileReader(f), Message.class);
                if (message.getId() == id) {
                    return message;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
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
        }

        try {
            FileWriter fileWriter = new FileWriter(messageFile);
            gson.toJson(message, fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
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
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }
    }

    @Override
    public Message getByName(String username) {
        return null;
    }
}
