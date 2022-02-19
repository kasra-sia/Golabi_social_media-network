package ir.sharif.AP.kasra_sia.network;

import ir.sharif.AP.kasra_sia.requests.Request;
import ir.sharif.AP.kasra_sia.responses.Response;

public interface RequestSender {
    Response send(Request event);

    void close();
}
