package ir.sharif.AP.kasra_sia.listeners;

import ir.sharif.AP.kasra_sia.demoModel.DemoComment;
import ir.sharif.AP.kasra_sia.requests.LikeCommentRequest;
import ir.sharif.AP.kasra_sia.requests.LikeTweetRequest;

public class CommentListener implements StringListener {
    private DemoComment comment;
    private RequestListener requestListener;
    private int parentID;

    public CommentListener(int parentID,DemoComment comment,RequestListener requestListener) {
        this.parentID = parentID;
        this.comment = comment;
        this.requestListener = requestListener;
    }

    @Override
    public void stringEventOccurred(String string) {
        if (string.equals("like")) {
            requestListener.listen(new LikeCommentRequest(parentID,comment.getID(),true));
        }
        if (string.equals("unLike")) {
            requestListener.listen(new LikeCommentRequest(parentID,comment.getID(),false));        }
        if (string.equals("comment")) {
//            CommentsPageView commentsPageView =
//                    new CommentsPageView();
//            CommentsPageListener commentsPageListener = new CommentsPageListener(comment, commentsPageView);
//            commentsPageListener.stringEventOccurred("openPage");
//            ((CommentsPageView) view).getContainer().add(commentsPageView);
//            ((CommentsPageView) view).getCardLayout().next(((CommentsPageView) view).getContainer());
//            commentsPageView.addStringListener(commentsPageListener);
        }
    }
}

