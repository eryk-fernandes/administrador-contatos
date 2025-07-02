package org.example.models;

public class Contact {

    private Integer id;
    private String name;
    private String email;
    private String number;
    private Integer userId;

    public Contact() {}

    public Contact(Integer id, String name, String email, String number, Integer userId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.number = number;
        this.userId = userId;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String numero) {
        this.number = numero;
    }

    public Integer getUserId() { return userId; }

    public void setUserId(Integer userId) { this.userId = userId; }
}
