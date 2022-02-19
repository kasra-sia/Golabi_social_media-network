package ir.sharif.AP.kasra_sia.responses;

import ir.sharif.AP.kasra_sia.model.RequestStatus;

import java.util.LinkedHashMap;

public class OpenSentRequestPageResponse extends Response {
    LinkedHashMap<String, RequestStatus> sentRequests;

    public OpenSentRequestPageResponse(LinkedHashMap<String, RequestStatus> sentRequests) {
        this.sentRequests = sentRequests;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.openSentRequestPage(sentRequests);
    }
}
