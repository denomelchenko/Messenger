package Server;

public class Server {
    public static final short PORT = 8888;

    public static void main(String[] args) throws InterruptedException {
        new MessageServer(PORT).start();
    }
}
