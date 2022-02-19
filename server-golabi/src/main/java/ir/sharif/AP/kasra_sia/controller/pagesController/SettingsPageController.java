package ir.sharif.AP.kasra_sia.controller.pagesController;

import ir.sharif.AP.kasra_sia.controller.Controller;
import ir.sharif.AP.kasra_sia.model.*;
import ir.sharif.AP.kasra_sia.model.account.User;
import ir.sharif.AP.kasra_sia.requests.eventObjects.ChangePasswordForm;

public class SettingsPageController extends Controller {
    public SettingsPageController(User owner) {
        super(owner);
    }

    public void setAccountPrivacy(boolean isPrivate){
        if (isPrivate)
            setAccountPrivate();
        else setAccountPublic();
    }
    private void setAccountPrivate(){
        owner.getProfile().setPrivate(true);
        logger.info("@"+owner.getUsername()+" set his account public");
        context.Users.update(owner);
    }
    private void setAccountPublic(){
        owner.getProfile().setPrivate(false);
        logger.info("@"+owner.getUsername()+" set his account private ");
        context.Users.update(owner);
    }
    public void setLastSeenType(LastSeen lastSeen){
        switch (lastSeen){
            case FOLLOWINGS : setLastSeenFollowings(); break;
            case EVERYONE: setLastSeenEveryBody();break;
            case NOBODY:setLastSeenNoBody();break;
        }
    }
    private void setLastSeenNoBody(){
        owner.getProfile().setLastSeen(LastSeen.NOBODY);
        logger.info("@"+owner.getUsername()+" set his lastSeen "+LastSeen.NOBODY.toString());
        context.Users.update(owner);
    }
    private void setLastSeenEveryBody(){
        owner.getProfile().setLastSeen(LastSeen.EVERYONE);
        logger.info("@"+owner.getUsername()+" set his lastSeen "+LastSeen.EVERYONE.toString());
        context.Users.update(owner);
    }
    private void setLastSeenFollowings(){
        owner.getProfile().setLastSeen(LastSeen.FOLLOWINGS);
        logger.info("@"+owner.getUsername()+" set his lastSeen "+LastSeen.FOLLOWINGS.toString());
        context.Users.update(owner);
    }

    public void changePassword(ChangePasswordForm e) throws Exception {
        if (e.getOldPassword().equals(owner.getPassword())) {
            owner.setPassword(e.getNewPassword());
            context.Users.update(owner);
            logger.info("@"+owner.getUsername()+"changed his password");
        }else throw new Exception("wrongPassword");
    }


}
