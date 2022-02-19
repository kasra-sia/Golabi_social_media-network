package ir.sharif.AP.kasra_sia.controller;


import ir.sharif.AP.kasra_sia.model.FollowRequest;
import ir.sharif.AP.kasra_sia.model.Notification;
import ir.sharif.AP.kasra_sia.model.RequestStatus;
import ir.sharif.AP.kasra_sia.requests.eventObjects.FollowRequestEvent;
import ir.sharif.AP.kasra_sia.util.*;
import ir.sharif.AP.kasra_sia.model.account.*;

import java.util.Arrays;

public class UserController extends Controller {
    public UserController(User owner) {
        super(owner);
    }

    public boolean follow(User user) {
        boolean temp;
        if (user!=null) {
            if (!user.getProfile().isPrivate()) {
                owner.getProfile().getFollowing().add(user.getID());
                user.getProfile().getFollowers().add(owner.getID());
                user.getProfile().getSystemNotifications().add(new Notification("@" + owner.getUsername() + " followed you"));
                temp = true;

            } else {
                temp = false;
                sendFollowRequest(user);
            }
            context.Users.update(user);
            context.Users.update(owner);
            return temp;
        }
        return false;
    }

    public void unfollow(User user) {
        owner.getProfile().getFollowing().remove(Integer.valueOf(user.getID()));
        user.getProfile().getFollowers().remove(Integer.valueOf(owner.getID()));
        user.getProfile().getSystemNotifications().add(new Notification("@" + owner.getUsername() + " unfollowed you"));
    }

    public void block(User user) {
        if (owner.getProfile().getFollowers().contains(user.getID())) {
            owner.getProfile().getFollowers().remove(Integer.valueOf(user.getID()));
            user.getProfile().getFollowing().remove(Integer.valueOf(owner.getID()));
        }
        if (owner.getProfile().getFollowing().contains(user.getID())) {
            owner.getProfile().getFollowing().remove(Integer.valueOf(user.getID()));
            user.getProfile().getFollowers().remove(Integer.valueOf(owner.getID()));
        }
        owner.getProfile().getBlacklist().add(user.getID());
        owner.getProfile().getReceivedRequest().forEach(
                request -> {
                    if (request.getSenderID() == user.getID()) {
                        owner.getProfile().getReceivedRequest().remove(request);
                        user.getProfile().getSentRequest().remove(request);
                    }
                });
        context.ChatRooms.all().forEach(
                chatRoom -> {
                    if (chatRoom.getOther(owner.getID()) == user.getID())
                        context.ChatRooms.remove(chatRoom);
                }
        );
        logger.info("@" + owner.getUsername() + " blocked @" + user.getUsername());
        context.Users.update(user);
        context.Users.update(owner);
    }

    public void unblock(User user) {
        owner.getProfile().getBlacklist().remove(Integer.valueOf(user.getID()));
        logger.info("@" + owner.getUsername() + " blocked @" + user.getUsername());
        context.Users.update(owner);
        context.Users.update(user);
    }

    private void sendFollowRequest(User user) {
        boolean found = user.getProfile().getReceivedRequest()
                .stream().anyMatch(request -> request.getSenderID() == owner.getID());
        if (!found) {
            FollowRequest request = new FollowRequest(owner.getID(), user.getID());
            user.getProfile().getReceivedRequest().add(request);
            logger.info("@" + owner.getUsername() + " sent follow request to @" + user.getUsername());
            owner.getProfile().getSentRequest().add(request);
        }
    }
    public void manageRequest(FollowRequestEvent event) {
        if (event.isAccepted()) {
            context.Users.all().forEach(
                    user -> {
                        if (user.getUsername().equals(event.getUsername())) {
                            owner.getProfile().getFollowers().add(user.getID());
                            user.getProfile().getFollowing().add(owner.getID());
                            for (int i = 0; i < owner.getProfile().getReceivedRequest().size(); i++) {
                                FollowRequest request = owner.getProfile().getReceivedRequest().get(i);
                                if (request.getSenderID() == user.getID()) {
                                    owner.getProfile().getReceivedRequest().remove(request);
                                    request.setRequestStatus(RequestStatus.ACCEPTED);
                                    logger.info("@"+owner.getUsername()+" accepted @"+user.getUsername());

                                }
                            }
                            user.getProfile().getSystemNotifications().add(new Notification("@"+owner.getUsername() + " accepted your follow request"));
                            context.Users.update(owner);
                            context.Users.update(user);
                        }
                    });
        } else if (!event.isAccepted()) {
            context.Users.all().forEach(
                    user -> {
                        if (user.getUsername().equals(event.getUsername())) {
                            for (int i = 0; i < owner.getProfile().getReceivedRequest().size(); i++) {
                                FollowRequest request = owner.getProfile().getReceivedRequest().get(i);
                                if (request.getSenderID() == user.getID()) {
                                    owner.getProfile().getReceivedRequest().remove(request);
                                    logger.info("@"+owner.getUsername()+" declined @"+user.getUsername());
                                    request.setRequestStatus(RequestStatus.DECLINED);
                                }
                            }
                            user.getProfile().getSystemNotifications().add(new Notification("@"+owner.getUsername() + " declined your follow request"));
                            context.Users.update(owner);
                            context.Users.update(user);
                        }

                    });
        }
    }

    public void deleteAccount() {
        context.Messages.all().forEach(
                message -> {
                    if (message.getSenderID() == owner.getID())
                        context.Messages.remove(message);
                }
        );
        context.ChatRooms.all().forEach(
                chatRoom -> {
                    if (Arrays.stream(chatRoom.getUsersID()).anyMatch(n -> n == owner.getID()))
                        context.ChatRooms.remove(chatRoom);
                }
        );
        owner.getProfile().getFollowing().forEach(following -> {
            block(context.Users.get(following));
        });

        owner.getProfile().getFollowers().forEach(followers -> {
            block(context.Users.get(followers));
        });
        context.Tweets.all().forEach(tweet -> {
            if (tweet.getUserID() == owner.getID())
                context.Tweets.remove(tweet);
        });

        context.GroupChats.all().forEach(groupChat -> {
            if (groupChat.getUsersID().contains(owner.getID())) {
                groupChat.getUsersID().remove(Integer.valueOf(owner.getID()));
                context.GroupChats.update(groupChat);
            }
        });
        logger.info("@" + owner.getUsername() + " deleted account");
        context.Users.remove(owner);
    }

    public void setLastSeen() {
        if (owner != null) {
            owner.getProfile().setLastSeenTime(CurrentTimeToString.get());
            Controller.logger.info("@" + owner.getUsername() + " logged out");
            context.Users.update(owner);
        }
    }

    public User getUser(String username) {
        final User[] user1 = {null};
        context.Users.all().forEach(user -> {
            if (user.getUsername().equals(username))
                user1[0] = user;
                });
        return user1[0];
    }
}
