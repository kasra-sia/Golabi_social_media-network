package ir.sharif.AP.kasra_sia.model.account;

import ir.sharif.AP.kasra_sia.model.FollowRequest;
import ir.sharif.AP.kasra_sia.model.LastSeen;
import ir.sharif.AP.kasra_sia.model.Model;
import ir.sharif.AP.kasra_sia.model.Notification;

import java.util.Date;
import java.util.LinkedList;

public class Profile extends Model {

    private String firstName;
    private String lastName;
    private String email;
    private String bio = "";
    private Date birthday;
    private String phoneNumber;
    private boolean isPrivate;
    private LastSeen lastSeen;
    private String lastSeenTime;
    private LinkedList<Notification> systemNotifications = new LinkedList<>();
    private LinkedList<FollowRequest> sentFollowRequest = new LinkedList<>();
    private LinkedList<FollowRequest> receivedFollowRequest = new LinkedList<>();
    private boolean isActive;
    private LinkedList<Integer> following = new LinkedList<>();
    private LinkedList<Integer> followers = new LinkedList<>();
    private LinkedList<Integer> blacklist = new LinkedList<>();
    private LinkedList<Integer> muteList = new LinkedList<>();

    public Profile(String firstName, String lastName, String email, String bio, Date birthday, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.bio = bio;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.lastSeen = LastSeen.EVERYONE;
    }

    public Profile() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public LastSeen getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(LastSeen lastSeen) {
        this.lastSeen = lastSeen;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LinkedList<Integer> getFollowing() {
        return following;
    }

    public void setFollowing(LinkedList<Integer> following) {
        this.following = following;
    }

    public LinkedList<Integer> getFollowers() {
        return followers;
    }

    public void setFollowers(LinkedList<Integer> followers) {
        this.followers = followers;
    }

    public LinkedList<Integer> getBlacklist() {
        return blacklist;
    }

    public void setBlacklist(LinkedList<Integer> blacklist) {
        this.blacklist = blacklist;
    }

    public LinkedList<Integer> getMuteList() {
        return muteList;
    }

    public void setMuteList(LinkedList<Integer> muteList) {
        this.muteList = muteList;
    }

    public String getLastSeenTime() {
        return lastSeenTime;
    }

    public LinkedList<Notification> getSystemNotifications() {
        return systemNotifications;
    }

    public void setSystemNotifications(LinkedList<Notification> systemNotifications) {
        this.systemNotifications = systemNotifications;
    }

    public void setLastSeenTime(String lastSeenTime) {
        this.lastSeenTime = lastSeenTime;
    }

    public LinkedList<FollowRequest> getSentRequest() {
        return sentFollowRequest;
    }

    public LinkedList<FollowRequest> getReceivedRequest() {
        return receivedFollowRequest;
    }


    public void setSentRequest(LinkedList<FollowRequest> sentFollowRequest) {
        this.sentFollowRequest = sentFollowRequest;
    }

    public void setReceivedRequest(LinkedList<FollowRequest> receivedFollowRequest) {
        this.receivedFollowRequest = receivedFollowRequest;
    }
}

