package Server;

public class Server {
    public static final short PORT = 8080;

    public static void main(String[] args) {
        new MsgServer(PORT).start();
    }
}
