package ir.sharif.AP.kasra_sia.requests;

import ir.sharif.AP.kasra_sia.requests.eventObjects.LoginForm;
import ir.sharif.AP.kasra_sia.responses.Response;

public class LoginRequest extends Request {
    private LoginForm loginForm;

    public LoginRequest(LoginForm loginForm) {
        this.loginForm = loginForm;
    }

    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.login(loginForm);
    }
}
