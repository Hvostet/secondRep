package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;


public class Main {
    private static final UserService user = new UserServiceImpl();

    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        Util util = new Util();
        util.getSessionFactory();
        user.createUsersTable();
        user.dropUsersTable();
        user.saveUser("dsf", "sdf", (byte) 98);

    }
}

