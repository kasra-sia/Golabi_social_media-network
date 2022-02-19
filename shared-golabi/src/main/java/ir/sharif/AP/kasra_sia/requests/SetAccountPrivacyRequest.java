package ir.sharif.AP.kasra_sia.requests;

import ir.sharif.AP.kasra_sia.responses.Response;

public class SetAccountPrivacyRequest extends Request{
    boolean isPrivate;

    public SetAccountPrivacyRequest(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.setPrivacy(isPrivate);
    }
}
