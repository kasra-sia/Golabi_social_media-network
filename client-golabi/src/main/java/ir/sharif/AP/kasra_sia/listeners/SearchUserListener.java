package ir.sharif.AP.kasra_sia.listeners;

public class SearchUserListener implements StringListener {
    RequestListener requestListener;
    public SearchUserListener(RequestListener requestListener) {
        this.requestListener = requestListener;
    }
    //    @Override
//    public <T> void eventOccurred(T event) throws Exception {
//        foundUser = controller. searchUser((SearchUserEvent) event);
//        if (((SearchUserEvent) event).getSource() instanceof ExplorerSearchView)
//        ((ExplorerSearchView) ((SearchUserEvent) event).getSource()).setPage(foundUser.getUsername(), controller.getContext().Users.getPhoto(foundUser.getID()));
//    }

    @Override
    public void stringEventOccurred(String string) {
        if (string.equals("openProfilePage")) {

//            userView.addStringListener(userViewListener);

        }
    }
//    private void updateProfilePage(golabi.listener.UserViewListener userViewListener){
//        userViewListener.stringEventOccurred("openPage");
    }

//}
