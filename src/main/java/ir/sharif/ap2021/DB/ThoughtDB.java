package ir.sharif.ap2021.DB;

import com.google.gson.Gson;
import ir.sharif.ap2021.Config.MainConfig;
import ir.sharif.ap2021.Model.Thought.Thought;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class ThoughtDB implements DBSet<Thought> {

    MainConfig mainConfig = new MainConfig();
    private static final Logger logger = LogManager.getLogger(DBSet.class);
    private static String thoughtAddress;
    private static final Gson gson = new Gson();

    public ThoughtDB() throws IOException {
        thoughtAddress = mainConfig.getResourcesPath() + "/Thoughts/";
    }

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
                logger.error(e.getMessage());
            }

        }

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
                logger.error(e.getMessage());
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
            logger.error(e.getMessage());
        }

        try {
            FileWriter fileWriter = new FileWriter(thoughtFile);
            gson.toJson(thought, fileWriter);
            fileWriter.flush();
            fileWriter.close();
            logger.info("thought " + thought.getId() + "added");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
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
                    logger.info("thought " + thought.getId() + "updated");
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error(e.getMessage());
                }

            }

        }

    }

    @Override
    public Thought getByName(String username) {
        return null;
    }


}
