package ir.sharif.AP.kasra_sia.graphic.view.modelsView;
import ir.sharif.AP.kasra_sia.listeners.StringListener;
import ir.sharif.AP.kasra_sia.util.Commons;
import ir.sharif.AP.kasra_sia.util.ImageResizer;
import ir.sharif.AP.kasra_sia.util.ImageUtil;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CommentView extends JPanel implements ActionListener {
    private JLabel userLabel = new JLabel();
    private JLabel timeLabel ;
    private JTextArea textArea ;
    private JCheckBox likeCheckBox ;
    private int parentID;
    private int commentID;
    private StringListener stringListener;
    public CommentView(String commentText, String username, String encodedProfilePhoto, long likes, boolean ownerLiked, String time, int parentID, int commentID) {
        this.parentID = parentID;
        this.commentID = commentID;
        this.setBackground(Commons.COMMENT_COLOR);
        Border innerBorder = BorderFactory.createTitledBorder(BorderFactory.
                createTitledBorder(null, "comment", javax.swing.border.
                        TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.
                        TitledBorder.DEFAULT_POSITION, new Font(null, Font.BOLD, 10), Color.lightGray));
        Border outerBoarder = BorderFactory.createEmptyBorder(1, 1, 1, 1);
        this.setBorder(BorderFactory.createCompoundBorder(outerBoarder, innerBorder));
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.weightx =0.1;
        gc.weighty =0.1;

        /////////////1
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 40);
        gc.anchor = GridBagConstraints.NORTHWEST;
        userLabel.setForeground(Color.WHITE);
        this.userLabel.setText("@"+username);
        if (encodedProfilePhoto != null) {
            try {
                this.userLabel.setIcon(ImageResizer.reSizeImage(Commons.PROFILE_SMALL_IMG_WIDTH, Commons.PROFILE_SMALL_IMG_HEIGHT, ImageUtil.convert(encodedProfilePhoto)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
            this.add(userLabel,gc);
        //////////////2

        gc.gridx = 1;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        textArea = new JTextArea(2,22);
        textArea.setBackground(Color.white);
        textArea.setCaretColor(Color.black);
        textArea.setForeground(Color.black);
        textArea.setEditable(false);
        textArea.setBorder(BorderFactory.createLineBorder(Color.white,10));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.textArea.setText(commentText);
        this.add(scrollPane,gc);

        //////////////3

        gc.gridx = 2;
        gc.gridy = 1;
        gc.insets = new Insets(30, 0, 0, 0);
        gc.anchor = GridBagConstraints.CENTER;
        likeCheckBox = new JCheckBox();
        likeCheckBox.setIcon(Commons.DISLIKE_IMG);
        likeCheckBox.setSelectedIcon(Commons.LIKE_IMG);
        likeCheckBox.setBackground(Commons.COMMENT_COLOR);
        likeCheckBox.setBorderPainted(false);
        likeCheckBox.setFocusable(false);
        likeCheckBox.addActionListener(this);
        likeCheckBox.setForeground(Color.white);
        this.likeCheckBox.setText(String.valueOf(likes));
        if (ownerLiked)
            this.likeCheckBox.setSelected(true);
        else this.likeCheckBox.setSelected(false);
        this.add(likeCheckBox,gc);
        /////////////5
        gc.gridx = 2;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LAST_LINE_END;
        timeLabel = new JLabel();
        timeLabel.setForeground(Color.white);
        timeLabel.setText(time);
        this.add(timeLabel, gc);
    }
    public void setStringListener(StringListener stringListener){
        this.stringListener = stringListener;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
                if (e.getSource() == likeCheckBox) {
                if (likeCheckBox.isSelected())
                    stringListener.stringEventOccurred("like");
                else stringListener.stringEventOccurred("unLike");
            }
        }
    }


