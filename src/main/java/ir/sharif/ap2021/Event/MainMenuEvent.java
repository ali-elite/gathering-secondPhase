package ir.sharif.ap2021.Event;

import ir.sharif.ap2021.View.Menu.Mainmenu;

import java.util.EventObject;

public class MainMenuEvent extends EventObject {

    String order;
    String username;


    public MainMenuEvent(Object source, String order,String username) {
        super(source);
        this.order = order;
        this.username = username;

    }

    public String getOrder() {
        return order;
    }

    public String getUsername() {
        return username;
    }
}
