package ir.sharif.ap2021.Model.Chat;

import ir.sharif.ap2021.Config.MainConfig;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Chat {

    MainConfig mainConfig = new MainConfig();

    private int id;
    private static int nextId = 45;
    private boolean isGroup;
    private String chatAddress;
    private String name;

    ArrayList<Integer> users = new ArrayList<>();
    ArrayList<Integer> messages = new ArrayList<>();

    public Chat(String name, boolean isGroup) throws IOException {

        chatAddress = mainConfig.getResourcesPath() + "/Chats";
        this.id = nextId + Objects.requireNonNull(new File(chatAddress).listFiles()).length;
        this.isGroup = isGroup;
        this.name = name;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public void setGroup(boolean group) {
        isGroup = group;
    }

    public String getChatAddress() {
        return chatAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Integer> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<Integer> users) {
        this.users = users;
    }

    public ArrayList<Integer> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Integer> messages) {
        this.messages = messages;
    }
}
