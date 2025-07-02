package org.example.dao;

import org.example.models.User;
import org.example.db.DB;
import org.example.exceptions.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DAO<User> {

    @Override
    public User find(int id) {

        Connection conn;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        User user = new User();

        try {
            conn = DB.getConn();

            statement = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
            }

        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        finally {
            DB.closeConn();
            DB.closeStatement(statement);
            DB.closeResultSet(resultSet);
        }

        return user;
    }

    @Override
    public List<User> findAll() {

        Connection conn;
        Statement statement = null;
        ResultSet resultSet = null;

        List<User> users = new ArrayList<>();

        try {
            conn = DB.getConn();

            statement = conn.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));

                users.add(user);
            }

        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        finally {
            DB.closeConn();
            DB.closeStatement(statement);
            DB.closeResultSet(resultSet);
        }

        return users;
    }

    public User find(String email) {

        Connection conn;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        User user = new User();

        try {
            conn = DB.getConn();

            statement = conn.prepareStatement("SELECT * FROM users WHERE email = ?");
            statement.setString(1, email);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
            }

        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        finally {
            DB.closeConn();
            DB.closeStatement(statement);
            DB.closeResultSet(resultSet);
        }

        return user;
    }

    @Override
    public void insert(User user) {
        Connection conn;
        PreparedStatement statement = null;

        try {
            conn = DB.getConn();

            statement = conn.prepareStatement(
                    "INSERT INTO users (name, email, password) VALUES (?, ?, ?)"
            );

            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());

            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        finally {
            DB.closeConn();
            DB.closeStatement(statement);
        }
    }

    @Override
    public void delete(User user) {
        Connection conn;
        PreparedStatement statement = null;

        try {
            conn = DB.getConn();

            statement = conn.prepareStatement(
                    "DELETE FROM users WHERE id = ?"
            );

            statement.setInt(1, user.getId());

            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        finally {
            DB.closeConn();
            DB.closeStatement(statement);
        }
    }

    @Override
    public void update(User user) {
        Connection conn;
        PreparedStatement statement = null;

        try {
            conn = DB.getConn();

            statement = conn.prepareStatement(
                    "UPDATE users SET name = ?, email = ?, password = ? WHERE id = ?"
            );
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getId());

            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        finally {
            DB.closeConn();
            DB.closeStatement(statement);
        }
    }

}
