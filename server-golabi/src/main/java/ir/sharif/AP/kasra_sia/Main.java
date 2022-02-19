package ir.sharif.AP.kasra_sia;

import ir.sharif.AP.kasra_sia.controller.Controller;
import ir.sharif.AP.kasra_sia.controller.network.SocketManager;

public class Main {
    public static void main(String[] args) {
        Controller.logger.info("sever is running");
        SocketManager socketManager = new SocketManager();
        socketManager.run();
    }
}
