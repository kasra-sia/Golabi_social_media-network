package ir.sharif.AP.kasra_sia.db;


import ir.sharif.AP.kasra_sia.model.Tweet;
import ir.sharif.AP.kasra_sia.model.account.*;
import ir.sharif.AP.kasra_sia.model.chat.*;

public class Context {
    public DBSet<User> Users = new UserDB();
    public DBSet<Tweet> Tweets = new TweetDB();
    public DBSet<Message> Messages = new MessageDB();
    public DBSet<ChatRoom> ChatRooms = new ChatDB();
    public DBSet<GroupChat> GroupChats = new GroupChatDB();

}