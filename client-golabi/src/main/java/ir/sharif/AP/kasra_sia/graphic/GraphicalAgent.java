package ir.sharif.AP.kasra_sia.graphic;

import ir.sharif.AP.kasra_sia.demoModel.DemoComment;
import ir.sharif.AP.kasra_sia.demoModel.DemoTweet;
import ir.sharif.AP.kasra_sia.graphic.view.modelsView.UserView;
import ir.sharif.AP.kasra_sia.graphic.view.pagesView.TimeLinePageView.TimeLinePageView;
import ir.sharif.AP.kasra_sia.graphic.view.pagesView.UsersListPageView;
import ir.sharif.AP.kasra_sia.graphic.view.pagesView.explorerPage.ExplorerGlobalView;
import ir.sharif.AP.kasra_sia.graphic.view.pagesView.explorerPage.ExplorerPageView;
import ir.sharif.AP.kasra_sia.graphic.view.pagesView.explorerPage.ExplorerSearchView;
import ir.sharif.AP.kasra_sia.graphic.view.pagesView.personalPage.*;
import ir.sharif.AP.kasra_sia.graphic.view.pagesView.personalPage.myTweetsPageView.commentsPageView.CommentsPageView;
import ir.sharif.AP.kasra_sia.graphic.view.pagesView.personalPage.myTweetsPageView.MyTweetsPageView;
import ir.sharif.AP.kasra_sia.model.LastSeen;
import ir.sharif.AP.kasra_sia.model.Notification;
import ir.sharif.AP.kasra_sia.model.RequestStatus;
import ir.sharif.AP.kasra_sia.requests.*;
import ir.sharif.AP.kasra_sia.requests.eventObjects.MyInfoForm;
import ir.sharif.AP.kasra_sia.requests.eventObjects.SetUserViewForm;
import ir.sharif.AP.kasra_sia.util.Loop;
import ir.sharif.AP.kasra_sia.graphic.view.MainPanel;
import ir.sharif.AP.kasra_sia.graphic.view.pagesView.openingPage.LoginPageView;
import ir.sharif.AP.kasra_sia.graphic.view.pagesView.mainPage.MainPageView;
import ir.sharif.AP.kasra_sia.graphic.view.pagesView.Page;
import ir.sharif.AP.kasra_sia.listeners.RequestListener;

import ir.sharif.AP.kasra_sia.graphic.view.pagesView.openingPage.RegistrationPageView;
import ir.sharif.AP.kasra_sia.graphic.view.MainFrame;
import ir.sharif.AP.kasra_sia.graphic.view.pagesView.settingsPage.PrivacySettingsPageView;
import ir.sharif.AP.kasra_sia.graphic.view.pagesView.settingsPage.SettingsPageView;

import java.io.IOException;
import java.util.*;

public class GraphicalAgent {
    private final RequestListener requestListener;
    private MainFrame frame;
    private MainPanel mainPanel;
    private final Stack<Page> pages;
    public Page currentPage;
    private Loop loop;

    public GraphicalAgent(RequestListener requestListener) {
        this.requestListener = requestListener;
        this.mainPanel = new MainPanel();
        this.pages = new Stack<>();
        this.frame = new MainFrame(mainPanel);
    }

    public void initialize() {
        frame.setVisible(true);
        mainPanel.initialize(this::toolbarListener);
    }

    public void goToOpeningPage() {
        pages.clear();
        currentPage = null;
        mainPanel = new MainPanel();
        frame.dispose();
        frame = new MainFrame(mainPanel);
        initialize();

    }

    public void goToMainPage() {
        if (loop != null)
            loop.stop();
        MainPageView mainPage = new MainPageView();
        mainPage.setStringListener(string -> {
            switch (string) {
                case "settingsPage" -> goToSettingsPage();
                case "personalPage" -> goToPersonalPage();
                case "messagingPage" -> goToMessagingPage();
                case "explorerPage" -> goToExplorerPage();
                case "timeLinePage" -> updateTimelinePage();
            }
        });
        setCurrentPage(mainPage);
        mainPanel.setToolsBar();

    }

    public void goToLoginPage() {
        LoginPageView loginPageView = new LoginPageView(requestListener);
        mainPanel.setCenterPanel(loginPageView);
        currentPage = loginPageView;
        if (loop != null)
            loop.stop();
    }

    public void goToRegistrationPage() {
        RegistrationPageView registrationPageView = new RegistrationPageView(requestListener);
        mainPanel.setCenterPanel(registrationPageView);
        currentPage = registrationPageView;
        if (loop != null)
            loop.stop();
    }

    public void goToSettingsPage() {
        SettingsPageView settingsPageView = new SettingsPageView(string -> {
            switch (string) {
                case "privacySettingsPage" -> requestListener.listen(new OpenPrivacyPageRequest());
                case "deleteAccount" -> requestListener.listen(new DeleteAccountRequest());
                case "logout" -> requestListener.listen(new LogoutRequest());
            }
        });
        setCurrentPage(settingsPageView);
        if (loop != null)
            loop.stop();

    }

