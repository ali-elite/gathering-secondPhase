package ir.sharif.ap2021.Listener;

import ir.sharif.ap2021.Controller.MessageController;
import ir.sharif.ap2021.Event.MessageEvent;

import java.io.IOException;

public class MessageListener {

    MessageController messageController = new MessageController();

    public MessageListener() throws IOException {
    }

    public void eventOccurred(MessageEvent formEvent) {

        if (formEvent.getOrder().equals("seen")) {
            messageController.seen(formEvent);
        }

        if (formEvent.getOrder().equals("edit")) {
            messageController.edit(formEvent);
        }

        if (formEvent.getOrder().equals("delete")) {
            messageController.delete(formEvent);
        }

    }

}
