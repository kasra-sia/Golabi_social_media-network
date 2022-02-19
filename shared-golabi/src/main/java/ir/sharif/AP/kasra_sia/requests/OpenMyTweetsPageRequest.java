package ir.sharif.AP.kasra_sia.requests;

import ir.sharif.AP.kasra_sia.demoModel.DemoTweet;
import ir.sharif.AP.kasra_sia.responses.Response;

import java.util.LinkedList;

public class OpenMyTweetsPageRequest extends Request{


    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.openMyTweetsPage();
    }
}
