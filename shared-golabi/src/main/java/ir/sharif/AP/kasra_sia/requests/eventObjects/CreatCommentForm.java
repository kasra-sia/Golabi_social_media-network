package ir.sharif.AP.kasra_sia.requests.eventObjects;

import java.util.EventObject;

public class CreatCommentForm extends EventObject {
    String text;
    private int parentID;

    public CreatCommentForm(Object source, String text,int parentID) {
        super(source);
        this.text = text;
        this.parentID = parentID;
    }
    public String getText() {
        return text;
    }

    public int getParentID() {
        return parentID;
    }
}