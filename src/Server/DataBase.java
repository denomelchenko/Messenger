package Server;

import java.util.ArrayList;
import java.util.LinkedList;

public class DataBase {
    private static LinkedList<User> userBase;

    public DataBase() {
        userBase = new LinkedList<>();
    }

    public LinkedList<User> getDataBase() {
        return userBase;
    }

    public boolean findLoginInDataBase(String login) {
        for (User user : userBase) {
            if (login.equals(user.login())) {
                return true;
            }
        }
        return false;
    }

    public User findUserInDataBase(String login) {
        for (User user : userBase) {
            if (user.login().equals(login))
                return user;
        }
        return new User(null, null);
    }

    public LinkedList<String> returnAllUsers() {
        LinkedList<String> allUsersInfo = new LinkedList<>();
        allUsersInfo.add("Count of users:" + MsgServer.getCount());
        for (User user : userBase) {
            allUsersInfo.add("login: " + user.login());
        }
        return allUsersInfo;
    }
}
