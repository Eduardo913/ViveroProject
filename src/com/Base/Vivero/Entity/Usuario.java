package com.Base.Vivero.Entity;

public class Usuario {

    private int id;
    private String password;
    private String userName;

    public Usuario() {
    }

    public Usuario(String password, String userName) {
        this.password = password;
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}