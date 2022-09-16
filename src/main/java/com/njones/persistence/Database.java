package com.njones.persistence;

import com.njones.authentication.Passwords;
import com.njones.utilities.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Properties;

/**
 * The type Database.
 */
public class Database implements PropertiesLoader {
    private Properties properties;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Constructor to get the properties' setup.
     */
    public Database() {
        properties = loadProperties("/database.properties");
    }


    /**
     * This method is for when a user signs up.
     *
     * @param householdName the household name
     * @param firstName     the first name
     * @param lastName      the last name
     * @param email         the email
     * @param username      the username
     * @param password      the password
     */
    public void signUpUser(String householdName, String firstName, String lastName, String email,
                              String username, String password) {
        Passwords genPassword = new Passwords();
        Connection connection = connectToDatabase();
        String sql;
        try (PreparedStatement pS = connection.prepareStatement(sql)) {

            pS.setString(1, householdName);
            pS.setString(2, firstName);
            pS.setString(3, lastName);
            pS.setString(4, email);
            pS.setString(5, username);
            pS.setString(6, genPassword.getPassword(password));

            pS.executeUpdate();

        } catch (SQLException | NoSuchAlgorithmException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }

    }

    /**
     * Checks if the entered username is a new username (not already used).
     *
     * @param username the users username
     * @return t/f depending on if the username is new or not
     */
    public boolean newUsername(String username) {
        boolean isNew = false;
        Connection connection = connectToDatabase();
        ResultSet resultSet = null;
        String sql = "SELECT * FROM users WHERE username = ?";

        try (PreparedStatement pS = connection.prepareStatement(sql)) {

            pS.setString(1, username);

            resultSet = pS.executeQuery();

            if (!resultSet.next()) {
                isNew = true;
            }
        } catch (SQLException sqlException) {
            logger.error("SQL exception: ", sqlException);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                logger.error("SQL exception: ", sqlException);
            }
        }

        return isNew;
    }

    private void insertNewUser(String householdName, String firstName, String lastName, String email,
                               String username, String password) {


    }


    /**
     * Connects to database and returns the connection.
     *
     * @return The connection to the database
     */
    private Connection connectToDatabase() {
        Connection connection = null;

        try {
            Class.forName(properties.getProperty("driver"));

            connection =
                    DriverManager.getConnection(properties.getProperty("url"),
                            properties.getProperty("username"),
                            properties.getProperty("password"));
        } catch (ClassNotFoundException | SQLException classNotFound) {
            classNotFound.printStackTrace();
        }
        return connection;
    }
}
