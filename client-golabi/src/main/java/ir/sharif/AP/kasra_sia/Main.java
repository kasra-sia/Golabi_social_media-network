package ir.sharif.AP.kasra_sia;

import ir.sharif.AP.kasra_sia.controller.MainController;
import ir.sharif.AP.kasra_sia.network.SocketRequestSender;
import java.io.IOException;
import java.net.Socket;

public class Main {
    public static Thread thread;
    public static void main(String[] args) {
        thread = new Thread(Main::start);
        thread.start();
    }
    private static void start(){
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", 5050);
            MainController controller = new MainController(new SocketRequestSender(socket));
            controller.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
