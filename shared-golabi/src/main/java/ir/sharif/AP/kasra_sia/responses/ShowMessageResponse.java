package ir.sharif.AP.kasra_sia.responses;

public class ShowMessageResponse extends Response{
    String message;

    public ShowMessageResponse(String message) {
        this.message = message;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.showMessage(message);
    }
}
