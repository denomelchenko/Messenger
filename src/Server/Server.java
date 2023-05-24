package Server;

public class Server {
    public static final short PORT = 999;

    public static void main(String[] args) {
        new MsgServer(PORT).start();
    }
}
