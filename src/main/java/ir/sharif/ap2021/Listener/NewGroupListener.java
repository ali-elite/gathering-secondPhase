package ir.sharif.ap2021.Listener;

import ir.sharif.ap2021.Controller.NewGroupController;
import ir.sharif.ap2021.Event.NewGroupEvent;

public class NewGroupListener {

    NewGroupController newGroupController = new NewGroupController();

    public void eventOccurred(NewGroupEvent formEvent) {

        if(formEvent.getOrder().equals("makeGroup")){
            newGroupController.makeGroup(formEvent);
        }

        if(formEvent.getOrder().equals("groupMessage")){
            newGroupController.groupMessage(formEvent);
        }

    }

}
