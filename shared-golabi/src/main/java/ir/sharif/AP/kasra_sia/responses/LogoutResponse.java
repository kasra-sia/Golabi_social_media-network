package ir.sharif.AP.kasra_sia.responses;

public class LogoutResponse extends Response{
    boolean result;

    public LogoutResponse(boolean result) {
        this.result = result;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.visitLogoutResult(result);
    }
}
