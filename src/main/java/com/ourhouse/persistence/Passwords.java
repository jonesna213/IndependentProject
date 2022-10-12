package com.ourhouse.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is for either creating or checking that a password matches.
 *
 * Found most of the code on https://howtodoinjava.com/java/java-security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
 * Reason I used already created code was because I was not taught password hashing in java yet.
 */
public class Passwords {
    private final Logger logger = LogManager.getLogger(this.getClass());

    //used for creating a new account
    public List<String> getPassword(String password) throws NoSuchAlgorithmException {
        List<String> passwordHashAndSalt = new ArrayList<String>();
        String salt = getSalt();
        passwordHashAndSalt.add(salt);
        passwordHashAndSalt.add(get_SHA_512_SecurePassword(password, salt));

        return passwordHashAndSalt;
    }

    //Used for signing in
    public String getPasswordHash(String password, String salt) {
        return get_SHA_512_SecurePassword(password, salt);
    }

    private String get_SHA_512_SecurePassword(String passwordToHash,
                                                     String salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16)
                        .substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            logger.error("Error generating password: ", e);
        }
        return generatedPassword;
    }

    // Add salt
    private static String getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt.toString();
    }
}
