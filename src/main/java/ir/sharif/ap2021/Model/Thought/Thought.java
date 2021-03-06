package ir.sharif.ap2021.Model.Thought;

import ir.sharif.ap2021.Config.MainConfig;
import ir.sharif.ap2021.Model.User.User;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;


public class Thought {

MainConfig mainConfig = new MainConfig();

    private int user;
    private int doed;
    private int parent;
    private String type;
    private String image;
    private long likes;
    private long spamReports;
    private String text;
    private final ArrayList<Integer> opinions;
    private final ArrayList<Integer> likers;
    private final ArrayList<Integer> rethoughters;


    private LocalDateTime localDateTime;
    private long rethought;
    private static int nextId = 79127;
    private int id;
    private static String thoughtAddress;



    public Thought(String type, User user, User doed, String text, LocalDateTime localDateTime) throws IOException {

        thoughtAddress = mainConfig.getResourcesPath() +"/Thoughts";

        this.type = type;
        this.localDateTime = localDateTime;
        this.user = user.getId();
        if (type.equalsIgnoreCase("t")) {
            this.doed = 0;
        } else this.doed = doed.getId();
        this.text = text;
        this.id = nextId + Objects.requireNonNull(new File(thoughtAddress).listFiles()).length;

        likes = 0;
        spamReports = 0;
        rethought = 0;
        opinions = new ArrayList<>();
        likers = new ArrayList<>();
        rethoughters = new ArrayList<>();

    }


    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public ArrayList<Integer> getLikers() {
        return likers;
    }

    public ArrayList<Integer> getOpinions() {
        return opinions;
    }

    public String getText() {
        return text;
    }

    public int getId() {
        return id;
    }

    public long getLikes() {
        return likes;
    }

    public long getSpamReports() {
        return spamReports;
    }

    public long getRethought() {
        return rethought;
    }

    public int getUser() {
        return user;
    }

    public ArrayList<Integer> getRethoughters() {
        return rethoughters;
    }

    public int getDoed() {
        return doed;
    }

    public String getType() {
        return type;
    }

    public void addRethought() {
        rethought++;
    }

    public void minusRethought() {
        if (rethought != 0) {
            rethought--;
        }
    }

    public void addLike() {
        likes++;
    }

    public void minusLike() {
        if (likes != 0) {
            likes--;
        }
    }

    public void addSpam() {
        spamReports++;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