    public void goToPrivacyPage(boolean isPrivate, LastSeen lastSeen) {
        PrivacySettingsPageView privacySettingsPageView = new PrivacySettingsPageView(requestListener);
        privacySettingsPageView.setPage(isPrivate, lastSeen);
        setCurrentPage(privacySettingsPageView);
        if (loop != null)
            loop.stop();
    }

    public void goToPersonalPage() {
        PersonalPageView personalPageView = new PersonalPageView();
        setCurrentPage(personalPageView);
        personalPageView.setStringListener(
                string -> {
                    switch (string) {
                        case "showInfo" -> requestListener.listen(new OpenMyInfoPageRequest());
                        case "notifications" -> goToNotificationsPage();
                        case "myTweets" -> requestListener.listen(new OpenMyTweetsPageRequest());
                        case "followings" -> updateUserList("followings");
                        case "followers" -> updateUserList("followers");
                        case "blackList" -> updateUserList("blackList");
                    }
                });
        if (loop != null)
            loop.stop();
    }

    public void goToMessagingPage() {

    }
    public void goToUsersListPage(LinkedHashMap<String, String> users){
        if (!(currentPage instanceof UsersListPageView)) {
            UsersListPageView usersListPageView = new UsersListPageView(requestListener);
            usersListPageView.setPage(users);
            setCurrentPage(usersListPageView);
            if (loop != null)
                loop.stop();
        }else {
            UsersListPageView usersListPageView = ((UsersListPageView) currentPage);
            usersListPageView.setPage(users);
        }
        }
    public void goToTimeLinePage(LinkedList<DemoTweet> tweets) {
        if (!(currentPage instanceof TimeLinePageView)) {
            TimeLinePageView timeLinePageView = new TimeLinePageView(requestListener);
            setCurrentPage(timeLinePageView);
            if (loop != null)
                loop.stop();
            loop = new Loop(1, this::updateTimelinePage);
            loop.start();
        } else {
            TimeLinePageView timeLinePageView = ((TimeLinePageView) currentPage);
            timeLinePageView.setPage(tweets);
        }
    }

    public void goToExplorerPage() {
        ExplorerPageView explorerPageView = new ExplorerPageView();
        explorerPageView.setStringListener(string -> {
            switch (string) {
                case "find" -> goToExplorerSearchView();
                case "tweetExplorer" -> requestListener.listen(new OpenTweetExplorerPageRequest());
            }
        });
        setCurrentPage(explorerPageView);
        if (loop != null)
            loop.stop();
    }

    public void goToExplorerGlobalPage(LinkedList<DemoTweet> tweets) {
        if (!(currentPage instanceof ExplorerGlobalView)) {
            ExplorerGlobalView explorerGlobalView = new ExplorerGlobalView(requestListener);
            setCurrentPage(explorerGlobalView);
            if (loop != null)
                loop.stop();
            loop = new Loop(1, this::updateTweetsExplorerPage);
            loop.start();
        } else {
            ExplorerGlobalView explorerGlobalView = ((ExplorerGlobalView) currentPage);
            explorerGlobalView.setPage(tweets);
        }
    }

    public void goToExplorerSearchView(){
        ExplorerSearchView explorerSearchView = new ExplorerSearchView(requestListener);
        setCurrentPage(explorerSearchView);
        if (loop != null)
            loop.stop();
    }

    public void goToUserViewPage(SetUserViewForm setUserViewForm) {
        if (!(currentPage instanceof UserView)) {
            UserView userView = new UserView(requestListener);
            setCurrentPage(userView);
            if (loop != null)
                loop.stop();
            loop = new Loop(1,()->updateUserView(setUserViewForm.getUsername()));
            loop.start();

        }else {
         UserView userView = ((UserView) currentPage);
         userView.setPage(setUserViewForm);
        }
    }

    public void goToMyInfoPage(MyInfoForm myInfoForm) throws IOException {
        ShowInfoPageView showInfoPageView = new ShowInfoPageView(requestListener);
        showInfoPageView.setPage(myInfoForm);
        setCurrentPage(showInfoPageView);
        if (loop != null)
            loop.stop();
    }

    public void goToNotificationsPage() {
        NotificationPageView notificationPageView = new NotificationPageView(requestListener);
        notificationPageView.addStringListener(string -> {
            switch (string){
                case "receivedRequestsPage"->updateReceived();
                case "systemNotificationsPage"->updateSystemNotifications();
                case "sentRequestsPage"->updateSent();
            }
        });
        setCurrentPage(notificationPageView);
        if (loop!=null){
            loop.stop();
        }
    }
    public void goToReceivedRequestPage(LinkedList<String> s){
        if (!(currentPage instanceof ReceivedRequestsPageView)){
            ReceivedRequestsPageView receivedRequestsPageView = new ReceivedRequestsPageView(requestListener);
            receivedRequestsPageView.setPage(s);
            setCurrentPage(receivedRequestsPageView);
            updateReceived();
            if (loop!=null)
                loop.stop();
            loop = new Loop(1,this::updateReceived);
        }else {
            ReceivedRequestsPageView receivedRequestsPageView = ((ReceivedRequestsPageView) currentPage);
            receivedRequestsPageView.setPage(s);
        }
    }

