package ir.sharif.AP.kasra_sia.controller.pagesController;

import ir.sharif.AP.kasra_sia.controller.Controller;
import ir.sharif.AP.kasra_sia.model.account.User;
import ir.sharif.AP.kasra_sia.requests.eventObjects.MyInfoForm;

public class PersonalPageController extends Controller {
    public PersonalPageController(User owner) {
        super(owner);
    }

    public void saveInfo(MyInfoForm e) throws Exception {
        if (context.Users.all() != null)
            for (User user : context.Users.all()) {
                if (e.getUsername().equals(user.getUsername()) && !e.getUsername().isEmpty() && !e.getUsername().equals(owner.getUsername()))
                    throw new Exception("duplicatedUsername");
                if (e.getEmail().equals(user.getProfile().getEmail()) && !e.getEmail().isEmpty() && !e.getEmail().equals(owner.getProfile().getEmail()))
                    throw new Exception("duplicatedEmail");
                if (e.getPhoneNumber().equals(user.getProfile().getPhoneNumber()) && !e.getPhoneNumber().isEmpty() && !e.getPhoneNumber().equals(owner.getProfile().getPhoneNumber())) {
                    throw new Exception("duplicatedPhoneNumber");
                }
            }
        owner.getProfile().setFirstName(e.getFirstName());
        owner.getProfile().setLastName(e.getLastname());
        owner.setUsername(e.getUsername());
        owner.getProfile().setBio(e.getBio());
        owner.getProfile().setBirthday(e.getBirthday());
        owner.getProfile().setEmail(e.getEmail());
        owner.getProfile().setPhoneNumber(e.getPhoneNumber());
        if (e.getEncodedImage()!=null)
        context.Users.updatePhoto(owner, e.getEncodedImage());
        else removeProfilePhoto();
        logger.info("@"+owner.getUsername()+"updated his account info");
        context.Users.update(owner);
    }

    public void removeProfilePhoto(){
        try{
        context.Users.removePhoto(owner);
            logger.info("@"+owner.getUsername()+" removed his photo");
    }catch (Exception ignore){

        }
    }
}
