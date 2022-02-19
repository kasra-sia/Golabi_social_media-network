package ir.sharif.AP.kasra_sia.controller.network;

import ir.sharif.AP.kasra_sia.requests.*;
import ir.sharif.AP.kasra_sia.responses.*;
public interface ResponseSender {
    Request getRequest();

    void sendResponse(Response response);

    void expireAuthToken();

    void close();
}
