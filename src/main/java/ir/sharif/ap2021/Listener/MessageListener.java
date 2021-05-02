package ir.sharif.ap2021.Listener;

import ir.sharif.ap2021.Controller.MessageController;
import ir.sharif.ap2021.Event.MessageEvent;

public class MessageListener {

    MessageController messageController = new MessageController();

    public void eventOccurred(MessageEvent formEvent) {

        if (formEvent.getOrder().equals("seen")) {
            messageController.seen(formEvent);
        }

    }

}
