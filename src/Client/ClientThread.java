package Client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientThread extends Thread {
    private final Socket clientSocket;
    private static final String EXIT_MESSAGE = "exit";

    public ClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
            String answer;
            do {
                answer = dataInputStream.readUTF();
                System.out.println(answer);
            } while (!answer.equals(EXIT_MESSAGE));
        } catch (IOException ignored) {
        }
    }
}