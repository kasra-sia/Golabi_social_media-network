package ir.sharif.AP.kasra_sia.controller;


import ir.sharif.AP.kasra_sia.model.Comment;
import ir.sharif.AP.kasra_sia.model.Tweet;
import ir.sharif.AP.kasra_sia.model.account.User;
import ir.sharif.AP.kasra_sia.requests.eventObjects.CreatCommentForm;
import ir.sharif.AP.kasra_sia.requests.eventObjects.CreatTweetForm;

public class TweetController extends Controller{
    public TweetController(User owner) {
        super(owner);
    }

    public void creatTweet(CreatTweetForm e){

        if (!e.getText().isEmpty() && e.getEncodedImage() == null){
            Tweet tweet = new Tweet(e.getText(),owner.getID());
            logger.info("@"+owner.getUsername()+" posted a new tweet ");
            context.Tweets.add(tweet);
        }
        if (e.getEncodedImage() != null && !e.getText().isEmpty()){
            Tweet tweet = new Tweet(e.getText(),owner.getID());
            context.Tweets.add(tweet);
            logger.info("@"+owner.getUsername()+" posted a new tweet ");
            context.Tweets.updatePhoto(tweet,e.getEncodedImage());
        }
        if (!e.getText().isEmpty() && e.getEncodedImage() != null){
        }
    }
    public void deleteTweet(int id){
        Tweet tweet = context.Tweets.get(id);
        logger.info("@"+owner.getUsername()+" delete a tweet ");
        context.Tweets.remove(tweet);
    }
    public void likeTweet(int id,boolean liked){
        Tweet tweet = context.Tweets.get(id);
        if (tweet.getLikes().contains(owner.getID()) && !liked){
            tweet.getLikes().remove(Integer.valueOf(owner.getID()));
        }
        if (!tweet.getLikes().contains(owner.getID()) && liked)
            tweet.getLikes().add(owner.getID());
        context.Tweets.update(tweet);
    }
    public void likeComment(int parentID,int id,boolean liked){
        Tweet tweet = context.Tweets.get(parentID);
        Comment comment = tweet.getComments().get(Integer.valueOf(id));
            if (comment.getLikes().contains(owner.getID()) && !liked) {
                comment.getLikes().remove(Integer.valueOf(owner.getID()));
            }
            if (!comment.getLikes().contains(owner.getID()) && liked)
                comment.getLikes().add(owner.getID());
        context.Tweets.update(tweet);
    }

    public void addComment(CreatCommentForm event, int id) {
        if (!event.getText().isEmpty()) {
            Tweet tweet =context.Tweets.get(id);
            System.out.println(id);
            tweet.getComments().add(new Comment(owner.getID(), event.getText()));
            context.Tweets.update(tweet);
            logger.info("@"+owner.getUsername()+" add a comment ");
        }
    }
    public void report(Tweet tweet) {
        UserController userController = new UserController(owner);
        if (tweet.getID() != owner.getID()) {
            userController.block(context.Users.get(tweet.getUserID()));
//            context.Tweets.report(tweet, owner);
            logger.info("@" + owner.getUsername() + " reported a tweet :"+tweet.getID());
        }
    }
}
