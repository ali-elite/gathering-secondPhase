package ir.sharif.ap2021.Listener;

import ir.sharif.ap2021.DB.Context;
import ir.sharif.ap2021.Event.UserPassEvent;
import ir.sharif.ap2021.View.Mainmenu;

public class UserSetListener {

    Context context = new Context();
    public void eventOccurred(UserPassEvent formEvent)  {
        Mainmenu.setMyUser(context.Users.getByName(formEvent.getUsername()));
    }

}
