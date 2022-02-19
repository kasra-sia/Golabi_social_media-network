package ir.sharif.AP.kasra_sia.responses;

import ir.sharif.AP.kasra_sia.model.Notification;

import java.util.LinkedList;

public class OpenSystemNotificationsPageResponse extends Response{
    LinkedList<Notification> notifications;

    public OpenSystemNotificationsPageResponse(LinkedList<Notification> notifications) {
        this.notifications = notifications;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.openSystemNotificationPage(notifications);
    }
}
