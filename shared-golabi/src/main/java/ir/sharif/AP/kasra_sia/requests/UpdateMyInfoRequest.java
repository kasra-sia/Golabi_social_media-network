package ir.sharif.AP.kasra_sia.requests;

import ir.sharif.AP.kasra_sia.requests.eventObjects.MyInfoForm;
import ir.sharif.AP.kasra_sia.responses.Response;

public class UpdateMyInfoRequest extends Request{
    MyInfoForm myInfoForm ;

    public UpdateMyInfoRequest(MyInfoForm myInfoForm) {
        this.myInfoForm = myInfoForm;
    }

    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.updateMyInfo(myInfoForm);
    }
}
