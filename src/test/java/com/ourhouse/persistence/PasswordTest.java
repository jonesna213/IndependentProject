package com.ourhouse.persistence;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordTest {
    Passwords passwords = new Passwords();

    @Test
    void successfulPasswordGenerator() {
        String passwordString = "test";
        String expectedPasswordHash = "c329a279987b100b58b12ac24a8a3207a1204544c79c119993147f31bcefb9aa070426cc06620b7a38949f9bd27668fd8da56dba4cd563c0b6a41df1099f0ebc";
        String passwordSalt = "[B@79a3aa3f";

        String passwordHash = passwords.getPasswordHash(passwordString, passwordSalt);
        assertEquals(expectedPasswordHash, passwordHash);
    }
}
