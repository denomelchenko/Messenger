package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.regex.Pattern;

public class ServerThread extends Thread {
    private final Socket clientSocket;
    private final String login;


    public ServerThread(Socket clientSocket, String login) {
        this.login = login;
        this.clientSocket = clientSocket;
        MsgServer.getCurrentDataBase().getDataBase().add(new User(login, this));
    }

    private void removeByLogin() {
        List<User> userBase = MsgServer.getCurrentDataBase().getDataBase();
        for (int i = 0; i < userBase.size(); ++i) {
            if (userBase.get(i).login().equals(login)) {
                userBase.remove(i);
            }
        }
    }

    @Override
    public void run() {
        try {
            DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            dataOutputStream.writeUTF("Okay, connected. Now, print your message(user_login::message)");
            Pattern pattern = Pattern.compile("^.*::.*$");
            String fullMessage;
            DataBase dataBase = MsgServer.getCurrentDataBase();
            do {
                fullMessage = dataInputStream.readUTF();
                if (fullMessage.equals("users")) {
                    MsgServer.printAllUsersToClient(dataOutputStream);
                } else {
                    String user_login = findUserLogin(fullMessage);
                    if (pattern.matcher(fullMessage).find() && dataBase.findLoginInDataBase(user_login)) {
                        MsgServer.printMessageToUser(findUserMessage(fullMessage), dataOutputStream);
                    } else {
                        dataOutputStream.writeUTF("login is invalid or message was not user_login::message");
                    }
                }
            }
            while (!fullMessage.equals("exit"));
            dataInputStream.close();
            dataOutputStream.close();
            clientSocket.close();
        } catch (IOException e) {
            removeByLogin();
            --MsgServer.count;
            System.out.println("Disconnected with error");
        }
    }

    private String findUserLogin(String fullMessage) {
        return fullMessage.split("::")[0];
    }

    private String findUserMessage(String fullMessage) {
        return fullMessage.split("::")[1];
    }

    public Socket getClientSocket() {
        return clientSocket;
    }
}
