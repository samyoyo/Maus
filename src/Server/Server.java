package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import Logger.*;

public class Server implements Runnable {
    private static Socket client;

    public static Socket getClient() {
        return client;
    }

    private void startServer() {
        startServer(ServerSettings.getPORT());
    }

    private void startServer(int port) {
        while (true) {
            try {
                ServerSocket server = new ServerSocket(port);
                client = server.accept();
                Runnable clientHandler = new ClientHandler(client);
                new Thread(clientHandler).start();
            } catch (IOException ignored) {
            }
        }
    }

    @Override
    public void run() {
        startServer();
    }
}