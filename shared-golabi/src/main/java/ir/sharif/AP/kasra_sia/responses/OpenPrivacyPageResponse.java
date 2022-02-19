package ir.sharif.AP.kasra_sia.responses;

import ir.sharif.AP.kasra_sia.model.LastSeen;

public class OpenPrivacyPageResponse extends Response{
    private boolean isPrivate;
    private LastSeen lastSeen;

    public OpenPrivacyPageResponse(boolean isPrivate, LastSeen lastSeen) {
        this.isPrivate = isPrivate;
        this.lastSeen = lastSeen;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.openPrivacyPage(isPrivate,lastSeen);
    }
}
