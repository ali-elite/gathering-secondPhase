package ir.sharif.ap2021.Controller;

import ir.sharif.ap2021.DB.Context;
import ir.sharif.ap2021.Model.Thought.Thought;
import ir.sharif.ap2021.Model.User.User;
import ir.sharif.ap2021.View.Menu.Mainmenu;
import ir.sharif.ap2021.View.Menu.Notifications;
import ir.sharif.ap2021.View.ModelView.ThoughtView;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MainMenuController {


    private Context context = new Context();


    public void load(Mainmenu mainmenu, String username) throws IOException {

        StaticController.setMyUser(context.Users.getByName(username));
        mainmenu.show();

    }


    public void gatherThought(Mainmenu mainmenu) throws IOException {


        Mainmenu.getThoughts().clear();
        for (int i = StaticController.getMyUser().getThoughts().size() - 1; i > -1; i--) {

            ThoughtView thoughtView = new ThoughtView();
            Thought thought = context.Thoughts.get(StaticController.getMyUser().getThoughts().get(i));

            thoughtView.setThought(thought);
            thoughtView.setOwnerUser(context.Users.get(thought.getUser()));
            thoughtView.setMainUser(StaticController.getMyUser());


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/thought.fxml"));
            loader.setController(thoughtView);

            Mainmenu.getThoughts().add((Pane) loader.load());
        }

    }



    public void timeLineThought(Mainmenu mainmenu) throws IOException {

        User mainUser = StaticController.getMyUser();

        ArrayList<User> followers = new ArrayList<>();
        for (Integer i : mainUser.getFollowers()) {
            followers.add(context.Users.get(i));
        }

        ArrayList<User> followings = new ArrayList<>();
        for (Integer i : mainUser.getFollowings()) {
            followings.add(context.Users.get(i));
        }

        ArrayList<Thought> thoughts = new ArrayList<>();

        Mainmenu.getThoughts().clear();

        //mainUserThoughts
        if (!mainUser.getMuteList().contains(mainUser.getId()) && mainUser.isActive()) {

            for (int i = mainUser.getThoughts().size() - 1; i > -1; i--) {

                Thought thought = context.Thoughts.get(mainUser.getThoughts().get(i));

                if (!thoughts.contains(thought)
                        && thought.getLocalDateTime().isAfter(LocalDateTime.now().minusDays(1))) {

                    ThoughtView thoughtView = new ThoughtView();
                    thoughtView.setParent(0);

                    thoughtView.setThought(thought);
                    thoughtView.setOwnerUser(context.Users.get(context.Thoughts.get(mainUser.getThoughts().get(i)).getUser()));
                    thoughtView.setMainUser(StaticController.getMyUser());
                    thoughtView.setDoedUser(context.Users.get(thought.getDoed()));


                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/thought.fxml"));
                    loader.setController(thoughtView);


                    Mainmenu.getThoughts().add((Pane) loader.load());

                    thoughts.add(thought);
                }


            }
        }

        for (User follower : followers) {

            if (!mainUser.getMuteList().contains(follower.getId()) && follower.isActive()) {

                for (int i = follower.getThoughts().size() - 1; i > -1; i--) {

                    Thought thought = context.Thoughts.get(follower.getThoughts().get(i));

                    if (!thoughts.contains(thought)
                    && thought.getLocalDateTime().isAfter(LocalDateTime.now().minusDays(1))) {

                        ThoughtView thoughtView = new ThoughtView();
                        thoughtView.setParent(0);

                        thoughtView.setThought(thought);
                        thoughtView.setOwnerUser(context.Users.get(context.Thoughts.get(follower.getThoughts().get(i)).getUser()));
                        thoughtView.setMainUser(StaticController.getMyUser());
                        thoughtView.setDoedUser(context.Users.get(thought.getDoed()));


                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/thought.fxml"));
                        loader.setController(thoughtView);


                        Mainmenu.getThoughts().add((Pane) loader.load());

                        thoughts.add(thought);
                    }


                }
            }
        }

        for (User following : followings) {

            if (!mainUser.getMuteList().contains(following.getId()) && following.isActive()) {

                for (int i = following.getThoughts().size() - 1; i > -1; i--) {

                    Thought thought = context.Thoughts.get(following.getThoughts().get(i));

                    if (!thoughts.contains(thought)
                            && thought.getLocalDateTime().isAfter(LocalDateTime.now().minusDays(1))) {

                        ThoughtView thoughtView = new ThoughtView();
                        thoughtView.setParent(0);

                        thoughtView.setThought(thought);
                        thoughtView.setOwnerUser(context.Users.get(context.Thoughts.get(following.getThoughts().get(i)).getUser()));
                        thoughtView.setMainUser(StaticController.getMyUser());
                        thoughtView.setDoedUser(context.Users.get(thought.getDoed()));


                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/thought.fxml"));
                        loader.setController(thoughtView);


                        Mainmenu.getThoughts().add((Pane) loader.load());

                        thoughts.add(thought);
                    }

                }
            }
        }

    }

    public void exploreThought(Mainmenu mainmenu) throws IOException {


        Mainmenu.getThoughts().clear();
        for (int i = 3 - 1; i > -1; i--) {

            ThoughtView thoughtView = new ThoughtView();
            thoughtView.setParent(0);

            Thought thought = context.Thoughts.all().get(i);
            thoughtView.setThought(thought);
            thoughtView.setOwnerUser(context.Users.get(thought.getUser()));
            thoughtView.setMainUser(StaticController.getMyUser());
            thoughtView.setDoedUser(context.Users.get(thought.getDoed()));


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/thought.fxml"));
            loader.setController(thoughtView);


            Mainmenu.getThoughts().add((Pane) loader.load());
        }
    }




    public void notif(Mainmenu mainmenu) throws IOException {

        Notifications notifications = new Notifications();
        notifications.show();

    }
}
