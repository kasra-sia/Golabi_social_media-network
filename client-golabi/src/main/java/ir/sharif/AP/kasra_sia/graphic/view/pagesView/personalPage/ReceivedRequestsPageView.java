package ir.sharif.AP.kasra_sia.graphic.view.pagesView.personalPage;

import ir.sharif.AP.kasra_sia.graphic.view.ComponentsScrollPanel;
import ir.sharif.AP.kasra_sia.graphic.view.modelsView.FollowRequestView;
import ir.sharif.AP.kasra_sia.graphic.view.pagesView.Page;
import ir.sharif.AP.kasra_sia.listeners.RequestListener;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class ReceivedRequestsPageView extends Page {
    private ComponentsScrollPanel<FollowRequestView> componentsScrollPanel = new ComponentsScrollPanel();
    private RequestListener requestListener;

    //    private LinkedList<StringListener> stringListener = new LinkedList<>();
    public ReceivedRequestsPageView(RequestListener requestListener) {
        this.requestListener = requestListener;
        this.setLayout(new BorderLayout());
        this.add(componentsScrollPanel, BorderLayout.CENTER);
    }
    public void setPage(LinkedList<String> usernames){
        LinkedList<FollowRequestView> temp = new LinkedList<>();
        for (String username:usernames) {
            FollowRequestView followRequestView = new FollowRequestView(username,requestListener);
            temp.add(followRequestView);
        }
        componentsScrollPanel.setComponentsList(temp);
        componentsScrollPanel.revalidate();
        componentsScrollPanel.repaint();
    }
}
