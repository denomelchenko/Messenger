package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class MsgServer {
    private final short port;
    protected static int count;
    private static final DataBase dataBase = new DataBase();

    public MsgServer(short port) {
        this.port = port;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            DataOutputStream dataOutputStream;
            //noinspection InfiniteLoopStatement
            while (true) {
                Socket clientSocket = serverSocket.accept();
                dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
                dataOutputStream.writeUTF("please enter your login:(without \"::\")");
                String login = new DataInputStream(clientSocket.getInputStream()).readUTF();
                if (dataBase.findLoginInDataBase(login) || login.contains("::")) {
                    dataOutputStream.writeUTF("you disconnected(invalid login, try again)");
                } else {
                    new ServerThread(clientSocket, login).start();
                    ++count;
                    System.out.println("Connected, count of connections " + count);
                }
            }
        } catch (IOException e) {
            System.err.println("Error" + Arrays.toString(e.getStackTrace()));
        }
    }

    protected static void printMessageToUser(String message, DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(message);
    }

    protected static void printAllUsersToClient(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(String.valueOf(dataBase.returnAllUsers()));
    }

    public static DataBase getCurrentDataBase() {
        return dataBase;
    }

    public static int getCount() {
        return count;
    }
}
