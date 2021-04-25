package ir.sharif.ap2021.Listener;

import ir.sharif.ap2021.Controller.ReloadController;
import ir.sharif.ap2021.Event.ReloadEvent;


public class ReloadListener {

    ReloadController reloadController = new ReloadController();

    public void eventOccurred(ReloadEvent e){
        reloadController.reload(e);
    }

}
