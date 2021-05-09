package ir.sharif.ap2021.Controller;

import ir.sharif.ap2021.Config.ErrorConfig;
import ir.sharif.ap2021.DB.Context;
import ir.sharif.ap2021.Event.RegistrationFormEvent;
import ir.sharif.ap2021.Model.User.User;
import ir.sharif.ap2021.Validation.AuthenticationException;

import java.io.IOException;

public class RegistrationController {

    ErrorConfig errorConfig = new ErrorConfig();
    private Context context;

    public RegistrationController() throws IOException {
        context = new Context();
    }

    public void register(RegistrationFormEvent e) throws AuthenticationException, IOException {

        if(!emailCheck(e.getEmail())){
            throw new AuthenticationException(errorConfig.getEmailExists());
        }

        if(!usernameCheck(e.getUsername())){
            throw new AuthenticationException(errorConfig.getUserExists());
        }

        User user = new User(e.getFirstname(),e.getLastname(),e.getUsername(),e.getEmail(),e.getPassword());
        context.Users.add(user);

    }

    public boolean emailCheck(String email){

        boolean check = true;

        for (User user :context.Users.all()) {
            if (user.getEmail().equals(email) && !user.isDeleted()) {
                check = false;
                break;
            }
        }

        return check;
    }

    public boolean usernameCheck(String username){

        boolean check = true;

        for (User user :context.Users.all()) {
            if (user.getUserName().equals(username) && !user.isDeleted()) {
                check = false;
                break;
            }
        }

        return check;
    }


}
