package ir.sharif.ap2021.Controller;

import ir.sharif.ap2021.DB.Context;
import ir.sharif.ap2021.Event.EditProfileEvent;
import ir.sharif.ap2021.Model.User.User;
import ir.sharif.ap2021.Validation.AuthenticationException;

public class EditProfileController {

    private Context context = new Context();

    public void edit(EditProfileEvent event) throws AuthenticationException {

        if (!emailCheck(event.getEmail())) {
            throw new AuthenticationException("Email Already Exists!");
        }

        if (!usernameCheck(event.getUser())) {
            throw new AuthenticationException("Username Already Exists!");
        }


        User user = StaticController.getMyUser();
        user.setFirstName(event.getFirst());
        user.setLastName(event.getLast());
        user.setUserName(event.getUser());
        user.setPhoneNumber(event.getPhone());
        user.setEmail(event.getEmail());
        user.setBirthday(event.getBirthday());
        user.setBiography(event.getBio());

        if (event.getAvatar().equals("change")) {

            String pathName = StaticController.getMyUser().getAvatar();
            String avatar;

            if (pathName.equals("/images/user.png")) {
                avatar = "/Avatars/" + StaticController.getMyUser().getId() +".png";
            } else {
                avatar = pathName.substring(0, pathName.length() - 4) + "-1.png";
            }

            user.setAvatar(avatar);
        }


        context.Users.update(user);
    }

    public boolean emailCheck(String email) {

        boolean check = true;

        for (User user : context.Users.all()) {
            if (user.getEmail().equals(email) && !user.getEmail().equals(StaticController.getMyUser().getEmail())) {
                check = false;
                break;
            }
        }

        return check;
    }

    public boolean usernameCheck(String username) {

        boolean check = true;

        for (User user : context.Users.all()) {
            if (user.getUserName().equals(username) && !user.getUserName().equals(StaticController.getMyUser().getUserName())) {
                check = false;
                break;
            }
        }

        return check;
    }




}
