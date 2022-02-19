package ir.sharif.AP.kasra_sia.responses;

public abstract class Response {
    public abstract void visit(ResponseVisitor responseVisitor);
}