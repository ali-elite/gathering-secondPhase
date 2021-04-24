package ir.sharif.ap2021.DB;

import ir.sharif.ap2021.Model.Thought.Thought;
import ir.sharif.ap2021.Model.User.User;

public class Context {

    public DBSet<User> Users = new UserDB();
    public DBSet<Thought> Thoughts = new ThoughtDB();

}