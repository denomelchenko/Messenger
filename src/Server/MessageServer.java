package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class MessageServer {
    private final short port;
    private final UserThreadDB userThreadDB;

    public MessageServer(short port) {
        this.port = port;
        this.userThreadDB = new UserThreadDB();
    }

    public void start() throws InterruptedException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            DataOutputStream dataOutputStream;
            //noinspection InfiniteLoopStatement
            while (true) {
                Socket clientSocket = serverSocket.accept();
                dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
                dataOutputStream.writeUTF("please enter your login:(without \":\")");
                DataInputStream input = new DataInputStream(clientSocket.getInputStream());
                String login = input.readUTF();

                while (userThreadDB.containsUserThread(login) || login.contains(":")) {
                    dataOutputStream.writeUTF("invalid login, try again");
                    login = input.readUTF();
                }

                ServerThread thread = new ServerThread(clientSocket, login, userThreadDB);
                userThreadDB.addUserThread(login, thread);
                thread.start();
            }
        } catch (IOException e) {
            System.err.println("Error" + Arrays.toString(e.getStackTrace()));
        } finally {
            for (ServerThread thread : userThreadDB.getAllThreads()) {
                thread.join();
            }
        }
    }
}
