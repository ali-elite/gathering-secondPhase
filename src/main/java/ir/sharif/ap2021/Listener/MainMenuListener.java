package ir.sharif.ap2021.Listener;


import ir.sharif.ap2021.Controller.MainMenuController;
import ir.sharif.ap2021.Event.MainMenuEvent;
import ir.sharif.ap2021.Event.PrivacyEvent;
import ir.sharif.ap2021.View.Menu.Mainmenu;

import java.io.IOException;

public class MainMenuListener {

    MainMenuController mainMenuController = new MainMenuController();
    Mainmenu mainmenu;

    public MainMenuListener(Mainmenu mainmenu) {
        this.mainmenu = mainmenu;
    }


    public void eventOccurred(MainMenuEvent event) throws IOException {

        if (event.getOrder().equals("load")) {
            mainMenuController.load(mainmenu, event.getUsername());
        }

        if (event.getOrder().equals("gatherThought")) {
            mainMenuController.gatherThought(mainmenu);
        }

        if (event.getOrder().equals("exploreThought")) {
            mainMenuController.exploreThought(mainmenu);
        }

        if (event.getOrder().equals("timeLineThought")) {
            mainMenuController.timeLineThought(mainmenu);
        }

        if (event.getOrder().equals("notif")) {
            mainMenuController.notif(mainmenu);
        }

        if (event.getOrder().equals("chats")) {
            mainMenuController.chats(mainmenu);
        }

        if (event.getOrder().equals("group")) {
            mainMenuController.group(mainmenu);
        }

        if (event.getOrder().equals("groupMessage")) {
            mainMenuController.groupMessage(mainmenu);
        }

        if (event.getOrder().equals("delete")) {
            mainMenuController.deleteUser(mainmenu);
        }

        if (event.getOrder().equals("logOut")) {
            mainMenuController.logOut(mainmenu);
        }

        if (event.getOrder().equals("forwards")) {
            mainMenuController.forwards(mainmenu);
        }

        if (event.getOrder().equals("unblock")) {
            mainMenuController.unblock(event);
        }

        if (event.getOrder().equals("blacklist")) {
            mainMenuController.blacklist(event);
        }
    }

    public void privacyEventOccurred(PrivacyEvent event) throws IOException {

        if (event.getOrder().equals("changePassword")) {
            mainMenuController.changePassword(event.getPassword());
        }

        if (event.getOrder().equals("lastSeen")) {
            mainMenuController.lastSeen(event.getLastSeen());
        }

        if (event.getOrder().equals("changeActivity")) {
            mainMenuController.changeActivity(event.isDiactive());
        }

        if (event.getOrder().equals("changePrivacy")) {
            mainMenuController.changePrivacy(event.isDiactive());
        }

    }
}