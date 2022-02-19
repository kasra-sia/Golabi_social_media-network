package ir.sharif.AP.kasra_sia.requests;

import ir.sharif.AP.kasra_sia.responses.Response;

public class OpenUsersListPageRequest extends Request {
    String command;

    public OpenUsersListPageRequest(String command) {
        this.command = command;
    }

    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.openUsersList(command);
    }
}
