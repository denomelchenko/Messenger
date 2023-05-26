package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.regex.Pattern;

public class ServerThread extends Thread {
    private static final Pattern messagePattern = Pattern.compile("^.*::.*$");
    private static final String MESSAGE_USERS = "users";
    private static final String MESSAGE_EXIT = "exit";
    private final Socket clientSocket;
    private final String userLogin;
    private final UserThreadDB db;


    public ServerThread(Socket clientSocket, String login, UserThreadDB db) {
        this.clientSocket = clientSocket;
        this.userLogin = login;
        this.db = db;
    }

    @Override
    public void run() {
        try (DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
             DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream())
        ) {
            dataOutputStream.writeUTF("Okay, connected. Now, print your message(recipient_login::message)");
            String fullMessage;

            do {
                fullMessage = dataInputStream.readUTF();
                if (fullMessage.equals(MESSAGE_USERS)) {
                    // Print all existing users logins to the client
                    List<String> allLogins = db.getAllUsers();
                    dataOutputStream.writeUTF("Count of online users: " + allLogins.size());
                    dataOutputStream.writeUTF("Online users: " + String.join(", ", allLogins));
                } else {
                    if (messagePattern.matcher(fullMessage).find()) {
                        String[] messageParts = fullMessage.split("::");
                        String recipientLogin = messageParts[0], message = messageParts[1];

                        if (db.containsUserThread(recipientLogin)) {
                            sendToMessageToUser(recipientLogin, message);
                        } else {
                            dataOutputStream.writeUTF("login is invalid or message was not recipient_login::message");
                        }
                    }
                }
            }
            while (!fullMessage.equals(MESSAGE_EXIT));

            clientSocket.close();
        } catch (IOException e) {
            System.err.println("Disconnected with error");
            db.removeUserThread(userLogin);
        }
    }

    private void sendToMessageToUser(String recipientLogin, String message) throws IOException {
        new DataOutputStream(db.getUserThread(recipientLogin).getClientSocket().getOutputStream()).writeUTF(message);

    }

    public Socket getClientSocket() {
        return clientSocket;
    }
}
