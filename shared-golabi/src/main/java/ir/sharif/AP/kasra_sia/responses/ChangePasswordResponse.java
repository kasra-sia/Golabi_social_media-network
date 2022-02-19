package ir.sharif.AP.kasra_sia.responses;

public class ChangePasswordResponse extends Response{
    String message;

    public ChangePasswordResponse(String message) {
        this.message = message;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.visitChangePasswordResponse(message);
    }
}
