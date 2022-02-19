package ir.sharif.AP.kasra_sia.requests;

import ir.sharif.AP.kasra_sia.responses.Response;

public class OpenCommentsPageRequest extends Request{
    int parentId;

    public OpenCommentsPageRequest(int parentId) {
        this.parentId = parentId;
    }

    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.openCommentsPage(parentId);
    }
}
