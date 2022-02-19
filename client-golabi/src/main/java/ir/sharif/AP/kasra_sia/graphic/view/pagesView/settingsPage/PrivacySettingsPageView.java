package ir.sharif.AP.kasra_sia.graphic.view.pagesView.settingsPage;

import ir.sharif.AP.kasra_sia.graphic.view.pagesView.Page;
import ir.sharif.AP.kasra_sia.listeners.RequestListener;
import ir.sharif.AP.kasra_sia.model.LastSeen;
import ir.sharif.AP.kasra_sia.requests.ChangePasswordRequest;
import ir.sharif.AP.kasra_sia.requests.SetAccountPrivacyRequest;
import ir.sharif.AP.kasra_sia.requests.SetLastSeenRequest;
import ir.sharif.AP.kasra_sia.requests.eventObjects.ChangePasswordForm;
import ir.sharif.AP.kasra_sia.util.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PrivacySettingsPageView extends Page implements ActionListener {
    private JCheckBox privateAccountCBox = new JCheckBox("Private Account");
    private JButton changePasswordBtn = new JButton("Change Password");
    private JComboBox lastSeenComboBox;
    private JPanel lastSeen;
    private RequestListener requestListener;

    public PrivacySettingsPageView(RequestListener requestListener) {
        this.requestListener = requestListener;

        this.setBounds(0, 0, Commons.FRAME_WIDTH, Commons.FRAME_HEIGHT);
        this.setBackground(Color.white);
        this.setLayout(new GridLayout(3, 3));
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(Commons.FRAME_WIDTH, Commons.FRAME_HEIGHT));
        this.setPreferredSize(new Dimension(Commons.FRAME_WIDTH, Commons.FRAME_HEIGHT));
        Border innerBorder = BorderFactory.createTitledBorder(BorderFactory.
                createTitledBorder(null, "Privacy Settings", javax.swing.border.
                        TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.
                        TitledBorder.DEFAULT_POSITION, new Font(null, Font.BOLD, 40), Color.PINK));

        Border outerBoarder = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        this.setBorder(BorderFactory.createCompoundBorder(outerBoarder, innerBorder));

        //////////////0
        privateAccountCBox.setBackground(Color.darkGray);
        privateAccountCBox.setFocusable(false);
        privateAccountCBox.setForeground(Color.white);
        privateAccountCBox.addActionListener(this);
        privateAccountCBox.setFont(new Font(null, Font.BOLD, 25));
        privateAccountCBox.setIcon(Commons.SWITCH_OFF_IMG);
        privateAccountCBox.setSelectedIcon(Commons.SWITCH_ON_IMG);
        privateAccountCBox.setSelected(false);
        this.add(privateAccountCBox);
        //////////////1
        changePasswordBtn.setBackground(Color.gray);
        changePasswordBtn.setFocusable(false);
        changePasswordBtn.setForeground(Color.white);
        changePasswordBtn.addActionListener(this);
        changePasswordBtn.setFont(new Font(null, Font.BOLD, 25));

        this.add(changePasswordBtn);
        //////////////2
        lastSeen = new JPanel();
        JLabel label = new JLabel("set Last Seen");
        label.setFont(new Font(null, Font.BOLD, 25));
        label.setForeground(Color.white);
        lastSeen.add(label);
        lastSeen.setBackground(Color.DARK_GRAY);
        String[] as = {LastSeen.EVERYONE.toString(), LastSeen.NOBODY.toString(), LastSeen.FOLLOWINGS.toString()};
        lastSeenComboBox = new JComboBox(as);
        lastSeenComboBox.setBackground(Color.gray);
        lastSeenComboBox.setFocusable(false);
        lastSeenComboBox.setForeground(Color.white);
        lastSeenComboBox.addActionListener(this);
        lastSeenComboBox.setFont(new Font(null, Font.BOLD, 20));
        lastSeen.add(lastSeenComboBox);
        this.add(lastSeen);
    }

    public JCheckBox getPrivateAccountCBox() {
        return privateAccountCBox;
    }

    public JComboBox getLastSeenComboBox() {
        return lastSeenComboBox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == privateAccountCBox) {
            if (privateAccountCBox.isSelected())
                requestListener.listen(new SetAccountPrivacyRequest(true));
            else requestListener.listen(new SetAccountPrivacyRequest(false));
        }

        if (e.getSource() == lastSeenComboBox) {
            LastSeen lastSeen =
                    switch (lastSeenComboBox.getSelectedItem().toString()) {
                        case "NOBODY" -> LastSeen.NOBODY;
                        case "EVERYONE" -> LastSeen.EVERYONE;
                        case "FOLLOWINGS" -> LastSeen.FOLLOWINGS;
                        default -> null;
                    };
            requestListener.listen(new SetLastSeenRequest(lastSeen));
        }
        if (e.getSource() == changePasswordBtn) {
            JPasswordField oldPasswordField = new JPasswordField();
            JPasswordField newPasswordField = new JPasswordField();

            int ans = JOptionPane.showConfirmDialog(null, changePasswordPanel(newPasswordField, oldPasswordField), "change password", JOptionPane.OK_CANCEL_OPTION);

            if (ans == 0)
                requestListener.listen(new ChangePasswordRequest(
                        new ChangePasswordForm(this,
                                oldPasswordField.getText(),
                                newPasswordField.getText())));
        }
    }

    private JPanel changePasswordPanel(JPasswordField newPasswordField, JPasswordField oldPasswordField) {

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 0.1;
        gc.weighty = 0.1;
//        /////////////
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("password: "), gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;


        oldPasswordField.setPreferredSize(new Dimension(150, 20));
        panel.add(oldPasswordField, gc);
//       /////////////////
        gc.gridx = 0;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("new password: "), gc);

        gc.gridx = 1;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;


        oldPasswordField.setPreferredSize(new Dimension(150, 20));
        newPasswordField.setPreferredSize(new Dimension(150, 20));
        panel.add(newPasswordField, gc);


        return panel;
    }

    public void setPage(boolean isPrivate, LastSeen lastSeen) {
        lastSeenComboBox.setSelectedItem(lastSeen.toString());
        privateAccountCBox.setSelected(isPrivate);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
