package ir.sharif.AP.kasra_sia.clientHandler;

import ir.sharif.AP.kasra_sia.controller.network.ResponseSender;
import ir.sharif.AP.kasra_sia.demoModel.DemoComment;
import ir.sharif.AP.kasra_sia.demoModel.DemoTweet;
import ir.sharif.AP.kasra_sia.model.LastSeen;
import ir.sharif.AP.kasra_sia.model.RequestStatus;
import ir.sharif.AP.kasra_sia.model.Tweet;
import ir.sharif.AP.kasra_sia.model.account.User;
import ir.sharif.AP.kasra_sia.model.account.UserStatus;
import ir.sharif.AP.kasra_sia.requests.AddCommentRequest;
import ir.sharif.AP.kasra_sia.requests.OpenUserPageRequest;
import ir.sharif.AP.kasra_sia.requests.RequestVisitor;
import ir.sharif.AP.kasra_sia.requests.eventObjects.*;
import ir.sharif.AP.kasra_sia.responses.*;
import ir.sharif.AP.kasra_sia.util.DateToString;
import ir.sharif.AP.kasra_sia.util.ImageResizer;
import ir.sharif.AP.kasra_sia.util.ImageUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import static ir.sharif.AP.kasra_sia.model.LastSeen.*;

public class ClientHandler extends Thread implements RequestVisitor {
    private ResponseSender sender;
    private ControllerManager controllerManager;
    private volatile boolean running;

    public ClientHandler(ResponseSender sender) throws IOException {
        this.sender = sender;
        controllerManager = new ControllerManager();
    }

    @Override
    public synchronized void start() {
        running = true;
        super.start();
    }

    @Override
    public void run() {
        while (running) {
            try {
                sender.sendResponse(sender.getRequest().visit(this));
            } catch (NoSuchElementException e) {
                if (controllerManager.controller.getOwner() != null) {
                    System.out.println("@" + controllerManager.controller.getOwner().getUsername() + " disconnected");
                    controllerManager.authController.logout();
                } else
                    System.out.println("client disconnected");
                sender.close();
                running = false;
            }
        }
    }

