package ir.sharif.AP.kasra_sia.responses;

import ir.sharif.AP.kasra_sia.demoModel.DemoComment;

import java.util.LinkedList;

public class OpenCommentsPageResponse extends Response{
    LinkedList<DemoComment> comments;
    int parenID;

    public OpenCommentsPageResponse(LinkedList<DemoComment> comments, int parenID) {
        this.comments = comments;
        this.parenID = parenID;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.openCommentsPage(comments,parenID);
    }
}
