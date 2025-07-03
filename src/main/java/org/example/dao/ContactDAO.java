package org.example.dao;

import org.example.db.DB;
import org.example.exceptions.DBException;
import org.example.models.Contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO implements DAO<Contact> {

    @Override
    public Contact find(int id) {

        Connection conn;
        PreparedStatement statement = null;
        ResultSet result = null;

        Contact contact;

        try {
            conn = DB.getConn();

            statement = conn.prepareStatement("SELECT * FROM contacts WHERE id = ?");
            statement.setInt(1, id);
            result = statement.executeQuery();

            contact = new Contact();

            while (result.next()) {
                contact.setId(result.getInt("id"));
                contact.setName(result.getString("name"));
                contact.setEmail(result.getString("email"));
                contact.setNumber(result.getString("number"));
                contact.setUserId(result.getInt("userId"));
            }

            return contact;
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        finally {
            DB.closeConn();
            DB.closeStatement(statement);
            DB.closeResultSet(result);
        }
    }

    @Override
    public List<Contact> findAll() {

        Connection conn;
        Statement statement = null;
        ResultSet result = null;

        List<Contact> contacts = new ArrayList<>();

        try {
            conn = DB.getConn();
            statement = conn.createStatement();
            result = statement.executeQuery("SELECT * FROM contacts");

            return getContacts(result, contacts);
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        finally {
            DB.closeConn();
            DB.closeStatement(statement);
            DB.closeResultSet(result);
        }
    }

    public List<Contact> findAll(int userId) {
        Connection conn;
        PreparedStatement statement = null;
        ResultSet result = null;

        List<Contact> contacts = new ArrayList<>();

        try {
            conn = DB.getConn();

            statement = conn.prepareStatement("SELECT * FROM contacts WHERE userId = ?");
            statement.setInt(1, userId);
            result = statement.executeQuery();

            return getContacts(result, contacts);
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        finally {
            DB.closeConn();
            DB.closeStatement(statement);
            DB.closeResultSet(result);
        }
    }

    @Override
    public void insert(Contact contact) {

        Connection conn;
        PreparedStatement statement = null;

        try {
            conn = DB.getConn();

            statement = conn.prepareStatement(
                        "INSERT INTO contacts " +
                            "(name, email, number, userId)" +
                            "VALUES (?, ?, ?, ?);"
            );

            statement.setString(1, contact.getName());
            statement.setString(2, contact.getEmail());
            statement.setString(3, contact.getNumber());
            statement.setInt(4, contact.getUserId());

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
    public void delete(Contact contact) {

        Connection conn;
        PreparedStatement statement = null;

        try {
            conn = DB.getConn();
            statement = conn.prepareStatement("DELETE FROM contacts WHERE id = ?");

            statement.setInt(1, contact.getId());

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
    public void update(Contact contact) {

        Connection conn;
        PreparedStatement statement = null;

        try {
            conn = DB.getConn();

            statement = conn.prepareStatement(
                    "UPDATE contacts SET name = ?, email = ?, number = ? WHERE id = ?"
            );

            statement.setString(1, contact.getName());
            statement.setString(2, contact.getEmail());
            statement.setString(3, contact.getNumber());
            statement.setInt(4, contact.getId());

        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        finally {
            DB.closeConn();
            DB.closeStatement(statement);
        }
    }

    private List<Contact> getContacts(ResultSet result, List<Contact> contacts) throws SQLException {
        while (result.next()) {

            Contact contact = new Contact();
            contact.setId(result.getInt("id"));
            contact.setName(result.getString("name"));
            contact.setEmail(result.getString("email"));
            contact.setNumber(result.getString("number"));
            contact.setUserId(result.getInt("userId"));

            contacts.add(contact);
        }
        return contacts;
    }

}
