package ir.sharif.AP.kasra_sia.responses;

import ir.sharif.AP.kasra_sia.demoModel.DemoTweet;

import java.util.LinkedList;

public class OpenMyTweetsPageResponse extends Response{
    LinkedList<DemoTweet> myTweets;

    public OpenMyTweetsPageResponse(LinkedList<DemoTweet> myTweets) {
        this.myTweets = myTweets;
    }
    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.openMyTweetsPage(myTweets);
    }
}
