package ir.sharif.AP.kasra_sia.listeners;


import ir.sharif.AP.kasra_sia.demoModel.DemoTweet;
import ir.sharif.AP.kasra_sia.requests.DeleteTweetRequest;
import ir.sharif.AP.kasra_sia.requests.LikeTweetRequest;
import ir.sharif.AP.kasra_sia.requests.OpenCommentsPageRequest;

public class TweetListener<View> implements StringListener {
    private DemoTweet tweet;
    private RequestListener requestListener;
    public TweetListener(DemoTweet tweet,RequestListener requestListener) {
        this.tweet = tweet;
        this.requestListener = requestListener;
    }
    @Override
    public void stringEventOccurred(String string) {
        if (string.equals("delete")) {
                requestListener.listen(new DeleteTweetRequest(tweet.getID()));
        }
        if (string.equals("comment")) {
                requestListener.listen(new OpenCommentsPageRequest(tweet.getID()));
            }

        if (string.equals("like")) {
            requestListener.listen(new LikeTweetRequest(tweet.getID(),true));
        }
        if (string.equals("unLike")) {
            requestListener.listen(new LikeTweetRequest(tweet.getID(),false));
        }
        if (string.equals("retweet")) {

        }
//        if (string.equals("report"))
//            controller.report(tweet);
    }
}
