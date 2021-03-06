package ir.sharif.ap2021.Event;

import java.time.LocalDate;
import java.util.EventObject;

public class EditProfileEvent extends EventObject {

    private String first;
    private String last;
    private String user;
    private String phone;
    private String email;
    private String bio;
    private String avatar;
    private LocalDate birthday;

    public EditProfileEvent(Object source, String first, String last, String user, String phone, String email, String bio, String avatar, LocalDate birthday) {
        super(source);
        this.first = first;
        this.last = last;
        this.user = user;
        this.phone = phone;
        this.email = email;
        this.bio = bio;
        this.avatar = avatar;
        this.birthday = birthday;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public String getUser() {
        return user;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getBio() {
        return bio;
    }

    public String getAvatar() {
        return avatar;
    }

    public LocalDate getBirthday() {
        return birthday;
    }
}
