package ir.sharif.AP.kasra_sia.graphic.view.pagesView.explorerPage;
import ir.sharif.AP.kasra_sia.graphic.view.pagesView.Page;
import ir.sharif.AP.kasra_sia.listeners.StringListener;
import ir.sharif.AP.kasra_sia.util.Commons;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class ExplorerPageView extends Page implements ActionListener {
    private JButton findBtn = new JButton();
    private JButton tweetsExplorerBtn = new JButton();
    private StringListener stringListener;
    public ExplorerPageView() {
        this.setBounds(0, 0, Commons.FRAME_WIDTH, Commons.FRAME_HEIGHT);
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(Commons.FRAME_WIDTH, Commons.CENTER_PANEL_HEIGHT));
        Border innerBorder = BorderFactory.createTitledBorder(BorderFactory.
                createTitledBorder(null, "Explorer Page", javax.swing.border.
                        TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.
                        TitledBorder.DEFAULT_POSITION, new Font(null, Font.BOLD, 20), Color.lightGray));
        Border outerBoarder = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        this.setBorder(BorderFactory.createCompoundBorder(outerBoarder, innerBorder));
        this.setLayout(new BorderLayout());

        this.setLayout(new FlowLayout(FlowLayout.RIGHT));


        //---------------find----------------
        findBtn = new JButton();
        findBtn.setFocusable(false);
        findBtn.setBorderPainted(false);
        findBtn.setBorder(null);
        findBtn.setFocusPainted(false);
        findBtn.setIcon(Commons.FIND_IMG);
        findBtn.setBackground(Color.black);
        this.add(findBtn);
        findBtn.addActionListener(this);

        //--------------explore------------------
        tweetsExplorerBtn = new JButton();
        tweetsExplorerBtn.setBackground(Color.black);
        tweetsExplorerBtn.setFocusable(false);
        tweetsExplorerBtn.setBorderPainted(false);
        tweetsExplorerBtn.setFocusPainted(false);
        tweetsExplorerBtn.setBorder(null);
        tweetsExplorerBtn.setIcon(Commons.EXPLORER_IMG);
        this.add(tweetsExplorerBtn);
        tweetsExplorerBtn.addActionListener(this);

    }


//    public void addStringListener(StringListener stringListener) {
//        this.stringListeners.add(stringListener);
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
            if (e.getSource() == findBtn) {
                stringListener.stringEventOccurred("find");
            }
            if (e.getSource() == tweetsExplorerBtn)
                stringListener.stringEventOccurred("tweetExplorer");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Commons.BACKGROUND_IMG.getImage(), 0, 0, null);
    }

    public void setStringListener(StringListener stringListener) {
        this.stringListener = stringListener;
    }
}
