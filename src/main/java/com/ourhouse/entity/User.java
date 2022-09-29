package com.ourhouse.entity;


/**
 * The user object
 */
public class User {
    private String householdName;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String permissions;
    private Household household;

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Gets household name.
     *
     * @return the household name
     */
    public String getHouseholdName() {
        return householdName;
    }

    /**
     * Sets household name.
     *
     * @param householdName the household name
     */
    public void setHouseholdName(String householdName) {
        this.householdName = householdName;
    }

    /**
     * Gets household.
     *
     * @return the household
     */
    public Household getHousehold() {
        return household;
    }

    /**
     * sets household.
     *
     * @param household the household object
     */
    public void setHousehold(Household household) {
        this.household = household;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets permissions.
     *
     * @return the permissions
     */
    public String getPermissions() {
        return permissions;
    }

    /**
     * Sets permissions.
     *
     * @param permissions the permissions
     */
    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    /**
     * gets the string representation of the user
     * @return string representation of user
     */
    @Override
    public String toString() {
        return "User{" +
                "householdName='" + householdName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", permissions='" + permissions + '\'' +
                '}';
    }
}
