package ir.sharif.AP.kasra_sia.requests;

import ir.sharif.AP.kasra_sia.requests.eventObjects.FollowRequestEvent;
import ir.sharif.AP.kasra_sia.responses.Response;

public class ManageFollowReqRequest extends Request{
    FollowRequestEvent followRequestEvent;

    public ManageFollowReqRequest(FollowRequestEvent followRequestEvent) {
        this.followRequestEvent = followRequestEvent;
    }

    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.mangeFollowRequest(followRequestEvent);
    }
}
