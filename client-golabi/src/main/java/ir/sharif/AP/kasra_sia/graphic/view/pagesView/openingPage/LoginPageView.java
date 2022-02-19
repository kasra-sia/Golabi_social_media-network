package ir.sharif.AP.kasra_sia.graphic.view.pagesView.openingPage;


import ir.sharif.AP.kasra_sia.graphic.view.pagesView.Page;
import ir.sharif.AP.kasra_sia.listeners.RequestListener;
import ir.sharif.AP.kasra_sia.requests.LoginRequest;
import ir.sharif.AP.kasra_sia.requests.eventObjects.LoginForm;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPageView extends Page implements ActionListener {
    private JTextField usernameField = new JTextField(15);
    private JPasswordField passwordField = new JPasswordField();
    private JButton loginBtn = new JButton("login");
    private JLabel wrongUsernameOrPasswordWarning;
    private RequestListener requestListener;


    public LoginPageView(RequestListener requestListener) {
        this.requestListener = requestListener;
        Border innerBorder = BorderFactory.createTitledBorder("login");
        Border outerBoarder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        this.setPreferredSize(new Dimension(600, 400));
        this.setBorder(BorderFactory.createCompoundBorder(outerBoarder, innerBorder));
        loginBtn.setBackground(Color.CYAN);
        loginBtn.addActionListener(this);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 0.1;
        gc.weighty = 0.1;
        //////////////// 0
        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 200);
        gc.anchor = GridBagConstraints.LINE_END;
        wrongUsernameOrPasswordWarning = new JLabel("<html><font color=red>Wrong username or password </font>");
        wrongUsernameOrPasswordWarning.setVisible(false);
        this.add(wrongUsernameOrPasswordWarning, gc);
        /////////////// 1
        gc.gridx = 0;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 7);
        gc.anchor = GridBagConstraints.LINE_END;
        this.add(new JLabel("username: "), gc);

        gc.gridx = 1;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 7);
        gc.anchor = GridBagConstraints.LINE_START;
        this.add(usernameField, gc);

        gc.gridx = 2;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 7);
        gc.anchor = GridBagConstraints.LINE_START;

        /////////////// 2
        gc.gridx = 0;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 7);
        gc.anchor = GridBagConstraints.LINE_END;
        this.add(new JLabel("password: "), gc);

        gc.gridx = 1;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        passwordField.setPreferredSize(new Dimension(140, 20));
        this.add(passwordField, gc);

        gc.gridx = 2;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 7);
        gc.anchor = GridBagConstraints.LINE_START;

        /////////////// 3
        gc.weightx = 1;
        gc.weighty = 2;

        gc.gridx = 1;
        gc.gridy = 4;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        this.add(loginBtn, gc);
    }

    public String getUsernameField() {
        return usernameField.getText();
    }

    public String getPasswordField() {
        return passwordField.getText();
    }

//    public void setStringListener(StringListener stringListener) {
//        this.stringListener = stringListener;
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ((JButton) e.getSource() == loginBtn) {
            resetWarnings();
            requestListener.listen(new LoginRequest(new LoginForm(
                    this,
                    getUsernameField(),
                    getPasswordField()
            )));

        }
    }

    private void resetWarnings() {
        wrongUsernameOrPasswordWarning.setVisible(false);
    }

    public void setWarnings(String message) {
        if (message.equals("wrongUsernameOrPassword")) {
            wrongUsernameOrPasswordWarning.setVisible(true);
        }
    }
}
