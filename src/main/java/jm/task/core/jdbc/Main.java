package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    private static final UserService userService = new UserServiceImpl();
    private static User user1 = new User("Ivan", "Ivanov", (byte) 12);
    private static User user2 = new User("Petr", "Sergeev", (byte) 21);
    private static User user3 = new User("Serg", "Alexandrov", (byte) 18);
    private static User user4 = new User("Alexey", "Volkov", (byte) 22);


    public static void main(String[] args) throws SQLException {

        System.out.println("Create Table");
        userService.createUsersTable();

        System.out.println("\nAdding Users");
        userService.saveUser(user1.getName(), user1.getLastName(), (byte) user1.getAge());
        userService.saveUser(user2.getName(), user2.getLastName(), (byte) user2.getAge());
        userService.saveUser(user3.getName(), user3.getLastName(), (byte) user3.getAge());
        userService.saveUser(user4.getName(), user4.getLastName(), (byte) user4.getAge());

        System.out.println("\nGet AllUsers");
        userService.getAllUsers();

        System.out.println("\nClean Table");
        userService.cleanUsersTable();

        System.out.println("\nDrop Table");
        userService.dropUsersTable();

    }
}
