package ir.sharif.ap2021.Event;

import java.util.ArrayList;
import java.util.EventObject;

public class NewGroupEvent extends EventObject {

    String order;
    ArrayList<String> users;
    String name;

    public NewGroupEvent(Object source, String order, ArrayList<String> users, String name) {

        super(source);
        this.order = order;
        this.users = users;
        this.name = name;
    }

    public String getOrder() {

        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
