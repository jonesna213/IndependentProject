package com.ourhouse.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Household.
 */
@Entity(name = "Household")
@Table(name = "household")
public class Household {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "Id")
    private int id;
    private String passwordHash;
    private String salt;
    private String householdName;

    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<User> members = new HashSet<>();

    /**
     * Instantiates a new Household.
     */
    public Household() {
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
     * Gets password hash.
     *
     * @return the password hash
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Sets password hash.
     *
     * @param passwordHash the password hash
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * Gets salt.
     *
     * @return the salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * Sets salt.
     *
     * @param salt the salt
     */
    public void setSalt(String salt) {
        this.salt = salt;
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
     * Gets members.
     *
     * @return the members
     */
    public Set<User> getMembers() {
        return members;
    }

    /**
     * Sets members.
     *
     * @param members the members
     */
    public void setMembers(Set<User> members) {
        this.members = members;
    }

    /**
     * Add member.
     *
     * @param member the member
     */
    public void addMember(User member) {
        members.add(member);
        member.setHousehold(this);
    }

    /**
     * Remove member.
     *
     * @param member the member
     */
    public void removeMember(User member) {
        members.remove(member);
        member.setHousehold(null);
    }
}
