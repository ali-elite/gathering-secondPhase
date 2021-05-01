package ir.sharif.ap2021.Controller;

import ir.sharif.ap2021.Model.Thought.Thought;
import ir.sharif.ap2021.Model.User.User;
import ir.sharif.ap2021.View.Menu.Mainmenu;
import javafx.stage.Stage;

public class StaticController {

    private static User myUser;
    private static Stage myStage;
    private static Mainmenu myMainMenu;
    private static Thought lastThought;


//    private static Context context = new Context();


    public static User getMyUser() {
        return myUser;
    }

    public static void setMyUser(User myUser) {
        StaticController.myUser = myUser;
    }

    public static Stage getMyStage() {
        return myStage;
    }

    public static void setMyStage(Stage myStage) {
        StaticController.myStage = myStage;
    }

    public static Mainmenu getMyMainMenu() {
        return myMainMenu;
    }

    public static void setMyMainMenu(Mainmenu myMainMenu) {
        StaticController.myMainMenu = myMainMenu;
    }

    public static Thought getLastThought() {
        return lastThought;
    }

    public static void setLastThought(Thought lastThought) {
        StaticController.lastThought = lastThought;
    }


//    public static void reloadMyUser() {
//        setMyUser(context.Users.get(myUser.getId()));
//    }

}
