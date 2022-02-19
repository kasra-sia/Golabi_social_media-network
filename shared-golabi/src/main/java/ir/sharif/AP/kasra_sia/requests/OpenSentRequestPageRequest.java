package ir.sharif.AP.kasra_sia.requests;

import ir.sharif.AP.kasra_sia.responses.Response;

public class OpenSentRequestPageRequest extends Request{
    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.openSentRequestPage();
    }
}
