package ir.sharif.AP.kasra_sia.requests.eventObjects;

import java.util.EventObject;

public class CreatTweetForm extends EventObject {
    private String text;
    private String encodedImage;

    public CreatTweetForm(Object source, String text, String encodedImage) {
        super(source);
        this.text = text;
        this.encodedImage = encodedImage;
    }
    public String getText(){
        return text;
    }

    public String getEncodedImage() {
        return encodedImage;
    }

    public void setText(String text) {
        this.text = text;
    }
}
