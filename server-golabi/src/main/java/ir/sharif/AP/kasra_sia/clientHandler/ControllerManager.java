package ir.sharif.AP.kasra_sia.clientHandler;

import ir.sharif.AP.kasra_sia.controller.AuthController;
import ir.sharif.AP.kasra_sia.controller.Controller;
import ir.sharif.AP.kasra_sia.controller.TweetController;
import ir.sharif.AP.kasra_sia.controller.UserController;
import ir.sharif.AP.kasra_sia.controller.pagesController.ExplorerPageController;
import ir.sharif.AP.kasra_sia.controller.pagesController.PersonalPageController;
import ir.sharif.AP.kasra_sia.controller.pagesController.SettingsPageController;
import ir.sharif.AP.kasra_sia.model.account.User;

public class ControllerManager {
    Controller controller;
    AuthController authController;
    SettingsPageController settingsPageController;
    PersonalPageController personalPageController;
    TweetController tweetController;
    UserController userController;
    ExplorerPageController explorerPageController;
    public ControllerManager() {
        authController = new AuthController();
    }

    public void initialize() {
        controller = new Controller(authController.getOwner());
        userController = new UserController(authController.getOwner());
        settingsPageController = new SettingsPageController(authController.getOwner());
        personalPageController = new PersonalPageController(authController.getOwner());
        tweetController = new TweetController(authController.getOwner());
        explorerPageController = new ExplorerPageController(authController.getOwner());
    }
}