    public void goToSentRequestPage(LinkedHashMap<String, RequestStatus> linkedHashMap){
        if (!(currentPage instanceof SentRequestsPageView)){
            SentRequestsPageView sentRequestsPageView = new SentRequestsPageView();
            sentRequestsPageView.setPage(linkedHashMap);
            setCurrentPage(sentRequestsPageView);
            updateSent();
            if (loop!=null)
                loop.stop();
            loop = new Loop(1,this::updateSent);
        }else {
            SentRequestsPageView sentRequestsPageView = ((SentRequestsPageView) currentPage);
            sentRequestsPageView.setPage(linkedHashMap);
        }
    }

    public void goToSystemNotificationsPage(LinkedList<Notification> notifications){
        if (!(currentPage instanceof SystemNotificationsPage)){
            SystemNotificationsPage systemNotificationsPage = new SystemNotificationsPage();
            systemNotificationsPage.setPage(notifications);
            setCurrentPage(systemNotificationsPage);
            updateSystemNotifications();
            if (loop!=null)
                loop.stop();
            loop = new Loop(1,this::updateSystemNotifications);
        }else {
            SystemNotificationsPage systemNotificationsPage = ((SystemNotificationsPage) currentPage);
            systemNotificationsPage.setPage(notifications);
        }
    }

    public void goToMyTweetsPage(LinkedList<DemoTweet> myTweets) {
        if (!(currentPage instanceof MyTweetsPageView)) {
            MyTweetsPageView myTweetsPageView = new MyTweetsPageView(requestListener);
            setCurrentPage(myTweetsPageView);
            updateMyTweetsPage();
            if (loop != null)
                loop.stop();
            loop = new Loop(1, this::updateMyTweetsPage);
            loop.start();
        } else {
            MyTweetsPageView myTweetsPageView = ((MyTweetsPageView) currentPage);
            myTweetsPageView.setPage(myTweets);
        }
    }

    public void goToCommentPage(LinkedList<DemoComment> comments, int parentID) {
        if (!(currentPage instanceof CommentsPageView)) {
            CommentsPageView commentsPageView = new CommentsPageView(parentID, requestListener);
            setCurrentPage(commentsPageView);
            commentsPageView.setPage(comments);
            if (loop != null)
                loop.stop();
        } else {
            CommentsPageView commentsPageView = ((CommentsPageView) currentPage);
            commentsPageView.setPage(comments);
        }
    }

    public void goToMyFollowingPage() {

    }

    public void goToMyFollowersPage() {

    }

    public void goToBlackListPage() {

    }

    private void setCurrentPage(Page page) {
        for (int i = 0; i < pages.size(); i++) {
            if (pages.get(i).getClass().equals(page.getClass()))
                pages.remove(i);
        }
        pages.push(page);
        mainPanel.setCenterPanel(page);
        currentPage = page;
    }

    private void updateMyTweetsPage() {
        requestListener.listen(new OpenMyTweetsPageRequest());
    }

    private void updateTweetsExplorerPage() {
        requestListener.listen(new OpenTweetExplorerPageRequest());
    }

    private void updateTimelinePage(){requestListener.listen(new OpenTimeLinePageRequest());}

    private void updateUserView(String username) {
        requestListener.listen(new OpenUserPageRequest(username));
    }
    public void updateReceived(){
        requestListener.listen(new OpenReceivedRequestPageRequest());
    }
    public void updateSent(){
        requestListener.listen(new OpenSentRequestPageRequest());
    }
    public void updateSystemNotifications(){
        requestListener.listen(new OpenSystemNotificationPageRequest());
    }
    public void updateUserList(String s){
        requestListener.listen(new OpenUsersListPageRequest(s));
    }

    public void toolbarListener(String command) {
        if (command.equals("registration"))
            goToRegistrationPage();
        if (command.equals("login"))
            goToLoginPage();
        if (command.equals("back")) {
            if (!(currentPage instanceof MainPageView)) {
                pages.pop();
                if (pages.peek() instanceof MainPageView) goToMainPage();
                if (pages.peek() instanceof SettingsPageView) goToSettingsPage();
                if (pages.peek() instanceof PersonalPageView) goToPersonalPage();
                if (pages.peek() instanceof MyTweetsPageView) requestListener.listen(new OpenMyTweetsPageRequest());
                if (pages.peek() instanceof ExplorerPageView) {
                    goToExplorerPage();
                }
                if (pages.peek() instanceof TimeLinePageView)updateTimelinePage();
                if (pages.peek() instanceof NotificationPageView)goToNotificationsPage();
                if (pages.peek() instanceof SentRequestsPageView)updateSent();
                if (pages.peek() instanceof ReceivedRequestsPageView)updateReceived();
                if (pages.peek() instanceof SystemNotificationsPage)updateSystemNotifications();
            }
        }
        if (command.equals("backToMainPage")) {
            pages.clear();
            goToMainPage();
        }


    }

}
