package ir.sharif.AP.kasra_sia.graphic.view.pagesView.personalPage.myTweetsPageView.commentsPageView;

import ir.sharif.AP.kasra_sia.demoModel.DemoComment;
import ir.sharif.AP.kasra_sia.graphic.view.ComponentsScrollPanel;
import ir.sharif.AP.kasra_sia.graphic.view.modelsView.CommentView;
import ir.sharif.AP.kasra_sia.graphic.view.modelsView.TweetView;
import ir.sharif.AP.kasra_sia.graphic.view.pagesView.Page;
import ir.sharif.AP.kasra_sia.listeners.CommentListener;
import ir.sharif.AP.kasra_sia.listeners.CreatCommentPanelListener;
import ir.sharif.AP.kasra_sia.listeners.RequestListener;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;


public class CommentsPageView extends Page {
    private CreatCommentPanel commentPanel ;
    private ComponentsScrollPanel componentsScrollPanel = new ComponentsScrollPanel();
    private CreatCommentPanelListener creatCommentPanelListener;
    private RequestListener requestListener;
//    private LinkedList<StringListener> stringListener = new LinkedList<>();
    private int parentID;
    public CommentsPageView(int parentID,RequestListener requestListener) {
        this.parentID = parentID;
        this.requestListener = requestListener;
        commentPanel = new CreatCommentPanel();
        this.setLayout(new BorderLayout());
        this.add(commentPanel, BorderLayout.SOUTH);
        creatCommentPanelListener = new CreatCommentPanelListener(commentPanel,parentID,requestListener);
        commentPanel.setListener(creatCommentPanelListener);
        this.add(componentsScrollPanel, BorderLayout.CENTER);
    }
    public void setPage(LinkedList<DemoComment> comments){
        LinkedList<CommentView> commentViews = new LinkedList<>();
        comments.forEach(comment->{
            CommentView commentView = new CommentView(
                    comment.getText(),
                    comment.getUsername(),
                    comment.getEncodedProfilePhoto(),
                    comment.getLikes(),
                    comment.isOwnerIsLiked(),
                    comment.getTime(),
                    comment.getParentID(),
                    comment.getID()
            );
            commentViews.add(commentView);
            commentView.setStringListener(new CommentListener(parentID,comment,requestListener));
        });
        componentsScrollPanel.setComponentsList(commentViews);
        repaint();
        revalidate();

    }
}
