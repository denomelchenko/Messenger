package Client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MsgClient {
    private final String HOST;
    private final int PORT;

    public MsgClient(String localhost, int PORT) {
        this.PORT = PORT;
        HOST = localhost;
    }

    public void start() {
        try (Socket clientSocket = new Socket(HOST, PORT)) {
            DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            String message;

            new ClientThread(clientSocket).start();
            do {
                message = scanner.nextLine();
                dataOutputStream.writeUTF(message);
            } while (!message.equals("exit"));
            scanner.close();
            dataOutputStream.close();
        } catch (IOException e) {
            System.out.println("Error:" + e);
        }
    }
}
