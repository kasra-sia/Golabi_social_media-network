package ir.sharif.AP.kasra_sia.responses;

import javax.swing.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class OpenUsersListPageResponse extends Response {
    LinkedHashMap<String, String> users;
    public OpenUsersListPageResponse(LinkedHashMap<String,String> users) {
        this.users = users;

    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.openUsersListPage(users);
    }
}
