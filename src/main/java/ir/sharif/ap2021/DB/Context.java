package ir.sharif.ap2021.DB;

import ir.sharif.ap2021.Model.Notification.Notification;
import ir.sharif.ap2021.Model.Thought.Thought;
import ir.sharif.ap2021.Model.User.User;

public class Context {

    public DBSet<User> Users = new UserDB();
    public DBSet<Thought> Thoughts = new ThoughtDB();
    public DBSet<Notification> Notifications = new NotificationDB();

}