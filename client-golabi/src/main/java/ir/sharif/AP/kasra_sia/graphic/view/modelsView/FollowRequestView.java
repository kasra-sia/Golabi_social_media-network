package ir.sharif.AP.kasra_sia.graphic.view.modelsView;

import ir.sharif.AP.kasra_sia.listeners.RequestListener;
import ir.sharif.AP.kasra_sia.requests.ManageFollowReqRequest;
import ir.sharif.AP.kasra_sia.requests.OpenUserPageRequest;
import ir.sharif.AP.kasra_sia.requests.eventObjects.FollowRequestEvent;
import ir.sharif.AP.kasra_sia.util.Commons;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;


public class FollowRequestView extends JPanel implements ActionListener {
    private final JButton acceptBtn;
    private final JButton declineBtn;
    private final JButton userBtn;
//    private EventListener eventListener;
//    private LinkedList<StringListener> stringListeners = new LinkedList<>();
    private String username;
    private RequestListener requestListener;

    public FollowRequestView(String username, RequestListener requestListener) {
        this.username = username;
        this.requestListener = requestListener;
        this.setBackground(Commons.FOLLOW_REQUEST_COLOR);
        Border innerBorder = BorderFactory.createTitledBorder(BorderFactory.
                createTitledBorder(null, "request", javax.swing.border.
                        TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.
                        TitledBorder.DEFAULT_POSITION, new Font(null, Font.BOLD, 10), Color.lightGray));
        Border outerBoarder = BorderFactory.createEmptyBorder(1, 1, 1, 1);
        this.setBorder(BorderFactory.createCompoundBorder(outerBoarder, innerBorder));
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.weightx =0.1;
        gc.weighty =0.1;
//      1------------------
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 50, 0, 0);
        gc.anchor = GridBagConstraints.NORTH;
        userBtn = new JButton("@"+username);
        userBtn.setBackground(Commons.FOLLOW_REQUEST_COLOR);
        userBtn.setFocusable(false);
        userBtn.setBorder(null);
        userBtn.addActionListener(this);
        this.add(userBtn,gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 100);
        gc.anchor = GridBagConstraints.NORTH;

        this.add(new Label(" want's to follow you"),gc);
//      2-------------------
        gc.gridx = 0;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 40);
        gc.anchor = GridBagConstraints.LINE_START;
        acceptBtn = new JButton("accept");
        acceptBtn.addActionListener(this);
        this.add(acceptBtn,gc);

//      3--------------------
        gc.gridx = 1;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_END;
        declineBtn = new JButton("decline");
        declineBtn.addActionListener(this);
        this.add(declineBtn,gc);
    }

//    public void setEventListener(EventListener eventListener) {
//        this.eventListener = eventListener;
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == declineBtn) {
            try {
//                eventListener.eventOccurred(new FollowRequestEvent(this, username, false));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        if (e.getSource() == acceptBtn)
          requestListener.listen(new ManageFollowReqRequest(new FollowRequestEvent(this, username, true)));
        if (e.getSource() == userBtn) {
                   requestListener.listen(new OpenUserPageRequest(username));
        }

    }

}
