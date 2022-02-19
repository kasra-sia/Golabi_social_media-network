package ir.sharif.AP.kasra_sia.requests;

import ir.sharif.AP.kasra_sia.demoModel.DemoComment;
import ir.sharif.AP.kasra_sia.demoModel.DemoTweet;
import ir.sharif.AP.kasra_sia.model.LastSeen;
import ir.sharif.AP.kasra_sia.requests.eventObjects.*;
import ir.sharif.AP.kasra_sia.responses.Response;

public interface RequestVisitor {

    Response login(LoginForm loginForm);

    Response register(RegistrationForm registerForm);

    Response openPrivacyPage();

    Response openMyInfoPage();

    Response openMyTweetsPage();

    Response openTweetExplorerPage();

    Response openCommentsPage(int ParentID);

    Response openUserPage(String username);

    Response openTimelinePage();

    Response openReceivedRequestPage();

    Response openSentRequestPage();

    Response openSystemNotificationsPage();

    Response openUsersList(String command);

    Response mangeFollowRequest(FollowRequestEvent followRequestEvent);

    Response searchUser(String username);

    Response deleteAccount();

    Response logout();

    Response setLastSeenType(LastSeen lastSeen);

    Response changePassword(ChangePasswordForm changePasswordForm);

    Response setPrivacy(boolean isPrivate);

    Response updateMyInfo(MyInfoForm myInfoForm);

    Response newTweet(CreatTweetForm form);

    Response newComment(CreatCommentForm creatCommentForm,int parentID);

    Response likeTweet(int id,boolean liked);

    Response likeComment(int parentID,int id,boolean liked);

    Response deleteTweet(int id);

    Response userAction(String username,String command);

}
