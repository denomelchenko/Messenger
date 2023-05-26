package Server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserThreadDB {
    private static Map<String, ServerThread> userDB;

    public UserThreadDB() {
        userDB = new HashMap<>();
    }

    public void addUserThread(String login, ServerThread thread) {
        userDB.put(login, thread);
    }

    public ServerThread getUserThread(String login) {
        return userDB.get(login);
    }

    public ServerThread removeUserThread(String login) {
        return userDB.remove(login);
    }

    public boolean containsUserThread(String login) {
        return userDB.containsKey(login);
    }

    public List<String> getAllUsers() {
        return new ArrayList<>(userDB.keySet());
    }

    public Iterable<ServerThread> getAllThreads() {
        return userDB.values();
    }
}
