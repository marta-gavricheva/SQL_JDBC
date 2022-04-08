package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String query = """
                CREATE TABLE user
                (id BIGINT PRIMARY KEY AUTO_INCREMENT,
                name VARCHAR(255),
                lastname VARCHAR(255),
                age INT);
                """;

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void dropUsersTable() {
        String drop = "DROP TABLE user";
                try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(drop);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void saveUser(String name, String lastName, byte age) {
        String insert = """
                INSERT INTO user
                (name, lastname, age)
                VALUES
                (?,?,?);
                """;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void removeUserById(long id) {
        String delete = "DELETE FROM user WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String select = "SELECT * FROM  USER;";
        List<User> userList = new ArrayList<>();

        try {ResultSet resultSet = connection.createStatement().executeQuery(select);
                   while (resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("lastname"),
                        resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                userList.add(user);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return userList;
    }

    public void cleanUsersTable() {
        String trunc = "TRUNCATE TABLE user";

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(trunc);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

