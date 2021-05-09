package ir.sharif.ap2021.DB;

import ir.sharif.ap2021.Model.Chat.Chat;
import ir.sharif.ap2021.Model.Chat.Message;
import ir.sharif.ap2021.Model.Notification.Notification;
import ir.sharif.ap2021.Model.Thought.Thought;
import ir.sharif.ap2021.Model.User.User;

import java.io.IOException;

public class Context {

    public DBSet<User> Users = new UserDB();
    public DBSet<Thought> Thoughts = new ThoughtDB();
    public DBSet<Notification> Notifications = new NotificationDB();
    public DBSet<Message> Messages = new MessageDB();
    public DBSet<Chat> Chats = new ChatDB();


    public Context() throws IOException {
    }

}