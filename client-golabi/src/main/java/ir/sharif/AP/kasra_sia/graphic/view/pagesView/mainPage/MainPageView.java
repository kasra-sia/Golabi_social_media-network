package ir.sharif.AP.kasra_sia.graphic.view.pagesView.mainPage;

import ir.sharif.AP.kasra_sia.graphic.view.pagesView.Page;
import ir.sharif.AP.kasra_sia.listeners.StringListener;
import ir.sharif.AP.kasra_sia.util.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPageView extends Page implements ActionListener {
    private JButton personalPageBtn = new JButton("PersonalPage");
    private JButton timeLinePageBtn = new JButton("timeLinePage");
    private JButton settingsPageBtn = new JButton("settingsPage");
    private JButton explorerPageBtn = new JButton("explorerPage");
    private JButton messagingPageBtn = new JButton("messagingPage");
    private StringListener stringListener;

    public MainPageView() {
        this.setBounds(0, 0, Commons.FRAME_WIDTH, Commons.FRAME_HEIGHT);
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(Commons.FRAME_WIDTH, Commons.FRAME_HEIGHT));
        this.setPreferredSize(new Dimension(Commons.FRAME_WIDTH, Commons.FRAME_HEIGHT));
        Border innerBorder = BorderFactory.createTitledBorder(BorderFactory.
                createTitledBorder(null, "Main page", javax.swing.border.
                        TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.
                        TitledBorder.DEFAULT_POSITION, new Font(null, Font.BOLD, 40), Color.lightGray));

        Border outerBoarder = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        this.setBorder(BorderFactory.createCompoundBorder(outerBoarder, innerBorder));
        this.setLayout(new GridBagLayout());
        GridLayout gd = new GridLayout(1, 5);
        this.setLayout(gd);

        //////////////0
        personalPageBtn.setBackground(Color.gray);
        personalPageBtn.setFocusable(false);
        personalPageBtn.setForeground(Color.white);
        personalPageBtn.addActionListener(this);
        personalPageBtn.setFont(new Font(null, Font.BOLD, 25));
        this.add(personalPageBtn);
        //////////////1
        timeLinePageBtn.setBackground(Color.darkGray);
        timeLinePageBtn.setFocusable(false);
        timeLinePageBtn.addActionListener(this);
        timeLinePageBtn.setForeground(Color.white);
        timeLinePageBtn.setFont(new Font(null, Font.BOLD, 25));
        this.add(timeLinePageBtn);
        //////////////2
        explorerPageBtn.setBackground(Color.gray);
        explorerPageBtn.setFocusable(false);
        explorerPageBtn.addActionListener(this);
        explorerPageBtn.setForeground(Color.white);
        explorerPageBtn.setFont(new Font(null, Font.BOLD, 25));
        this.add(explorerPageBtn);
        //////////////3
        messagingPageBtn.setBackground(Color.darkGray);
        messagingPageBtn.setForeground(Color.white);
        messagingPageBtn.addActionListener(this);
        messagingPageBtn.setFont(new Font(null, Font.BOLD, 25));
        messagingPageBtn.setFocusable(false);
        this.add(messagingPageBtn);
        //////////////4
        settingsPageBtn.setBackground(Color.gray);
        settingsPageBtn.setForeground(Color.white);
        settingsPageBtn.addActionListener(this);
        settingsPageBtn.setFont(new Font(null, Font.BOLD, 25));
        settingsPageBtn.setFocusable(false);
        this.add(settingsPageBtn);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
            if ((JButton) e.getSource() == settingsPageBtn)
                stringListener.stringEventOccurred("settingsPage");
            if (e.getSource() == personalPageBtn)
                stringListener.stringEventOccurred("personalPage");
            if (e.getSource().equals(explorerPageBtn))
                stringListener.stringEventOccurred("explorerPage");
            if (e.getSource() == messagingPageBtn)
                stringListener.stringEventOccurred("messagingPage");
            if (e.getSource() == timeLinePageBtn)
                stringListener.stringEventOccurred("timeLinePage");
    }

    public void setStringListener(StringListener stringListener) {
        this.stringListener = stringListener;
    }
}
