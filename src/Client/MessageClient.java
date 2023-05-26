package Client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MessageClient {
    private final String host;
    private final int port;

    public MessageClient(String host, int port) {
        this.port = port;
        this.host = host;
    }

    public void start() {
        try (Socket clientSocket = new Socket(host, port);
             DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
             Scanner scanner = new Scanner(System.in)
        ) {
            String message;
            new ClientThread(clientSocket).start();
            do {
                message = scanner.nextLine();
                dataOutputStream.writeUTF(message);
            } while (!message.equals("exit"));
        } catch (IOException e) {
            System.out.println("Error:" + e);
        }
    }
}
