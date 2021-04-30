package ir.sharif.ap2021.Listener;


import ir.sharif.ap2021.Controller.MainMenuController;
import ir.sharif.ap2021.Event.MainMenuEvent;
import ir.sharif.ap2021.View.Menu.Mainmenu;

import java.io.IOException;

public class MainMenuListener {

     MainMenuController mainMenuController = new MainMenuController();
     Mainmenu mainmenu;

    public MainMenuListener(Mainmenu mainmenu) {
        this.mainmenu = mainmenu;
    }


    public void eventOccurred(MainMenuEvent event) throws IOException {

        if (event.getOrder().equals("load")){
            mainMenuController.load(mainmenu,event.getUsername());
        }

        if (event.getOrder().equals("update")){
            mainMenuController.update(mainmenu);
        }

        if (event.getOrder().equals("gatherThought")){
            mainMenuController.gatherThought(mainmenu);
        }

        if (event.getOrder().equals("notif")){
            mainMenuController.notif(mainmenu);
        }

    }

}
