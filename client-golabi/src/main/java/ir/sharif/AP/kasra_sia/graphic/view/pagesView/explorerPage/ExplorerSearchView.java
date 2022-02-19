package ir.sharif.AP.kasra_sia.graphic.view.pagesView.explorerPage;

import ir.sharif.AP.kasra_sia.graphic.view.pagesView.Page;
import ir.sharif.AP.kasra_sia.listeners.RequestListener;
import ir.sharif.AP.kasra_sia.requests.OpenUserPageRequest;
import ir.sharif.AP.kasra_sia.requests.SearchUserRequest;
import ir.sharif.AP.kasra_sia.util.Commons;
import ir.sharif.AP.kasra_sia.util.ImageResizer;
import ir.sharif.AP.kasra_sia.util.ImageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;

public class ExplorerSearchView extends Page implements ActionListener {
    private JTextField usernameField = new JTextField("username", 20);
    private JButton searchBtn = new JButton("search");
    private JButton foundUserBtn = new JButton();
    private String foundUsername;
    //    private LinkedList<StringListener> stringListeners = new LinkedList<>();
//    private EventListener eventListener;
    private JLabel notFoundWarning;
    private RequestListener requestListener;

    public ExplorerSearchView(RequestListener requestListener) {
        this.requestListener = requestListener;
        this.setBorder(BorderFactory.createTitledBorder("find"));
        this.setBackground(Color.gray);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 1;
        gc.weighty = 1;
        ////////////// 0
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_END;
        usernameField.setPreferredSize(new Dimension(200, 50));
//        usernameField.setFocusable(false);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 10));

        this.add(usernameField, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 20, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        searchBtn.setBackground(Color.pink);
        searchBtn.setFocusable(false);
        searchBtn.setBorderPainted(false);
        searchBtn.addActionListener(this);
        this.add(searchBtn, gc);


        ////////////// 1

        gc.gridx = 0;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.WEST;
        this.add(new JLabel("result :"), gc);

        gc.gridx = 0;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.EAST;
        foundUserBtn.setForeground(Color.black);
        foundUserBtn.setBackground(Color.gray);
        foundUserBtn.setFocusable(false);
        foundUserBtn.setBorderPainted(false);
        foundUserBtn.setEnabled(false);
        foundUserBtn.setVisible(false);
        foundUserBtn.addActionListener(this);
        notFoundWarning = new JLabel("<html><font color=red> user not found </font>");
        notFoundWarning.setVisible(false);
        notFoundWarning.setFont(new Font(null, Font.BOLD, 20));
        foundUserBtn.setFont(new Font(null, Font.BOLD, 20));
        this.add(notFoundWarning, gc);
        this.add(foundUserBtn, gc);
        //////////////2


    }

    public void setPage(String username, String encodedImage) {
        if (username != null) {
            foundUserBtn.setEnabled(true);
            foundUserBtn.setVisible(true);
            notFoundWarning.setVisible(false);
            if (encodedImage != null) {
                try {
                    foundUserBtn.setIcon(ImageResizer.reSizeImage(Commons.PROFILE_SMALL_IMG_WIDTH, Commons.PROFILE_SMALL_IMG_HEIGHT, ImageUtil.convert(encodedImage)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            foundUsername = username;
            foundUserBtn.setText("@" + username);
        } else {
            notFoundWarning.setVisible(true);
            foundUserBtn.setVisible(false);
            foundUserBtn.setEnabled(false);
        }
        revalidate();
        repaint();
    }
//    public void setEventListener(EventListener eventListener) {
//        this.eventListener = eventListener;
//    }
//
//    public void addStringListeners(StringListener stringListener) {
//        this.stringListeners.add(stringListener);
//    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == foundUserBtn) {
            requestListener.listen(new OpenUserPageRequest(foundUsername));
        }
        if (e.getSource() == searchBtn)
            if (!usernameField.getText().equals("") && !usernameField.getText().equals("username"))
                requestListener.listen(new SearchUserRequest(usernameField.getText()));


    }
}


