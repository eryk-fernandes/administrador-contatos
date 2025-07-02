package org.example.dao;

import org.example.models.Contact;

import java.util.List;

public class ContactDAO implements DAO<Contact> {


    @Override
    public Contact find(int id) {
        return null;
    }

    @Override
    public List<Contact> findAll() {
        return List.of();
    }

    @Override
    public void insert(Contact contact) {

    }

    @Override
    public void delete(Contact contact) {

    }

    @Override
    public void update(Contact contact) {

    }
}
