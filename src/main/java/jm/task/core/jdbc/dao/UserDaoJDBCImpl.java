package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String query_string = ("" +
                "CREATE TABLE IF NOT EXISTS Users (" +
                "id int auto_increment primary key," +
                "name varchar(255) not null," +
                "lastName varchar(255) not null," +
                "age int(11) not null);"
        );
        try (PreparedStatement ps = connection.prepareStatement(query_string)) {
            ps.executeUpdate();
            System.out.println("Таблица Users создана!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String query_string = "DROP TABLE IF EXISTS Users;";
        try (PreparedStatement ps = connection.prepareStatement(query_string)) {
            ps.executeUpdate();
            System.out.println("Таблица Users удалена!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String query_string = "INSERT INTO Users (name, lastName, age) values (?, ?, ?);";
        try (PreparedStatement ps = connection.prepareStatement(query_string)) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String query_string = "DELETE FROM Users WHERE id=?;";
        try (PreparedStatement ps = connection.prepareStatement(query_string)) {
            ps.setLong(1, id);
            ps.executeUpdate();
            System.out.println("User id=" + id + " удален");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new LinkedList();
        String query_string = "SELECT * FROM Users;";
        try (PreparedStatement ps = connection.prepareStatement(query_string)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setAge(resultSet.getByte("age"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                users.add(user);
            }
            Arrays.stream(users.toArray()).forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void cleanUsersTable() {
        String query_string = "TRUNCATE Users;";
        try (PreparedStatement ps = connection.prepareStatement(query_string)) {
            ps.executeUpdate();
            System.out.println("Таблица Users очищена!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Таблицы не существует!");
        }
    }
}
