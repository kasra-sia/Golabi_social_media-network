package ir.sharif.AP.kasra_sia.demoModel;

public class DemoComment extends DemoModel{
    protected int parentID;
    protected String text;
    protected String username;
    protected String encodedProfilePhoto;
    protected long likes;
    protected boolean ownerIsLiked;
    protected boolean isMyTweet;
    protected String time;

    public DemoComment(int parentID, int ID, String text, String username, String encodedProfilePhoto, String time, long likes, boolean ownerIsLiked, boolean isMyTweet) {
        this.parentID = parentID;
        this.time = time;
        this.ID = ID;
        this.text = text;
        this.username = username;
        this.encodedProfilePhoto = encodedProfilePhoto;
        this.likes = likes;
        this.ownerIsLiked = ownerIsLiked;
        this.isMyTweet = isMyTweet;
    }
    public DemoComment(){}

    public String getText() {
        return text;
    }

    public String getUsername() {
        return username;
    }

    public String getEncodedProfilePhoto() {
        return encodedProfilePhoto;
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

    public int getParentID() {
        return parentID;
    }
}
