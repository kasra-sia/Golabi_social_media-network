package ir.sharif.AP.kasra_sia.responses;

public class SignInResponse extends Response{
    private boolean success;
    private String message;

    public SignInResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.visitSignInResult(success,message);
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
