package Client;

import Server.Server;

public class Client {
    public static void main(String[] args) {
        new MsgClient("localhost", Server.PORT).start();
    }
}