package ir.sharif.AP.kasra_sia.model.chat;


import ir.sharif.AP.kasra_sia.model.Model;

public abstract class ChatAbstractClass extends Model {
    protected boolean isGroup;

    public boolean isGroup() {
        return isGroup;
    }

    public void setGroup(boolean group) {
        isGroup = group;
    }
}
