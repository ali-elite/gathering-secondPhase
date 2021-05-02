package ir.sharif.ap2021.Model.User;

import java.util.ArrayList;

public class UserCategory {

    int ownerUser;
    ArrayList<Integer> users;
    String name;

    public UserCategory(String name,int ownerUser) {

        this.name = name;
        this.ownerUser = ownerUser;
        users = new ArrayList<>();

    }

    public ArrayList<Integer> getUsers() {
        return users;
    }

    public String getName() {
        return name;
    }

    public int getOwnerUser() {
        return ownerUser;
    }
}
