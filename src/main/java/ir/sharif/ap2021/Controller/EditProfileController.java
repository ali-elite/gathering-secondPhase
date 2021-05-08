package ir.sharif.ap2021.Controller;

import ir.sharif.ap2021.Config.ErrorConfig;
import ir.sharif.ap2021.DB.Context;
import ir.sharif.ap2021.Event.EditProfileEvent;
import ir.sharif.ap2021.Model.User.User;
import ir.sharif.ap2021.Validation.AuthenticationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class EditProfileController {


    ErrorConfig errorConfig = new ErrorConfig();

    private static final Logger logger = LogManager.getLogger(EditProfileController.class);
    private Context context = new Context();

    public EditProfileController() throws IOException {
    }

    public void edit(EditProfileEvent event) throws AuthenticationException {

        if (!emailCheck(event.getEmail())) {
            throw new AuthenticationException(errorConfig.getEmailExists());
        }

        if (!usernameCheck(event.getUser())) {
            throw new AuthenticationException(errorConfig.getUserExists());
        }


        User user = StaticController.getMyUser();

        user.setFirstName(event.getFirst());
        logger.info("user " + user.getId() + " has changed his/her firstname to " + event.getFirst());

        user.setLastName(event.getLast());
        logger.info("user " + user.getId() + " has changed his/her lastname to " + event.getLast());

        user.setUserName(event.getUser());
        logger.info("user " + user.getId() + " has changed his/her username to " + event.getUser());

        user.setPhoneNumber(event.getPhone());
        logger.info("user " + user.getId() + " has changed his/her phoneNumber to " + event.getPhone());

        user.setEmail(event.getEmail());
        logger.info("user " + user.getId() + " has changed his/her email to " + event.getEmail());

        user.setBirthday(event.getBirthday());
        logger.info("user " + user.getId() + " has changed his/her birthday to " + event.getBirthday());

        user.setBiography(event.getBio());
        logger.info("user " + user.getId() + " has changed his/her biography to " + event.getBio());

        if (event.getAvatar().equals("change")) {
            user.setAvatar("/Avatars/" + user.getId() + ".png");
            logger.info("user " + user.getId() + " has changed his/her avatar");
        }

        context.Users.update(user);
    }

    public boolean emailCheck(String email) {

        boolean check = true;

        for (User user : context.Users.all()) {
            if (user.getEmail().equals(email) && !user.getEmail().equals(StaticController.getMyUser().getEmail())
                    && !user.isDeleted()) {
                check = false;
                break;
            }
        }

        return check;
    }

    public boolean usernameCheck(String username) {

        boolean check = true;

        for (User user : context.Users.all()) {
            if (user.getUserName().equals(username) && !user.getUserName().equals(StaticController.getMyUser().getUserName())
                    && !user.isDeleted()) {
                check = false;
                break;
            }
        }

        return check;
    }


}
