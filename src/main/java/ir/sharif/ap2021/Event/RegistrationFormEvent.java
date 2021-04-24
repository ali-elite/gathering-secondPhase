package ir.sharif.ap2021.Event;

import java.util.EventObject;

public class RegistrationFormEvent extends EventObject {

    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;

    public RegistrationFormEvent(Object source, String firstname, String lastname, String username, String email,String password) {
        super(source);
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
