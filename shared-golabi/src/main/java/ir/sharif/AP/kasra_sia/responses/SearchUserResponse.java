package ir.sharif.AP.kasra_sia.responses;

public class SearchUserResponse extends Response{
    String username;
    private String encodedImage;

    public SearchUserResponse(String username,String encodedImage) {
        this.username = username;
        this.encodedImage = encodedImage;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.foundUser(username,encodedImage);
    }
}
