package ir.sharif.AP.kasra_sia.graphic.view.pagesView.personalPage;
import ir.sharif.AP.kasra_sia.graphic.view.ComponentsScrollPanel;
import ir.sharif.AP.kasra_sia.graphic.view.pagesView.Page;
import ir.sharif.AP.kasra_sia.model.Notification;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class SystemNotificationsPage extends Page {
    private final ComponentsScrollPanel<JPanel> componentsScrollPanel;
    public SystemNotificationsPage() {
        this.setLayout(new BorderLayout());
        componentsScrollPanel = new ComponentsScrollPanel<>();
        this.add(componentsScrollPanel,BorderLayout.CENTER);

    }
    public void setPage(LinkedList<Notification> notifications){
        LinkedList<JPanel> temp = new LinkedList<>();
        notifications.forEach(notification->{
            JPanel temp1 = new JPanel();
            temp1.add(new JLabel( notification.toString()));

            temp.add(temp1);
        });
            componentsScrollPanel.setComponentsList(temp);
    }
}
