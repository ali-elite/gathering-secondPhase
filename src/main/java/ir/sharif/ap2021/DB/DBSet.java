package ir.sharif.ap2021.DB;

import java.util.ArrayList;

public interface DBSet<T> {

    T get(int id);

    ArrayList<T> all();

    void add(T t);

    void remove(T t);

    void update(T t);

    T getByName(String username);
}
