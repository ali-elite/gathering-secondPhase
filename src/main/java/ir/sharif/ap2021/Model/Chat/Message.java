package ir.sharif.ap2021.Model.Chat;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Message {

    private int id;
    private static int nextId = 4311;

    private String text;
    private int sender;
    private boolean isForwarded;
    private LocalDateTime time;
    private final ArrayList<Integer> seenUsers = new ArrayList<>();

    private final String messageAddress = "./src/main/resources/Messages";

    public Message(int sender, boolean isForwarded,String text) {

        this.id = nextId + Objects.requireNonNull(new File(messageAddress).listFiles()).length;
        this.sender = sender;
        this.isForwarded = isForwarded;
        this.text = text;

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
}
