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
 * @author Navy Jones
 */
public class Passwords {
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Used for creating a new account
     *
     * @param password the string of the password
     * @return a list containing the password hash and salt
     * @throws NoSuchAlgorithmException error from generating password
     */
    public List<String> getPassword(String password) throws NoSuchAlgorithmException {
        List<String> passwordHashAndSalt = new ArrayList<String>();
        String salt = getSalt();
        passwordHashAndSalt.add(salt);
        passwordHashAndSalt.add(get_SHA_512_SecurePassword(password, salt));

        return passwordHashAndSalt;
    }

    /**
     * Used for signing in
     *
     * @param password the string of the password
     * @param salt the passwords salt
     * @return the hash of the password and salt
     */
    public String getPasswordHash(String password, String salt) {
        return get_SHA_512_SecurePassword(password, salt);
    }

    /**
     * Generates the password hash.
     * Found on - https://howtodoinjava.com/java/java-security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
     *
     * @param passwordToHash the password to hash
     * @param salt the password salt
     * @return the password hash
     */
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

    /**
     * Creates salt
     *
     * @return string containing salt
     * @throws NoSuchAlgorithmException error with secure random
     */
    private static String getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt.toString();
    }
}
