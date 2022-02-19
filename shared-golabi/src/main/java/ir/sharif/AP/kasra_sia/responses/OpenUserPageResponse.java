package ir.sharif.AP.kasra_sia.responses;

import ir.sharif.AP.kasra_sia.requests.eventObjects.SetUserViewForm;

public class OpenUserPageResponse extends Response {
    SetUserViewForm form;
    public OpenUserPageResponse(SetUserViewForm form) {
        this.form = form;
    }
    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.openUserPage(form);
    }
}
