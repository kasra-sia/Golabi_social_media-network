package ir.sharif.AP.kasra_sia.requests;

import ir.sharif.AP.kasra_sia.requests.eventObjects.CreatCommentForm;
import ir.sharif.AP.kasra_sia.responses.Response;

public class AddCommentRequest extends Request{
    CreatCommentForm form;
    private int parentID;

    public AddCommentRequest(CreatCommentForm form,int parentID) {
        this.form = form;
        this.parentID = parentID;
    }

    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.newComment(form,parentID);
    }
}
