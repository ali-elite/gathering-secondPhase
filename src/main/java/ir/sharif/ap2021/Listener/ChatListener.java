package ir.sharif.ap2021.Listener;

import ir.sharif.ap2021.Controller.ChatController;
import ir.sharif.ap2021.Event.ChatEvent;
import ir.sharif.ap2021.View.ModelView.ChatView;

import java.io.IOException;

public class ChatListener {

    ChatController chatController = new ChatController();
    ChatView chatView;


    public ChatListener(ChatView chatView) {
        this.chatView = chatView;
    }

    public void eventOccurred(ChatEvent formEvent) throws IOException {

        if (formEvent.getOrder().equals("loadChats")) {
            chatController.loadChats(formEvent);
        }

        if (formEvent.getOrder().equals("send")) {
            chatController.send(formEvent);
        }

        if (formEvent.getOrder().equals("open")) {
            chatController.open(formEvent);
        }


    }


}
