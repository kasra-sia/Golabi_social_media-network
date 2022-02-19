package ir.sharif.AP.kasra_sia.controller;


import ir.sharif.AP.kasra_sia.db.Context;
import ir.sharif.AP.kasra_sia.model.account.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Controller {
    public static volatile Logger logger = LogManager.getLogger(Controller.class);
    protected Context context;
    protected User owner;
    public Controller(User owner){
        this.owner = owner;
        context = new Context();
    }
    public Controller(){
        context = new Context();
    }

    public Context getContext() {
        return context;
    }

    public  User getOwner() {
        return owner;
    }


}