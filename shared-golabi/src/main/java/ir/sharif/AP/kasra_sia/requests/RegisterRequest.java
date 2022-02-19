package ir.sharif.AP.kasra_sia.requests;

import ir.sharif.AP.kasra_sia.requests.eventObjects.RegistrationForm;
import ir.sharif.AP.kasra_sia.responses.Response;

public class RegisterRequest extends Request{
    private RegistrationForm registrationForm;
    public RegisterRequest(RegistrationForm registrationForm) {
        this.registrationForm = registrationForm;
    }

    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.register(registrationForm);
    }
}
