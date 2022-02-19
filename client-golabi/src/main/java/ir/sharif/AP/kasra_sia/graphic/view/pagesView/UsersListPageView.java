package ir.sharif.AP.kasra_sia.graphic.view.pagesView;

import ir.sharif.AP.kasra_sia.graphic.view.ComponentsScrollPanel;
import ir.sharif.AP.kasra_sia.listeners.RequestListener;
import ir.sharif.AP.kasra_sia.listeners.StringListener;
import ir.sharif.AP.kasra_sia.requests.OpenUserPageRequest;
import ir.sharif.AP.kasra_sia.util.Commons;
import ir.sharif.AP.kasra_sia.util.ImageResizer;
import ir.sharif.AP.kasra_sia.util.ImageUtil;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;


public class UsersListPageView extends Page {
    private LinkedList<StringListener> stringListeners = new LinkedList<>();
    private ComponentsScrollPanel<JPanel> componentsScrollPanel ;
    private RequestListener requestListener;

    public UsersListPageView(RequestListener requestListener) {
        this.requestListener = requestListener;
        this.setLayout(new BorderLayout());
        componentsScrollPanel = new ComponentsScrollPanel<>();
        this.add(componentsScrollPanel,BorderLayout.CENTER);
    }

    public void setPage(LinkedHashMap<String, String> users) {
        LinkedList<JPanel> temp = new LinkedList<>();
        users.forEach( (username,image) -> {
            JPanel userPanel = new JPanel();
            userPanel.setPreferredSize(new Dimension(100,30));
            userPanel.setLayout(new BorderLayout());
            JLabel imageLabel = new JLabel();
            if (image!=null) {
                try {
                    imageLabel.setIcon(ImageResizer.reSizeImage(Commons.PROFILE_SMALL_IMG_WIDTH,Commons.PROFILE_SMALL_IMG_HEIGHT,ImageUtil.convert(image))
);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            JButton userBtn = new JButton("@"+username);
            userBtn.setBorder(null);
            userBtn.setBackground(Color.WHITE);
            userPanel.add(imageLabel,BorderLayout.WEST);
            userPanel.add(userBtn,BorderLayout.CENTER);
            userPanel.add(new JLabel("   "),BorderLayout.EAST);
            userPanel.setBackground(Color.WHITE);
            temp.add(userPanel);
            userBtn.addActionListener(e ->
                    requestListener.listen(new OpenUserPageRequest(username))
                    );
        });
        componentsScrollPanel.setComponentsList(temp);
        componentsScrollPanel.revalidate();
        componentsScrollPanel.repaint();
    }
}
