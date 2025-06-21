package org.papelariapachecotorres.auth;

import java.util.Arrays;
import java.util.List;

public class MockUserRepository {
    private static final List<MockUser> users = Arrays.asList(
            new MockUser("admin", "admin123"),
            new MockUser("user", "user123")
    );

    public static MockUser findByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
} 