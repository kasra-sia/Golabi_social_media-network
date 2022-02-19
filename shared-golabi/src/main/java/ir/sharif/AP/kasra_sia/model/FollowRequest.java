package ir.sharif.AP.kasra_sia.model;

public class FollowRequest extends Model{
    private int senderID;
    private int receiverID;
    private RequestStatus requestStatus;
    public FollowRequest(int senderID, int receiverID) {
        this.senderID = senderID;
        this.receiverID = receiverID;
        requestStatus = RequestStatus.SENT;
    }

    public FollowRequest() {
    }

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

}

