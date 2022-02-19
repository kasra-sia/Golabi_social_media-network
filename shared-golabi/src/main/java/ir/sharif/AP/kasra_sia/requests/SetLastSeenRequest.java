package ir.sharif.AP.kasra_sia.requests;

import ir.sharif.AP.kasra_sia.model.LastSeen;
import ir.sharif.AP.kasra_sia.responses.Response;

public class SetLastSeenRequest extends Request{
    private LastSeen lastSeen;

    public SetLastSeenRequest(LastSeen lastSeen) {
        this.lastSeen = lastSeen;
    }

    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.setLastSeenType(lastSeen);
    }
}
