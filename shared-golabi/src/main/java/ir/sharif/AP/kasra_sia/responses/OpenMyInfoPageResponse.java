package ir.sharif.AP.kasra_sia.responses;

import ir.sharif.AP.kasra_sia.requests.eventObjects.MyInfoForm;

public class OpenMyInfoPageResponse extends Response {
    MyInfoForm myInfoForm ;

    public OpenMyInfoPageResponse(MyInfoForm myInfoForm) {
        this.myInfoForm = myInfoForm;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.openMyInfoPage(myInfoForm);
    }
}
