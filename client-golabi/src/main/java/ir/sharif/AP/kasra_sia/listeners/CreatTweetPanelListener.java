package ir.sharif.AP.kasra_sia.listeners;


import ir.sharif.AP.kasra_sia.graphic.view.pagesView.personalPage.myTweetsPageView.CreatTweetPanel;
import ir.sharif.AP.kasra_sia.requests.AddTweetRequest;
import ir.sharif.AP.kasra_sia.requests.OpenMyTweetsPageRequest;
import ir.sharif.AP.kasra_sia.requests.eventObjects.CreatTweetForm;
import ir.sharif.AP.kasra_sia.util.Commons;
import ir.sharif.AP.kasra_sia.util.ImageFilter;
import ir.sharif.AP.kasra_sia.util.ImageResizer;
import ir.sharif.AP.kasra_sia.util.ImageUtil;
import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CreatTweetPanelListener implements StringListener {
    private CreatTweetPanel view;
    private String imageFilePath = null;
    private RequestListener requestListener;
    public CreatTweetPanelListener(CreatTweetPanel creatTweetPanel, RequestListener requestListener) {
        view = creatTweetPanel;
        this.requestListener = requestListener;
    }

    @Override
    public void stringEventOccurred(String string) {
        if (string.equals("selectImage")) {
            JFileChooser imageChooser = new JFileChooser();
            imageChooser.addChoosableFileFilter(new ImageFilter());
            imageChooser.setAcceptAllFileFilterUsed(false);
            int ans = imageChooser.showOpenDialog(view);
            if (ans == 0) {
                 imageFilePath = imageChooser.getSelectedFile().getPath();
                 view.getShowImagePath().setText(imageFilePath);
                try {
                    new BufferedImage(700, 700, BufferedImage.TYPE_INT_ARGB);
                    BufferedImage bf;
                    bf = ImageIO.read(new File(imageFilePath));
                    bf.flush();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        if (string.equals("tweet")) {
            String encodedImage = null;
            try {
                if (imageFilePath!=null)
                encodedImage = ImageUtil.convert(ImageIO.read(new File(imageFilePath)), FilenameUtils.getExtension(imageFilePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            requestListener.listen(new AddTweetRequest(
                        new CreatTweetForm(
                                view,
                                view.getTweetText(),
                                encodedImage)));
                    view.reset();
            imageFilePath = null;
            requestListener.listen(new OpenMyTweetsPageRequest());
        }
        if (string.equals("showImage")) {
            BufferedImage bf = null;
            try {
                if (imageFilePath!=null)
                bf = ImageIO.read(new File(imageFilePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (bf != null) {
                int a = JOptionPane.showOptionDialog(
                        null,
                        "delete this photo ?",
                        null,
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        ImageResizer.reSizeImage(Commons.TWEET_IMAGE_WIDTH,
                        Commons.TWEET_IMAGE_HEIGHT
                                , bf), null, null);
                if (a==0){
                    view.getShowImagePath().setText("");
                    imageFilePath = null;
                    bf.flush();
                }
              }
        }
    }
}
