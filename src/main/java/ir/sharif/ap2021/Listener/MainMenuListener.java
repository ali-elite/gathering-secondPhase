package ir.sharif.ap2021.Listener;


import ir.sharif.ap2021.Controller.MainMenuController;
import ir.sharif.ap2021.Event.MainMenuEvent;

import java.io.IOException;

public class MainMenuListener {

    MainMenuController mainMenuController = new MainMenuController();


    public void eventOccurred(MainMenuEvent event) throws IOException {

        if (event.getOrder().equals("load")){
            mainMenuController.load(event.getMainmenu(),event.getUsername());
        }

        if (event.getOrder().equals("update")){
            mainMenuController.update(event.getMainmenu());
        }

        if (event.getOrder().equals("thought")){
            mainMenuController.thought(event.getMainmenu());
        }

        if (event.getOrder().equals("notif")){
            mainMenuController.notif(event.getMainmenu());
        }

    }

}
