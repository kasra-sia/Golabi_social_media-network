package ir.sharif.AP.kasra_sia.requests;

import ir.sharif.AP.kasra_sia.responses.Response;

public class LikeTweetRequest extends Request{
    int id;
    boolean liked;

    public LikeTweetRequest(int id,boolean liked) {
        this.id = id;
        this.liked =liked;
    }

    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.likeTweet(id,liked);
    }
}
