package ir.sharif.AP.kasra_sia.requests;

import ir.sharif.AP.kasra_sia.responses.Response;

public class OpenUserPageRequest extends Request{
    String username;

    public OpenUserPageRequest(String username) {
        this.username = username;
    }

    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.openUserPage(username);
    }
}
