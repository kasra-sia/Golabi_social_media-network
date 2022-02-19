package ir.sharif.AP.kasra_sia.responses;

import ir.sharif.AP.kasra_sia.demoModel.DemoComment;
import ir.sharif.AP.kasra_sia.demoModel.DemoTweet;
import ir.sharif.AP.kasra_sia.model.LastSeen;
import ir.sharif.AP.kasra_sia.model.Notification;
import ir.sharif.AP.kasra_sia.model.RequestStatus;
import ir.sharif.AP.kasra_sia.requests.eventObjects.MyInfoForm;
import ir.sharif.AP.kasra_sia.requests.eventObjects.SetUserViewForm;

import javax.swing.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public interface ResponseVisitor {

    void visitSignInResult(boolean success,String message);

    void openPrivacyPage(boolean isPrivate, LastSeen lastSeen);

    void openMyInfoPage(MyInfoForm myInfoForm);

    void openMyTweetsPage(LinkedList<DemoTweet> myTweets);

    void openTweetExplorePage(LinkedList<DemoTweet> tweets);

    void openCommentsPage(LinkedList<DemoComment> comments,int parentID);

    void openUserPage(SetUserViewForm form);

    void openTimeLinePage(LinkedList<DemoTweet> tweets);

    void openReceivedRequestPage(LinkedList<String> requests);

    void openSentRequestPage(LinkedHashMap<String , RequestStatus> sentRequests);

    void openUsersListPage(LinkedHashMap<String, String> users);

    void openSystemNotificationPage(LinkedList<Notification> notifications);

    void foundUser(String username,String encodedImage);

    void visitStringResponse(String message);

    void visitLogoutResult(boolean result);

    void visitChangePasswordResponse(String message);

    void visitMyInfoEditResult(boolean success,String message);

    void showMessage(String message);
}
