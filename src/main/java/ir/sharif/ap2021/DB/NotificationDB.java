package ir.sharif.ap2021.DB;

import com.google.gson.Gson;
import ir.sharif.ap2021.Model.Notification.Notification;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Objects;

public class NotificationDB implements DBSet<Notification>{


    private static final String notifAddress = "./src/main/resources/Notifications/";
    private static final Gson gson = new Gson();


    @Override
    public Notification get(int id) {
        File us = new File(notifAddress);

        for (File f : Objects.requireNonNull(us.listFiles())) {
            try {
                Notification notification = gson.fromJson(new FileReader(f), Notification.class);
                if (notification.getId() == id) {
                    return notification;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        return null;
    }

    @Override
    public ArrayList<Notification> all() {
        return null;
    }

    @Override
    public void add(Notification notification) {

    }

    @Override
    public void remove(Notification notification) {

    }

    @Override
    public void update(Notification notification) {

    }

    @Override
    public Notification getByName(String username) {
        return null;
    }
}
