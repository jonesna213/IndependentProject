package com.njones.persistence;

import com.njones.authentication.Passwords;
import com.njones.entities.User;
import com.njones.utilities.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.List;
import java.util.Properties;

/**
 * This class is for signing in and signing up using the database.
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
    public boolean signUpUser(String householdName, String firstName, String lastName, String email,
                              String username, String password) {
        boolean success = false;
        Passwords genPassword = new Passwords();
        Connection connection = connectToDatabase();
        String sql = "INSERT INTO households (passwordHash, salt, householdName) VALUES (?, ?, ?)";

        try (PreparedStatement pS = connection.prepareStatement(sql)) {
            List<String> passwordStuff = genPassword.getPassword(password);

            pS.setString(1, passwordStuff.get(1));
            pS.setString(2, passwordStuff.get(0));
            pS.setString(3, householdName);

            pS.executeUpdate();
            int household = getHousehold(householdName);
            if (household != 0) {
                insertNewUser(firstName, lastName, email, username, "admin", household);
                success = true;
            }

        } catch (SQLException | NoSuchAlgorithmException sqlException) {
            logger.error("Sql exception or password gen exception ", sqlException);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                logger.error("Sql exception ", sqlException);
            }
        }
        return success;
    }


    /**
     * Sign in user.
     *
     * @param username the username
     * @param password the password
     * @param householdName the household name
     * @return the user object containing the user info
     */
    public User signInUser(String username, String password, String householdName) {
        Passwords passwords = new Passwords();
        Connection connection = connectToDatabase();
        ResultSet resultSet = null;
        User user = new User();
        String sql = "SELECT firstName, lastName, email, householdPrivileges " +
                "FROM users as u INNER JOIN households as h ON u.household = h.Id " +
                "WHERE u.username = ? AND h.passwordHash = ?";

        try (PreparedStatement pS = connection.prepareStatement(sql)) {

            pS.setString(1, username);
            pS.setString(2, passwords.getPasswordHash(password, getSalt(getHousehold(householdName))));

            resultSet = pS.executeQuery();

            if (resultSet.next()) {
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setEmail(resultSet.getString("email"));
                user.setPermissions(resultSet.getString("householdPrivileges"));
                user.setUsername(username);
                user.setHouseholdName(householdName);
            } else {
                user = null;
            }

        } catch (SQLException sqlException) {
            logger.error("SQL exception or password exception: ", sqlException);
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
        return user;
    }

    /**
     * Gets the salt from database to verify password
     * @param household the household id
     * @return the string of the salt for password
     */
    private String getSalt(int household) {
        String salt = "";
        Connection connection = connectToDatabase();
        ResultSet resultSet = null;
        String sql = "SELECT salt FROM households WHERE Id = ?";

        try (PreparedStatement pS = connection.prepareStatement(sql)) {

            pS.setString(1, String.valueOf(household));

            resultSet = pS.executeQuery();

            while (resultSet.next()) {
                salt = resultSet.getString("salt");
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
        return salt;
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

    /**
     * This function inserts a new user
     *
     * @param firstName users first name
     * @param lastName users last name
     * @param email users email
     * @param username users username
     * @param householdPrivileges users household privileges
     * @param householdId users household id
     */
    private void insertNewUser(String firstName, String lastName, String email,
                               String username, String householdPrivileges, int householdId) {

        Connection connection = connectToDatabase();
        String sql = "INSERT INTO users (firstName, lastName, username, email, householdPrivileges, household) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pS = connection.prepareStatement(sql)) {

            pS.setString(1, firstName);
            pS.setString(2, lastName);
            pS.setString(3, username);
            pS.setString(4, email);
            pS.setString(5, householdPrivileges);
            pS.setString(6, String.valueOf(householdId));

            pS.executeUpdate();

        } catch (SQLException sqlException) {
            logger.error("Sql exception ", sqlException);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                logger.error("Sql exception ", sqlException);
            }
        }

    }

    /**
     * This function gets the household id from the households table so that a new user can be inserted with
     * the correct household foreign key,
     *
     * @param householdName the name of the household
     * @return the household id for the household passed in
     */
    private int getHousehold(String householdName) {
        int householdID = 0;
        Connection connection = connectToDatabase();
        ResultSet resultSet = null;
        String sql = "SELECT Id FROM households WHERE householdName = ?";

        try (PreparedStatement pS = connection.prepareStatement(sql)) {

            pS.setString(1, householdName);

            resultSet = pS.executeQuery();

            while (resultSet.next()) {
                householdID = resultSet.getInt("Id");
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
        return householdID;
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
