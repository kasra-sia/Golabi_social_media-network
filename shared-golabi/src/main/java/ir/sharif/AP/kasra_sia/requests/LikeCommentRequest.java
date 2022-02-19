package ir.sharif.AP.kasra_sia.requests;

import ir.sharif.AP.kasra_sia.responses.Response;

public class LikeCommentRequest extends Request{
    int parentID;
    int id;
    boolean liked;

    public LikeCommentRequest(int parentID, int id, boolean liked) {
        this.parentID = parentID;
        this.id = id;
        this.liked = liked;
    }

    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.likeComment(parentID,id,liked);
    }
}
