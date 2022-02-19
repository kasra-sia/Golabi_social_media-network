package ir.sharif.AP.kasra_sia.graphic.view.pagesView.personalPage;
import ir.sharif.AP.kasra_sia.graphic.view.ComponentsScrollPanel;
import ir.sharif.AP.kasra_sia.graphic.view.pagesView.Page;
import ir.sharif.AP.kasra_sia.model.RequestStatus;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class SentRequestsPageView extends Page {
    private final ComponentsScrollPanel<JPanel> componentsScrollPanel;
    public SentRequestsPageView() {
        this.setLayout(new BorderLayout());
        componentsScrollPanel = new ComponentsScrollPanel();
        this.add(componentsScrollPanel,BorderLayout.CENTER);
    }

    public void setPage(LinkedHashMap<String , RequestStatus> sentRequests) {
        LinkedList<JPanel> temp = new LinkedList<>();
        sentRequests.forEach((username, requestStatus) ->{
            JPanel jPanel = new JPanel();
            jPanel.add(new Label("to @"+username+" --> status : "+requestStatus.toString()));
            temp.add(jPanel);
        } );
        componentsScrollPanel.setComponentsList(temp);
    }
}
