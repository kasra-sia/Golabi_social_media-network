package ir.sharif.AP.kasra_sia.requests;

import ir.sharif.AP.kasra_sia.responses.Response;

public class UserActionRequest extends Request{
    String username;
    String command;

    public UserActionRequest(String username, String command) {
        this.username = username;
        this.command = command;
    }

    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.userAction(username,command);
    }
}