    @Override
    public Response login(LoginForm loginForm) {
        try {
            controllerManager.authController.login(loginForm);
            controllerManager.initialize();
            return new SignInResponse(true, "");
        } catch (Exception e) {
            if (e.getMessage() != null)
                return new SignInResponse(false, e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response register(RegistrationForm registrationForm) {
        try {
            controllerManager.authController.register(registrationForm);
            controllerManager.initialize();
            return new SignInResponse(true, "");
        } catch (Exception e) {
            if (e.getMessage() != null)
                return new SignInResponse(false, e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response openPrivacyPage() {
        User owner = controllerManager.controller.getOwner();
        return new OpenPrivacyPageResponse(owner.getProfile().isPrivate(),
                owner.getProfile().getLastSeen());
    }

    @Override
    public Response openMyInfoPage() {
        User owner = controllerManager.controller.getOwner();
        String encodedImage = null;
        try {
            encodedImage = ImageUtil.convert(ImageIO.read(controllerManager.controller.getContext().Users.getPhoto(owner.getID())), "png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new OpenMyInfoPageResponse(
                new MyInfoForm(this,
                        owner.getProfile().getFirstName(),
                        owner.getProfile().getLastName(),
                        owner.getUsername(),
                        owner.getProfile().getEmail(),
                        owner.getProfile().getPhoneNumber(),
                        owner.getProfile().getBirthday(),
                        owner.getProfile().getBio(),
                        encodedImage
                ));
    }

    @Override
    public Response openMyTweetsPage() {
        LinkedList<DemoTweet> temp = new LinkedList<>();
        if (controllerManager.tweetController.getContext().Tweets.all() != null)
            for (Tweet tweet : controllerManager.tweetController.getContext().Tweets.all()) {
                if (tweet.getUserID() == controllerManager.tweetController.getOwner().getID()) {
                    boolean flag1 = false;
                    for (Integer like : tweet.getLikes()) {
                        if (like == controllerManager.tweetController.getOwner().getID()) {
                            flag1 = true;
                            break;
                        }
                    }
                    String encodedProfilePhoto = null;
                    String encodedTweetImage = null;
                    try {
                        encodedProfilePhoto = ImageUtil.convert(ImageIO.read(controllerManager.controller.getContext().Users.getPhoto(tweet.getUserID())), "png");
                        encodedTweetImage = ImageUtil.convert(ImageIO.read(controllerManager.controller.getContext().Tweets.getPhoto(tweet.getID())), "png");
                    } catch (IllegalArgumentException | IOException ignored) {

                    }
                    DemoTweet demoTweet = new DemoTweet(
                            tweet.getID(),
                            tweet.getText(),
                            controllerManager.tweetController.getContext().Users.get(tweet.getUserID()).getUsername(),
                            encodedProfilePhoto,
                            encodedTweetImage,
                            tweet.getTime(),
                            tweet.getLikes().size(),
                            flag1,
                            controllerManager.controller.getOwner().getID() == tweet.getUserID()
                    );
                    temp.add(demoTweet);
                }
            }
        return new OpenMyTweetsPageResponse(temp);
    }

    @Override
    public Response openTweetExplorerPage() {
        LinkedList<DemoTweet> tweets = new LinkedList<>();
        if (controllerManager.controller.getContext().Tweets.all() != null)
            for (Tweet tweet : controllerManager.controller.getContext().Tweets.all()) {
                boolean flag1 = false;
                for (Integer like : tweet.getLikes()) {
                    if (like == controllerManager.controller.getOwner().getID()) {
                        flag1 = true;
                        break;
                    }
                }
                String encodedProfilePhoto = null;
                String encodedTweetImage = null;
                try {
                    encodedProfilePhoto = ImageUtil.convert(ImageIO.read(controllerManager.controller.getContext().Users.getPhoto(tweet.getUserID())), "png");
                    encodedTweetImage = ImageUtil.convert(ImageIO.read(controllerManager.controller.getContext().Tweets.getPhoto(tweet.getID())), "png");
                } catch (IllegalArgumentException | IOException ignored) {

                }
                if (!controllerManager.controller.getOwner().getProfile().getBlacklist().contains(tweet.getUserID())) {
                    DemoTweet demoTweet = new DemoTweet(
                            tweet.getID(),
                            tweet.getText(),
                            controllerManager.tweetController.getContext().Users.get(tweet.getUserID()).getUsername(),
                            encodedProfilePhoto,
                            encodedTweetImage,
                            tweet.getTime(),
                            tweet.getLikes().size(),
                            flag1,
                            controllerManager.controller.getOwner().getID() == tweet.getUserID()
                    );
                    tweets.add(demoTweet);
                }
            }
        return new OpenTweetExplorerPageResponse(tweets);
    }

    @Override
    public Response openCommentsPage(int i) {
        Tweet tweet = controllerManager.controller.getContext().Tweets.get(i);
        if (tweet.getComments() != null) {

            LinkedList<DemoComment> demoComments = new LinkedList<>();
            tweet.getComments().forEach(comment -> {
                String encodedProfilePhoto = null;
                try {
                    encodedProfilePhoto = ImageUtil.convert(ImageIO.read(controllerManager.controller.getContext().Users.getPhoto(comment.getUserID())), "png");
                } catch (IllegalArgumentException | IOException ignored) {

                }
                boolean flag1 = false;
                for (Integer like : comment.getLikes()) {
                    if (like == controllerManager.controller.getOwner().getID()) {
                        flag1 = true;
                        break;
                    }
                }
                DemoComment demoComment = new DemoComment(
                        tweet.getID(),
                        comment.getID(),
                        comment.getText(),
                        controllerManager.controller.getContext().Users.get(comment.getUserID()).getUsername(),
                        encodedProfilePhoto,
                        comment.getTime(),
                        comment.getLikes().size(),
                        flag1,
                        controllerManager.controller.getOwner().getID() == comment.getUserID()
                );
                demoComments.add(demoComment);
            });
            return new OpenCommentsPageResponse(demoComments, tweet.getID());
        }
        return null;
    }

    @Override
    public Response openUserPage(String username) {
        boolean isFollowed = false;
        boolean isBlocked = false;
        boolean isMuted = false;
        User foundUser = controllerManager.userController.getUser(username);
        if (controllerManager.controller.getOwner().getProfile().getFollowing().contains(foundUser.getID())) {
            isFollowed = true;
        }
        if (controllerManager.controller.getOwner().getProfile().getBlacklist().contains(foundUser.getID())) {
            isBlocked = true;
        }
        if (controllerManager.controller.getOwner().getProfile().getMuteList().contains(foundUser.getID())) {
            isMuted = true;
        }
        String lastSeen = "";
        switch (foundUser.getProfile().getLastSeen()) {
            case NOBODY -> lastSeen = "recently";
            case EVERYONE -> lastSeen = foundUser.getProfile().getLastSeenTime();
            case FOLLOWINGS -> {
                if (foundUser.getProfile().getFollowing().contains(controllerManager.controller.getOwner().getID())) {
                    lastSeen = foundUser.getProfile().getLastSeenTime();
                } else lastSeen = "recently";
            }
        }
        String encodedProfilePhoto = null;
        try {
            encodedProfilePhoto = ImageUtil.convert(ImageIO.read(controllerManager.controller.getContext().Users.getPhoto(foundUser.getID())), "png");
        } catch (IllegalArgumentException | IOException ignored) {

        }
        if (foundUser.getUserStatus() == UserStatus.ONLINE)
            lastSeen = "online";
        return new OpenUserPageResponse(new SetUserViewForm(
                foundUser.getUsername(),
                encodedProfilePhoto,
                foundUser.getProfile().getFirstName(),
                foundUser.getProfile().getLastName(),
                lastSeen,
                DateToString.dateToString(foundUser.getProfile().getBirthday()),
                foundUser.getProfile().getBio(),
                isFollowed,
                isBlocked,
                isMuted,
                foundUser.getProfile().isPrivate()
        ));

    }

    @Override
    public Response openTimelinePage() {
        LinkedList<DemoTweet> tweets = new LinkedList<>();
        if (controllerManager.controller.getContext().Tweets.all() != null)
            for (Tweet tweet : controllerManager.controller.getContext().Tweets.all()) {
                controllerManager.controller.getOwner().getProfile().getFollowing().forEach(following -> {
                    boolean flag1 = false;
                    for (Integer like : tweet.getLikes()) {
                        if (like == controllerManager.controller.getOwner().getID()) {
                            flag1 = true;
                            break;
                        }
                    }
                    String encodedProfilePhoto = null;
                    String encodedTweetImage = null;
                    try {
                        encodedProfilePhoto = ImageUtil.convert(ImageIO.read(controllerManager.controller.getContext().Users.getPhoto(tweet.getUserID())), "png");
                        encodedTweetImage = ImageUtil.convert(ImageIO.read(controllerManager.controller.getContext().Tweets.getPhoto(tweet.getID())), "png");
                    } catch (IllegalArgumentException | IOException ignored) {

                    }
                    if (tweet.getUserID() == following) {
                        DemoTweet demoTweet = new DemoTweet(
                                tweet.getID(),
                                tweet.getText(),
                                controllerManager.tweetController.getContext().Users.get(tweet.getUserID()).getUsername(),
                                encodedProfilePhoto,
                                encodedTweetImage,
                                tweet.getTime(),
                                tweet.getLikes().size(),
                                flag1,
                                controllerManager.controller.getOwner().getID() == tweet.getUserID()
                        );
                        tweets.add(demoTweet);
                    }
                });
            }
        return new OpenTimeLinePageResponse(tweets);
    }

    @Override
    public Response openReceivedRequestPage() {
        LinkedList<String>temp = new LinkedList<>();
        controllerManager.controller.getOwner().getProfile().getReceivedRequest().forEach(
                request -> temp.add(controllerManager.controller.getContext().Users.get(request.getSenderID()).getUsername())
        );
        return new OpenReceivedRequestPageResponse(temp);
    }

    @Override
    public Response openSentRequestPage() {
        LinkedHashMap<String, RequestStatus> map = new LinkedHashMap<>();
        controllerManager.controller.getOwner().getProfile().getSentRequest().forEach(request -> {
            map.put(controllerManager.controller.getContext().Users.get(request.getReceiverID()).getUsername(),request.getRequestStatus());
        });
        return new OpenSentRequestPageResponse(map);
    }

    @Override
    public Response openSystemNotificationsPage() {
        return new OpenSystemNotificationsPageResponse(controllerManager.controller.getOwner().getProfile().getSystemNotifications());
    }

    @Override
    public Response openUsersList(String s) {
        if (s.equals("followings")){

            LinkedHashMap<String, String> temp = new LinkedHashMap<>();
            controllerManager.controller.getOwner().getProfile().getFollowing().forEach(following ->{
                String encodedProfilePhoto = null;
                try {
                    encodedProfilePhoto = ImageUtil.convert(ImageIO.read(controllerManager.controller.getContext().Users.getPhoto(following)), "png");
                } catch (IllegalArgumentException | IOException ignored) {

                }
                temp.put(controllerManager.controller.getContext().Users.get(following).getUsername(),encodedProfilePhoto);
            });
            return new OpenUsersListPageResponse(temp);
        }
        if (s.equals("followers")){
            LinkedHashMap<String, String> temp = new LinkedHashMap<>();
            controllerManager.controller.getOwner().getProfile().getFollowers().forEach(following ->{
                String encodedProfilePhoto = null;
                try {
                    encodedProfilePhoto = ImageUtil.convert(ImageIO.read(controllerManager.controller.getContext().Users.getPhoto(following)), "png");
                } catch (IllegalArgumentException | IOException ignored) {

                }
                temp.put(controllerManager.controller.getContext().Users.get(following).getUsername(),encodedProfilePhoto);
            });
            return new OpenUsersListPageResponse(temp);
        }
        if (s.equals("blackList")){
            LinkedHashMap<String, String> temp = new LinkedHashMap<>();
            controllerManager.controller.getOwner().getProfile().getBlacklist().forEach(following ->{
                String encodedProfilePhoto = null;
                try {
                    encodedProfilePhoto = ImageUtil.convert(ImageIO.read(controllerManager.controller.getContext().Users.getPhoto(following)), "png");
                } catch (IllegalArgumentException | IOException ignored) {

                }
                temp.put(controllerManager.controller.getContext().Users.get(following).getUsername(),encodedProfilePhoto);
            });
            return new OpenUsersListPageResponse(temp);
        }
        return null;
    }

    @Override
    public Response mangeFollowRequest(FollowRequestEvent followRequestEvent) {
        controllerManager.userController.manageRequest(followRequestEvent);
        return openReceivedRequestPage();
    }

    @Override
    public Response searchUser(String s) {
        try {
            User user = controllerManager.explorerPageController.searchUser(s);
            String encodedProfilePhoto = null;
            try {
                encodedProfilePhoto = ImageUtil.convert(ImageIO.read(controllerManager.controller.getContext().Users.getPhoto(user.getID())), "png");
            } catch (IllegalArgumentException | IOException ignored) {
            }
            return new SearchUserResponse(user.getUsername(), encodedProfilePhoto);
        } catch (Exception e) {
            return new SearchUserResponse(null, null);
//
        }

    }

    @Override
    public Response deleteAccount() {
        controllerManager.userController.deleteAccount();
        return new LogoutResponse(true);

    }

    @Override
    public Response logout() {
        controllerManager.authController.logout();
        sender.expireAuthToken();
        return new LogoutResponse(true);
    }

    @Override
    public Response setLastSeenType(LastSeen lastSeen) {
        System.out.println(lastSeen);
        controllerManager.settingsPageController.setLastSeenType(lastSeen);
        return null;
    }

    @Override
    public Response changePassword(ChangePasswordForm changePasswordForm) {
        try {
            controllerManager.settingsPageController.changePassword(changePasswordForm);
            return new ChangePasswordResponse("password Changed successfully");
        } catch (Exception e) {
            if (e.getMessage() != null)
                return new ChangePasswordResponse(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response setPrivacy(boolean b) {
        controllerManager.settingsPageController.setAccountPrivacy(b);
        return null;
    }

    @Override
    public Response updateMyInfo(MyInfoForm myInfoForm) {
        try {
            controllerManager.personalPageController.saveInfo(myInfoForm);
            return new UpdateMyInfoResponse(true, "");
        } catch (Exception e) {
            if (e.getMessage() != null)
                return new UpdateMyInfoResponse(false, e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response newTweet(CreatTweetForm form) {
        controllerManager.tweetController.creatTweet(form);
        return openMyTweetsPage();
    }

    @Override
    public Response newComment(CreatCommentForm creatCommentForm, int i) {
        controllerManager.tweetController.addComment(creatCommentForm, i);
        return openCommentsPage(i);
    }

    @Override
    public Response likeTweet(int i, boolean b) {
        controllerManager.tweetController.likeTweet(i, b);
        return null;
    }

    @Override
    public Response likeComment(int i, int i1, boolean b) {
        controllerManager.tweetController.likeComment(i, i1, b);
        return openCommentsPage(i);
    }

    @Override
    public Response deleteTweet(int i) {
        controllerManager.tweetController.deleteTweet(i);
        return null;
    }

    @Override
    public Response userAction(String s, String s1) {
        User foundUser = controllerManager.userController.getUser(s);
        if (s1.equals("follow")) {
            boolean temp = controllerManager.userController.follow(foundUser);
            if (!temp)
                return new ShowMessageResponse("follow request sent");
        }
        if (s1.equals("unfollow"))
            controllerManager.userController.unfollow(foundUser);
        if (s1.equals("block"))
            controllerManager.userController.block(foundUser);
        if (s1.equals("unblock"))
            controllerManager.userController.unblock(foundUser);
        return null;
    }
}

