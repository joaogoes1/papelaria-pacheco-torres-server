package org.papelariapachecotorres.auth;

public class MockUser {
    private final String username;
    private final String password;

    public MockUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
} 