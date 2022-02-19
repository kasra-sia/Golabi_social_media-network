package ir.sharif.AP.kasra_sia.network;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ir.sharif.AP.kasra_sia.gson.Deserializer;
import ir.sharif.AP.kasra_sia.gson.Serializer;
import ir.sharif.AP.kasra_sia.requests.LoginRequest;
import ir.sharif.AP.kasra_sia.requests.LogoutRequest;
import ir.sharif.AP.kasra_sia.requests.RegisterRequest;
import ir.sharif.AP.kasra_sia.requests.Request;
import ir.sharif.AP.kasra_sia.responses.Response;
import ir.sharif.AP.kasra_sia.responses.SignInResponse;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class SocketRequestSender implements RequestSender {
    private final Socket socket;
    private final PrintStream printStream;
    private final Scanner scanner;
    private final Gson gson;
    private  String token = null;
    public SocketRequestSender(Socket socket) throws IOException {
        this.socket = socket;
        this.scanner = new Scanner(socket.getInputStream());
        this.printStream = new PrintStream(socket.getOutputStream());
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Response.class, new Deserializer<>())
                .registerTypeAdapter(Request.class, new Serializer<>())
                .create();
    }

    @Override
    public Response send(Request request) {
        if (!(request instanceof LoginRequest || request instanceof RegisterRequest))
            request.setAuthToken(token);
        String eventString = gson.toJson(request, Request.class);
        printStream.println(eventString);
        String responseString = scanner.nextLine();
        Response response =gson.fromJson(responseString, Response.class);
        if (response instanceof SignInResponse && ((SignInResponse) response).isSuccess())
            token = ((SignInResponse) response).getMessage();
        if (request instanceof LogoutRequest)
            token = null;

        return response;
    }

    @Override
    public void close() {
        try {
            printStream.close();
            scanner.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
