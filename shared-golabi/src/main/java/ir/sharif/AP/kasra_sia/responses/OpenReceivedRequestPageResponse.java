package ir.sharif.AP.kasra_sia.responses;

import java.util.LinkedList;

public class OpenReceivedRequestPageResponse extends Response {
    LinkedList<String> requests;

    public OpenReceivedRequestPageResponse(LinkedList<String> requests) {
        this.requests = requests;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.openReceivedRequestPage(requests);
    }
}
