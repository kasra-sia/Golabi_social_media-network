package ir.sharif.AP.kasra_sia.controller.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ir.sharif.AP.kasra_sia.gson.Deserializer;
import ir.sharif.AP.kasra_sia.gson.Serializer;
import ir.sharif.AP.kasra_sia.requests.*;
import ir.sharif.AP.kasra_sia.responses.*;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;

public class SocketResponseSender implements ResponseSender {
    private final Socket socket;
    private final PrintStream printStream;
    private final Scanner scanner;
    private final Gson gson;
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();
    private String token = null;

    public SocketResponseSender(Socket socket) throws IOException {
        this.socket = socket;
        this.scanner = new Scanner(socket.getInputStream());
        this.printStream = new PrintStream(socket.getOutputStream());

        this.gson = new GsonBuilder()
                .registerTypeAdapter(Request.class, new Deserializer<>())
                .registerTypeAdapter(Response.class, new Serializer<>())
                .create();
    }

    @Override
    public Request getRequest() {
        String eventString = scanner.nextLine();
        Request event = gson.fromJson(eventString, Request.class);
        if (!(event instanceof LoginRequest || event instanceof RegisterRequest) && !event.getAuthToken().equals(token))
                throw new Error("security alert : wrong AuthToken");
        return event;
    }

    public String generateNewToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    @Override
    public void sendResponse(Response response) {
        if (((response instanceof SignInResponse ) && ((SignInResponse) response).isSuccess())) {
                token = generateNewToken();
                ((SignInResponse) response).setMessage(token);
        }
        printStream.println(gson.toJson(response, Response.class));
    }

    @Override
    public void expireAuthToken() {
        token = null;
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
