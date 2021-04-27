package ir.sharif.ap2021.Controller;

import ir.sharif.ap2021.DB.Context;
import ir.sharif.ap2021.Model.User.User;
import javafx.stage.Stage;

public class StaticController {

    private static User myUser;
    private static Stage myStage;
    private static Context context = new Context();

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

    public static void reloadMyUser() {
        setMyUser(context.Users.get(myUser.getId()));
    }

}
