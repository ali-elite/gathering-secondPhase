package ir.sharif.ap2021.DB;

import com.google.gson.Gson;
import ir.sharif.ap2021.Model.Thought.Thought;
import ir.sharif.ap2021.Model.User.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class UserDB implements DBSet<User> {

    private static final String userAddress = "./src/main/resources/Users/";
    private static final Gson gson = new Gson();

    @Override
    public User get(int id) {

        File us = new File(userAddress);

        for (File f : Objects.requireNonNull(us.listFiles())) {
            try {
                User user = gson.fromJson(new FileReader(f), User.class);
                if (user.getId() == id) {
                    return user;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        return null;
    }


    @Override
    public ArrayList<User> all() {

        ArrayList<User> u = new ArrayList<>();

        File us = new File(userAddress);

        for (File f : Objects.requireNonNull(us.listFiles())) {

            try {
                User user = gson.fromJson(new FileReader(f), User.class);
                u.add(user);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        return u;
    }

    @Override
    public void add(User user) {

        File userFile = new File(userAddress + user.getId() + ".txt");
        if (!userFile.getParentFile().exists()) userFile.getParentFile().mkdirs();

        try {
            userFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter fileWriter = new FileWriter(userFile);
            gson.toJson(user, fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void remove(User user) {

    }

    @Override
    public void update(User user) {

        File us = new File(userAddress);

        for (File f : Objects.requireNonNull(us.listFiles())) {


            if (f.getName().equals(user.getUserName() + ".txt")) {
                try {
                    FileWriter fileWriter = new FileWriter(f);
                    gson.toJson(user, fileWriter);
                    fileWriter.flush();
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }

    }

    @Override
    public User getByName(String username) {

        for (User user : all()) {
            if (user.getUserName().equals(username)) {
                return user;
            }
        }

        return null;
    }


}
