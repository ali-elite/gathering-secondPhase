package ir.sharif.ap2021.Listener;

import ir.sharif.ap2021.Controller.NewGroupController;
import ir.sharif.ap2021.Event.NewGroupEvent;

import java.io.IOException;

public class NewGroupListener {

    NewGroupController newGroupController = new NewGroupController();

    public NewGroupListener() throws IOException {
    }

    public void eventOccurred(NewGroupEvent formEvent) {

        if(formEvent.getOrder().equals("makeGroup")){
            newGroupController.makeGroup(formEvent);
        }

        if(formEvent.getOrder().equals("groupMessage")){
            newGroupController.groupMessage(formEvent);
        }

    }

}
