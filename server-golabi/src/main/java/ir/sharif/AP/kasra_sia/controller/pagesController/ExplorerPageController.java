package ir.sharif.AP.kasra_sia.controller.pagesController;


import ir.sharif.AP.kasra_sia.controller.Controller;
import ir.sharif.AP.kasra_sia.model.account.User;

public class ExplorerPageController extends Controller {
    public ExplorerPageController(User owner) {
        super(owner);
    }

    public User searchUser(String username) throws Exception {
        for (User user:context.Users.all()) {
            if (user.getUsername().equals(username) && !user.getUsername().equals(owner.getUsername())) {
                if (!user.getProfile().getBlacklist().contains(owner.getID())) {
                    return user;
                }
            }
        }
            throw new Exception("not found");
    }

}
