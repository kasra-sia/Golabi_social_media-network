package ir.sharif.AP.kasra_sia.responses;

public class UpdateMyInfoResponse extends Response{
    boolean success;
    String message;

    public UpdateMyInfoResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.visitMyInfoEditResult(success,message);
    }
}
