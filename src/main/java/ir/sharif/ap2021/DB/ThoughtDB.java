package ir.sharif.ap2021.DB;

import com.google.gson.Gson;
import ir.sharif.ap2021.Model.Thought.Thought;
import ir.sharif.ap2021.Model.User.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class ThoughtDB implements DBSet<Thought> {

    private static final String thoughtAddress = "./src/main/resources/Thoughts/";
    private static final Gson gson = new Gson();

    @Override
    public Thought get(int id) {

        File us = new File(thoughtAddress);

        for (File f : Objects.requireNonNull(us.listFiles())) {
            try {
                Thought thought = gson.fromJson(new FileReader(f), Thought.class);
                if (thought.getId() == id) {
                    return thought;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        return null;
    }

    @Override
    public Thought getByName(String name) {

        return null;
    }


    @Override
    public ArrayList<Thought> all() {

        ArrayList<Thought> thoughts = new ArrayList<>();

        File us = new File(thoughtAddress);
        for (File f : Objects.requireNonNull(us.listFiles())) {

            try {
                Thought thought = gson.fromJson(new FileReader(f), Thought.class);
                thoughts.add(thought);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        return thoughts;
    }

    @Override
    public void add(Thought thought) {


        File thoughtFile = new File(thoughtAddress + thought.getId() + ".txt");
        if (!thoughtFile.getParentFile().exists()) thoughtFile.getParentFile().mkdirs();

        try {
            thoughtFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter fileWriter = new FileWriter(thoughtFile);
            gson.toJson(thought, fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void remove(Thought thought) {

    }

    @Override
    public void update(Thought thought) {

        File us = new File(thoughtAddress);

        for (File f : Objects.requireNonNull(us.listFiles())) {


            if (f.getName().equals(thought.getId() + ".txt")) {
                try {
                    FileWriter fileWriter = new FileWriter(f);
                    gson.toJson(thought, fileWriter);
                    fileWriter.flush();
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }

    }


}
