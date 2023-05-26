package Client;

import Server.Server;

public class Client {
    public static void main(String[] args) {
        new MessageClient("localhost", Server.PORT).start();
    }
}