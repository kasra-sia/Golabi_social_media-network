package ir.sharif.AP.kasra_sia.requests;

import ir.sharif.AP.kasra_sia.responses.Response;

public class SearchUserRequest extends Request{
    String username;

    public SearchUserRequest(String username) {
        this.username = username;
    }

    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.searchUser(username);
    }
}
