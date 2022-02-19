package ir.sharif.AP.kasra_sia.requests;

import ir.sharif.AP.kasra_sia.requests.eventObjects.ChangePasswordForm;
import ir.sharif.AP.kasra_sia.responses.Response;

public class ChangePasswordRequest extends Request{
    ChangePasswordForm changePasswordForm;

    public ChangePasswordRequest(ChangePasswordForm changePasswordForm) {
        this.changePasswordForm = changePasswordForm;
    }

    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.changePassword(changePasswordForm);
    }
}
