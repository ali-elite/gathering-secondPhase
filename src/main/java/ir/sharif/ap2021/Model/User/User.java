package ir.sharif.ap2021.Model.User;

import ir.sharif.ap2021.Config.ImageConfig;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;


public class User {


    ImageConfig imageConfig = new ImageConfig();

    public String firstName;
    public String lastName;
    public String userName;
    public String biography;
    private LocalDate birthday;
    private String avatar;
    private String email;
    private String phoneNumber;
    private String password;
    private String LastSeenPrivacy = "Public";
    private int id;
    private final int nextId = 122;
    private boolean isActive = true;
    private boolean isPrivate = false;
    private boolean isDeleted = false;
    private long reportedTimes = 0;
    private final LocalDateTime lastSeen;
    private final ArrayList<Integer> thoughts;
    private final ArrayList<Integer> followers;
    private final ArrayList<Integer> followings;
    private final ArrayList<Integer> blackList;
    private final ArrayList<Integer> muteList;
    private final ArrayList<Integer> notifications;
    private final ArrayList<Integer> chats;
    private static String userAddress;


    public User(String firstName, String lastName, String userName, String email, String password) throws IOException {

        userAddress = imageConfig.getMainConfig().getResourcesPath() + "/Users";

        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.id = nextId + Objects.requireNonNull(new File(userAddress).listFiles()).length;
        this.lastSeen = LocalDateTime.now();
        this.avatar = imageConfig.getUser();
        thoughts = new ArrayList<>();
        followers = new ArrayList<>();
        followings = new ArrayList<>();
        blackList = new ArrayList<>();
        notifications = new ArrayList<>();
        muteList = new ArrayList<>();
        chats = new ArrayList<>();

    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public ArrayList<Integer> getChats() {
        return chats;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLSPublic() {
        this.LastSeenPrivacy = "Public";
    }

    public void setLSPrivate() {
        this.LastSeenPrivacy = "Private";
    }

    public void setLSSemiPrivate() {
        this.LastSeenPrivacy = "SemiPrivate";
    }

    public String getLastSeenPrivacy() {
        return LastSeenPrivacy;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Integer> getThoughts() {
        return thoughts;
    }

    public String getUserName() {
        return userName;
    }

    public ArrayList<Integer> getFollowers() {
        return followers;
    }

    public ArrayList<Integer> getFollowings() {
        return followings;
    }

    public ArrayList<Integer> getBlackList() {
        return blackList;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBiography() {
        return biography;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDateTime getLastSeen() {
        return lastSeen;
    }

    public ArrayList<Integer> getNotifications() {
        return notifications;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void addReport() {
        reportedTimes++;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public long getReportedTimes() {
        return reportedTimes;
    }

    public void setReportedTimes(long reportedTimes) {
        this.reportedTimes = reportedTimes;
    }

    public ArrayList<Integer> getMuteList() {
        return muteList;
    }

}
