package ir.sharif.AP.kasra_sia.requests;

import ir.sharif.AP.kasra_sia.requests.eventObjects.CreatTweetForm;
import ir.sharif.AP.kasra_sia.responses.Response;

public class AddTweetRequest extends Request{
    CreatTweetForm form;

    public AddTweetRequest(CreatTweetForm form) {
        this.form = form;
    }

    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.newTweet(form);
    }
}
