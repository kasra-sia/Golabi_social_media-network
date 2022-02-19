package ir.sharif.AP.kasra_sia.graphic.view.pagesView.TimeLinePageView;

import ir.sharif.AP.kasra_sia.demoModel.DemoTweet;
import ir.sharif.AP.kasra_sia.graphic.view.ComponentsScrollPanel;
import ir.sharif.AP.kasra_sia.graphic.view.modelsView.TweetView;
import ir.sharif.AP.kasra_sia.graphic.view.pagesView.Page;
import ir.sharif.AP.kasra_sia.listeners.RequestListener;
import ir.sharif.AP.kasra_sia.listeners.TweetListener;
import ir.sharif.AP.kasra_sia.requests.Request;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class TimeLinePageView extends Page {
    private ComponentsScrollPanel componentsScrollPanel = new ComponentsScrollPanel();
    private RequestListener requestListener;
    public TimeLinePageView(RequestListener requestListener) {
        this.requestListener = requestListener;
        this.setLayout(new BorderLayout());
        this.add(componentsScrollPanel,BorderLayout.CENTER);
    }
    public void setPage(LinkedList<DemoTweet> tweets){
        LinkedList<TweetView> tweetViews = new LinkedList<>();
        tweets.forEach(tweet->{
            TweetView tweetView = new TweetView(
                    tweet.getText(),
                    tweet.getUsername(),
                    tweet.getEncodedProfilePhoto(),
                    tweet.getEncodedTweetImage(),
                    tweet.getTime(),
                    tweet.getLikes(),
                    tweet.isOwnerIsLiked(),
                    tweet.isMyTweet(),
                    tweet.getID()
            );
            tweetViews.add(tweetView);
            tweetView.setStringListener(new TweetListener<TweetView>(tweet,requestListener));
        });
        componentsScrollPanel.setComponentsList(tweetViews);
        repaint();
        revalidate();
    }

}
