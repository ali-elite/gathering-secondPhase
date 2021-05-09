package ir.sharif.ap2021.DB;

import com.google.gson.Gson;
import ir.sharif.ap2021.Config.MainConfig;
import ir.sharif.ap2021.Model.User.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class UserDB implements DBSet<User> {

    MainConfig mainConfig = new MainConfig();
    private static final Logger logger = LogManager.getLogger(UserDB.class);
    private static String userAddress;
    private static final Gson gson = new Gson();

    public UserDB() throws IOException {
        userAddress = mainConfig.getResourcesPath() + "/Users/";
    }

    @Override
    public User get(int id) {

        File us = new File(userAddress);

        for (File f : Objects.requireNonNull(us.listFiles())) {
            try {
                User user = gson.fromJson(new FileReader(f), User.class);
                if (user.getId() == id) {
                    logger.info("user " + id + "loaded from DBFile");
                    return user;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
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
                logger.error(e.getMessage());
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
            logger.error(e.getMessage());
        }

        try {
            FileWriter fileWriter = new FileWriter(userFile);
            gson.toJson(user, fileWriter);
            fileWriter.flush();
            fileWriter.close();
            logger.info("user " + user.getId() + "added (Singed up)");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

    }

    @Override
    public void remove(User user) {

    }

    @Override
    public void update(User user) {

        File us = new File(userAddress);

        for (File f : Objects.requireNonNull(us.listFiles())) {

            if (f.getName().equals(user.getId() + ".txt")) {
                try {
                    FileWriter fileWriter = new FileWriter(f);
                    gson.toJson(user, fileWriter);
                    fileWriter.flush();
                    fileWriter.close();
                    logger.info("user " + user.getId() + "updated");
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error(e.getMessage());
                }

            }

        }

    }

    @Override
    public User getByName(String username) {

        for (User user : all()) {
            if (user.getUserName().equals(username)) {
                logger.info("user " + user.getId() + "loaded from DBFile by username " + username);
                return user;
            }
        }

        return null;
    }


}
