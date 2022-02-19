package ir.sharif.AP.kasra_sia.responses;

import ir.sharif.AP.kasra_sia.demoModel.DemoTweet;

import java.util.LinkedList;

public class OpenTimeLinePageResponse extends Response{
    LinkedList<DemoTweet> tweets;

    public OpenTimeLinePageResponse(LinkedList<DemoTweet> tweets) {
        this.tweets = tweets;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.openTimeLinePage(tweets);
    }
}
