package ir.sharif.ap2021.Controller;

import ir.sharif.ap2021.DB.Context;
import ir.sharif.ap2021.Event.MessageEvent;
import ir.sharif.ap2021.Model.Chat.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MessageController {

    Context context = new Context();
    private static final Logger logger = LogManager.getLogger(MessageController.class);

    public void seen(MessageEvent formEvent) {

        Message message = formEvent.getMessage();

        int myId = StaticController.getMyUser().getId();

        if (!message.getSeenUsers().contains(myId)) {
            message.getSeenUsers().add(myId);
        }


        context.Messages.update(message);
    }


    public void edit(MessageEvent formEvent) {

        Message message = formEvent.getMessage();

        message.setText(formEvent.getEditedText());
        logger.info("message " + message.getId() + " has edited to " + formEvent.getEditedText());

        context.Messages.update(message);
    }

    public void delete(MessageEvent formEvent) {

        Message message = formEvent.getMessage();

        message.setDeleted(true);
        logger.info("message " + message.getId() + " has deleted");

        context.Messages.update(message);

    }
}
