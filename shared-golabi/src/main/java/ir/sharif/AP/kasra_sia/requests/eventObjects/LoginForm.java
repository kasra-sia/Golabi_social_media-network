package ir.sharif.AP.kasra_sia.requests.eventObjects;

import java.util.EventObject;

public class LoginForm extends EventObject {

    private String username;
    private String password;

    public LoginForm(Object source, String username, String password) {
        super(source);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
