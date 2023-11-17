package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

  //tests the userAuthorization() method with two usernames added manually for testing purposes only
    @Test
    void userAuthorization() {
        assertAll("User Authorization",
                () -> assertTrue(Main.userAuthorization("johndoe", "validPassword"), "Valid user and password should succeed"),
               () -> assertTrue(Main.userAuthorization("ibadmomin", "validPassword"), "Valid user and password should succeed"),
                () -> assertFalse(Main.userAuthorization("invalidUser", "validPassword"), "Invalid user should fail"),
                () -> assertFalse(Main.userAuthorization("validUser", "invalidPassword"), "Invalid password should fail"),
                () -> assertFalse(Main.userAuthorization("invalidUser", "invalidPassword"), "Invalid user and password should fail")
        );
    }
}
