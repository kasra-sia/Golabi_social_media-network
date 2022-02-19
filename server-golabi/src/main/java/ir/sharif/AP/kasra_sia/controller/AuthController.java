package ir.sharif.AP.kasra_sia.controller;


import ir.sharif.AP.kasra_sia.util.*;
import ir.sharif.AP.kasra_sia.requests.eventObjects.*;
import ir.sharif.AP.kasra_sia.model.account.*;
public class AuthController extends Controller {


    public AuthController() {
    }

    public User register(RegistrationForm e) throws Exception {
        if (context.Users.all()!=null)
        for (User user:context.Users.all()) {
            if (e.getUsername().equals(user.getUsername())&& !e.getUsername().isEmpty()) {
                logger.warn("registration warning : duplicatedUsername");
                throw new Exception("duplicatedUsername");
            }
            if (e.getEmail().equals(user.getProfile().getEmail())&& !e.getEmail().isEmpty()) {
                logger.warn("registration warning : duplicatedEmail");
                throw new Exception("duplicatedEmail");
            }
            if (e.getPhoneNumber().equals(user.getProfile().getPhoneNumber()) && !e.getPhoneNumber().isEmpty()) {
                logger.warn("registration warning : duplicatedPhoneNumber");
                throw new Exception("duplicatedPhoneNumber");
            }
        }
        Profile profile = new Profile(e.getFirstName(), e.getLastname(), e.getEmail(), e.getBio(), e.getBirthday(), e.getPhoneNumber());
        User user = new User(e.getUsername(), e.getPassword1(), profile);
        context.Users.add(user);
        owner = user;
        owner.setUserStatus(UserStatus.ONLINE);
        context.Users.update(owner);
        logger.info("@"+owner.getUsername()+" has signup to golabi");
        return user;

    }

    public User login(LoginForm e) throws Exception {

        if (context.Users.all() != null)
        for (User user : context.Users.all()) {
            if (user.getUsername().equals(e.getUsername())
                    && user.getPassword().equals(e.getPassword())) {
                owner = user;
                owner.setUserStatus(UserStatus.ONLINE);
                context.Users.update(owner);
                logger.info("@"+owner.getUsername()+" has signed in to golabi");

                return user;
            }
        }
        logger.warn("wrong username or password");
        throw new Exception("wrongUsernameOrPassword");

    }
    public void logout(){
        owner.setUserStatus(UserStatus.OFFLINE);
        owner.getProfile().setLastSeenTime(CurrentTimeToString.get());
        Controller.logger.info("@"+owner.getUsername()+" logged out");
        context.Users.update(owner);
        owner = null;
    }
}