package ir.sharif.ap2021.Controller;

import ir.sharif.ap2021.DB.Context;
import ir.sharif.ap2021.Event.MessageEvent;
import ir.sharif.ap2021.Model.Chat.Message;

public class MessageController {

    Context context = new Context();

    public void seen(MessageEvent formEvent) {

        Message message = formEvent.getMessage();


        int myId = StaticController.getMyUser().getId();


        if (!message.getSeenUsers().contains(myId)) {
            message.getSeenUsers().add(myId);
        }


        context.Messages.update(message);
    }


}
