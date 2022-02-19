package ir.sharif.AP.kasra_sia.requests.eventObjects;

import java.util.EventObject;

public class ChangePasswordForm extends EventObject {
    private String oldPassword;
    private String newPassword;
    public ChangePasswordForm(Object source, String oldPassword, String newPassword) {
        super(source);
       this.newPassword = newPassword;
       this.oldPassword = oldPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
