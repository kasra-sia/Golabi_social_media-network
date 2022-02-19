package ir.sharif.AP.kasra_sia.requests;

import ir.sharif.AP.kasra_sia.responses.Response;

public class DeleteTweetRequest extends Request{
    int id;

    public DeleteTweetRequest(int id) {
        this.id = id;
    }

    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.deleteTweet(id);
    }
}
