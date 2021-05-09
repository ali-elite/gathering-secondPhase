package ir.sharif.ap2021.Model.Notification;

import ir.sharif.ap2021.Config.MainConfig;
import ir.sharif.ap2021.Model.User.User;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Notification {

    MainConfig mainConfig = new MainConfig();

    private int sender;
    private int recevier;
    private boolean isAnswered;
    private boolean isRequest;
    private String text;
    private int id;
    private final int nextId = 1445;
    private String notificationAddress;

    public Notification(boolean isRequest,User sender, User recevier, String text) throws IOException {

        notificationAddress = mainConfig.getResourcesPath() + "/Notifications";

        this.id = nextId + Objects.requireNonNull(new File(notificationAddress).listFiles()).length;
        this.sender = sender.getId();
        this.recevier = recevier.getId();
        this.text = text;
        this.isRequest = isRequest;
        isAnswered = false;
    }

    public Notification(boolean isRequest,int sender, int recevier,String text) throws IOException {

        notificationAddress = mainConfig.getResourcesPath() + "/Notifications";

        this.id = nextId + Objects.requireNonNull(new File(notificationAddress).listFiles()).length;
        this.sender = sender;
        this.recevier = recevier;
        this.text = text;
        this.isRequest = isRequest;
        isAnswered = false;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getRecevier() {
        return recevier;
    }

    public void setRecevier(int recevier) {
        this.recevier = recevier;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    public boolean isRequest() {
        return isRequest;
    }

    public void setRequest(boolean request) {
        isRequest = request;
    }
}
