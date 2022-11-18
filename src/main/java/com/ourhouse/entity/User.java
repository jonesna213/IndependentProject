package com.ourhouse.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

/**
 * The user object
 *
 * @author Navy Jones
 */
@Entity(name = "User")
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    @Column(name = "householdPrivileges")
    private String permissions;
    @ManyToOne
    private Household household;

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Instantiates a new User.
     *
     * @param firstName   the first name
     * @param lastName    the last name
     * @param email       the email
     * @param username    the username
     * @param permissions the permissions
     * @param household   the household
     */
    public User(String firstName, String lastName, String email, String username, String permissions, Household household) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.permissions = permissions;
        this.household = household;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email) && Objects.equals(username, user.username) && Objects.equals(permissions, user.permissions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, username, permissions);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", permissions='" + permissions + '\'' +
                ", household=" + household +
                '}';
    }
}
