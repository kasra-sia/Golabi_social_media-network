package ir.sharif.AP.kasra_sia.listeners;

import ir.sharif.AP.kasra_sia.graphic.view.pagesView.personalPage.myTweetsPageView.commentsPageView.CommentsPageView;
import ir.sharif.AP.kasra_sia.graphic.view.pagesView.personalPage.myTweetsPageView.commentsPageView.CreatCommentPanel;
import ir.sharif.AP.kasra_sia.requests.AddCommentRequest;
import ir.sharif.AP.kasra_sia.requests.AddTweetRequest;
import ir.sharif.AP.kasra_sia.requests.OpenMyTweetsPageRequest;
import ir.sharif.AP.kasra_sia.requests.eventObjects.CreatCommentForm;
import ir.sharif.AP.kasra_sia.requests.eventObjects.CreatTweetForm;

public class CreatCommentPanelListener implements StringListener {
    private final CreatCommentPanel view;
    private int parentID;
    private RequestListener requestListener;

    public CreatCommentPanelListener(CreatCommentPanel creatCommentPanel,int parentID,RequestListener requestListener) {
        view = creatCommentPanel;
         this.requestListener = requestListener;
        this.parentID = parentID;
    }

    @Override
    public void stringEventOccurred(String string) {
        if (string.equals("addComment")) {
            try {
                requestListener.listen(new AddCommentRequest(
                        new CreatCommentForm(
                                view,
                                view.getCommentText(),
                                parentID
                                ),parentID));
                view.reset();
                view.reset();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
