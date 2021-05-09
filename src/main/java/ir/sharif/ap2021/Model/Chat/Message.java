package ir.sharif.ap2021.Model.Chat;

import ir.sharif.ap2021.Config.MainConfig;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Message {


    MainConfig mainConfig = new MainConfig();

    private int id;
    private static int nextId = 4311;

    private String text;
    private int sender;
    private String image;
    private boolean isForwarded;
    private boolean isDeleted;
    private LocalDateTime time;
    private final ArrayList<Integer> seenUsers = new ArrayList<>();

    private String messageAddress;

    public Message(int sender, boolean isForwarded, String text) throws IOException {

        messageAddress = mainConfig.getResourcesPath() + "/Messages";

        this.id = nextId + Objects.requireNonNull(new File(messageAddress).listFiles()).length;
        this.sender = sender;
        this.isForwarded = isForwarded;
        this.text = text;
        isDeleted = false;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public boolean isForwarded() {
        return isForwarded;
    }

    public void setForwarded(boolean forwarded) {
        isForwarded = forwarded;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public ArrayList<Integer> getSeenUsers() {
        return seenUsers;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
