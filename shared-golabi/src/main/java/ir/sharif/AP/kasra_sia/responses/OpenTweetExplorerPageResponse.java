package ir.sharif.AP.kasra_sia.responses;

import ir.sharif.AP.kasra_sia.demoModel.DemoTweet;

import java.util.LinkedList;

public class OpenTweetExplorerPageResponse extends Response{
    LinkedList<DemoTweet> tweets;

    public OpenTweetExplorerPageResponse(LinkedList<DemoTweet> tweets) {
        this.tweets = tweets;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.openTweetExplorePage(tweets);
    }
}
