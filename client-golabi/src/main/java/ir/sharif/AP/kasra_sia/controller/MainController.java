package ir.sharif.AP.kasra_sia.controller;

import ir.sharif.AP.kasra_sia.graphic.GraphicalAgent;
import ir.sharif.AP.kasra_sia.graphic.view.pagesView.explorerPage.ExplorerSearchView;
import ir.sharif.AP.kasra_sia.graphic.view.pagesView.personalPage.ShowInfoPageView;
import ir.sharif.AP.kasra_sia.graphic.view.pagesView.settingsPage.PrivacySettingsPageView;
import ir.sharif.AP.kasra_sia.model.LastSeen;
import ir.sharif.AP.kasra_sia.model.Notification;
import ir.sharif.AP.kasra_sia.model.RequestStatus;
import ir.sharif.AP.kasra_sia.network.RequestSender;
import ir.sharif.AP.kasra_sia.requests.Request;
import ir.sharif.AP.kasra_sia.requests.eventObjects.MyInfoForm;
import ir.sharif.AP.kasra_sia.requests.eventObjects.SetUserViewForm;
import ir.sharif.AP.kasra_sia.responses.Response;
import ir.sharif.AP.kasra_sia.responses.ResponseVisitor;
import ir.sharif.AP.kasra_sia.util.Loop;
import ir.sharif.AP.kasra_sia.graphic.view.pagesView.openingPage.LoginPageView;
import ir.sharif.AP.kasra_sia.graphic.view.pagesView.openingPage.RegistrationPageView;
import ir.sharif.AP.kasra_sia.demoModel.*;

import javax.swing.*;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class MainController implements ResponseVisitor {
    private final RequestSender requestSender;
    private final List<Request> requests;
    private final Loop loop;
    private final GraphicalAgent graphicalAgent;

    public MainController(RequestSender requestSender) {
        this.requestSender = requestSender;
        this.requests = new LinkedList<>();
        this.loop = new Loop(10, this::sendRequests);
        this.graphicalAgent = new GraphicalAgent(this::addRequest);
    }

    public void start() {
        loop.start();
        graphicalAgent.initialize();
//        graphicalAgent.goToMainPanel();
    }

    private void addRequest(Request request) {
        synchronized (requests) {
            requests.add(request);
        }
    }

    private void sendRequests() {
        List<Request> temp;
        synchronized (requests) {
            temp = new LinkedList<>(requests);
            requests.clear();
        }
        for (Request event : temp) {
            Response response = requestSender.send(event);
            try {
                response.visit(this);
            } catch (NullPointerException nullPointerException) {
                System.out.println("no response needed");
            }
        }
    }

    @Override
    public void visitSignInResult(boolean b, String s) {

        if (b)
            graphicalAgent.goToMainPage();
        else if (graphicalAgent.currentPage instanceof RegistrationPageView)
            ((RegistrationPageView) graphicalAgent.currentPage).setWarnings(s);
        else if (graphicalAgent.currentPage instanceof LoginPageView)
            ((LoginPageView) graphicalAgent.currentPage).setWarnings(s);
    }

    @Override
    public void openPrivacyPage(boolean b, LastSeen lastSeen) {
        graphicalAgent.goToPrivacyPage(b, lastSeen);
    }

    @Override
    public void openMyInfoPage(MyInfoForm myInfoForm) {
        try {
            graphicalAgent.goToMyInfoPage(myInfoForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void openMyTweetsPage(LinkedList<DemoTweet> linkedList) {
        graphicalAgent.goToMyTweetsPage(linkedList);
    }

    @Override
    public void openTweetExplorePage(LinkedList<DemoTweet> linkedList) {
        graphicalAgent.goToExplorerGlobalPage(linkedList);
    }

    @Override
    public void openCommentsPage(LinkedList<DemoComment> linkedList,int parentID) {
        graphicalAgent.goToCommentPage(linkedList,parentID);
    }

    @Override
    public void openUserPage(SetUserViewForm setUserViewForm) {
        graphicalAgent.goToUserViewPage(setUserViewForm);
    }

    @Override
    public void openTimeLinePage(LinkedList<DemoTweet> linkedList) {
        graphicalAgent.goToTimeLinePage(linkedList);
    }

    @Override
    public void openReceivedRequestPage(LinkedList<String> linkedList) {
        graphicalAgent.goToReceivedRequestPage(linkedList);
    }

    @Override
    public void openSentRequestPage(LinkedHashMap<String, RequestStatus> linkedHashMap) {
        graphicalAgent.goToSentRequestPage(linkedHashMap);
    }

    @Override
    public void openUsersListPage(LinkedHashMap<String, String> users) {
        graphicalAgent.goToUsersListPage(users);
    }

    @Override
    public void openSystemNotificationPage(LinkedList<Notification> linkedList) {
        graphicalAgent.goToSystemNotificationsPage(linkedList);
    }

    @Override
    public void foundUser(String s,String encodedImage) {
        if (graphicalAgent.currentPage instanceof ExplorerSearchView)
            ((ExplorerSearchView) graphicalAgent.currentPage).setPage(s,encodedImage);
    }

    @Override
    public void visitStringResponse(String s) {

    }

    @Override
    public void visitLogoutResult(boolean b) {
        if (b)
            graphicalAgent.goToOpeningPage();
    }

    @Override
    public void visitChangePasswordResponse(String message) {
        if (graphicalAgent.currentPage instanceof PrivacySettingsPageView)
            ((PrivacySettingsPageView) graphicalAgent.currentPage).showMessage(message);
    }

    @Override
    public void visitMyInfoEditResult(boolean b, String s) {
        if (graphicalAgent.currentPage instanceof ShowInfoPageView)
            ((ShowInfoPageView) graphicalAgent.currentPage).setWarnings(b,s);
    }

    @Override
    public void showMessage(String s) {
        JOptionPane.showMessageDialog(null,s);
    }
}
