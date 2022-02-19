package ir.sharif.AP.kasra_sia.graphic.view;
import ir.sharif.AP.kasra_sia.graphic.view.pagesView.mainPage.MainPageView;
import ir.sharif.AP.kasra_sia.listeners.StringListener;
import ir.sharif.AP.kasra_sia.util.*;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private ToolsBar toolsBar;
    private CenterPanel centerPanel;
    private MainPageView mainPageView;
    private StringListener stringListener;
    private static MainPanel instance;
    public static Loop loop;

    public MainPanel() {
        this.setLayout(new BorderLayout());
        instance = this;
//        centerToToolbarStringListener = new CenterToToolbarStringListener(centerPanel);
//        toolsBar.addListener(centerToToolbarStringListener);
//
////        centerToToolbarStringListener.setStringListener(new StringListener() {
////            @Override
////            public void stringEventOccurred(String string) {
////                if (string.equals("userLoggedIn")) {
////                    centerPanel.removeAll();
////                    toolsBar.update();
////                    cardLayout = new CardLayout();
////                    container = centerPanel;
////                    container.setLayout(cardLayout);
////                    mainPageView = new MainPageView();
////                    container.add(mainPageView);
////                    mainPageView.addListener(new SettingsPageToMainPageListener(container, cardLayout));
////                    mainPageView.addListener(new PersonalPageToMainPageListener(container,cardLayout));
////                    mainPageView.addListener(new ExplorerPageToMainPageListener(container,cardLayout));
////                    mainPageView.addListener(new TimeLinePageToMainPageListener());
////                    mainPageView.addListener(new MessagingPageToMainPageListener());
////                    MainFrame.refreshFrame();
////                }
////            }
////        });
//
    }
    public void setToolsBar(){
        toolsBar.update();
    }
    public void initialize(StringListener stringListener){
        centerPanel = new CenterPanel();
        toolsBar = new ToolsBar(stringListener);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(toolsBar, BorderLayout.NORTH);
    }

    public void setCenterPanel(JPanel panel){
        centerPanel.removeAll();
        centerPanel.add(panel,BorderLayout.CENTER);
        repaint();
        revalidate();
    }
}