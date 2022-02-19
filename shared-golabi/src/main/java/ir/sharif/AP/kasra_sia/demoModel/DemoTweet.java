package ir.sharif.AP.kasra_sia.demoModel;

public class DemoTweet extends DemoModel {
    protected String text;
    protected String username;
    protected String encodedProfilePhoto;
    protected String encodedTweetImage;
    protected long likes;
    protected boolean ownerIsLiked;
    protected boolean isMyTweet;
    protected String time;
    public DemoTweet(int ID, String text, String username, String encodedProfilePhoto, String encodedTweetImage, String time, long likes, boolean ownerIsLiked, boolean isMyTweet) {
      this.ID = ID;
      this.text = text;
      this.username = username;
      this.encodedProfilePhoto = encodedProfilePhoto;
      this.encodedTweetImage = encodedTweetImage;
      this.time = time;
      this.likes = likes;
      this.ownerIsLiked = ownerIsLiked;
      this.isMyTweet = isMyTweet;
    }

    public String getText() {
        return text;
    }

    public String getUsername() {
        return username;
    }

    public String getEncodedProfilePhoto() {
        return encodedProfilePhoto;
    }

    public String getEncodedTweetImage() {
        return encodedTweetImage;
    }

    public long getLikes() {
        return likes;
    }

    public boolean isOwnerIsLiked() {
        return ownerIsLiked;
    }

    public boolean isMyTweet() {
        return isMyTweet;
    }

    public String getTime() {
        return time;
    }
}
