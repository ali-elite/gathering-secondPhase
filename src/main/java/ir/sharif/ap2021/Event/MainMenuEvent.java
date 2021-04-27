package ir.sharif.ap2021.Event;

import ir.sharif.ap2021.View.Menu.Mainmenu;

import java.util.EventObject;

public class MainMenuEvent extends EventObject {

    String order;
    String username;
    Mainmenu mainmenu;


    public MainMenuEvent(Object source, String order,String username, Mainmenu mainmenu) {
        super(source);
        this.order = order;
        this.username = username;
        this.mainmenu = mainmenu;
    }

    public String getOrder() {
        return order;
    }

    public Mainmenu getMainmenu() {
        return mainmenu;
    }

    public String getUsername() {
        return username;
    }
}
