package com.ourhouse.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The Household object.
 *
 * @author Navy Jones
 */
@Entity(name = "Household")
@Table(name = "households")
public class Household {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;
    private String passwordHash;
    private String salt;
    private String householdName;

    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<User> members = new HashSet<>();

    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Chore> chores = new HashSet<>();

    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Bill> bills = new HashSet<>();

    /**
     * Instantiates a new Household.
     */
    public Household() {
    }

    /**
     * Instantiates a new Household.
     *
     * @param passwordHash  the password hash
     * @param salt          the salt
     * @param householdName the household name
     */
    public Household(String passwordHash, String salt, String householdName) {
        this.passwordHash = passwordHash;
        this.salt = salt;
        this.householdName = householdName;
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
     * Gets chores.
     *
     * @return the chores
     */
    public Set<Chore> getChores() {
        return chores;
    }

    /**
     * Sets chores.
     *
     * @param chores the chores
     */
    public void setChores(Set<Chore> chores) {
        this.chores = chores;
    }

    /**
     * Gets bills.
     *
     * @return the bills
     */
    public Set<Bill> getBills() {
        return bills;
    }

    /**
     * Sets bills.
     *
     * @param bills the bills
     */
    public void setBills(Set<Bill> bills) {
        this.bills = bills;
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

    /**
     * Add chore.
     *
     * @param chore the chore
     */
    public void addChore(Chore chore) {
        chores.add(chore);
        chore.setHousehold(this);
    }

    /**
     * Remove chore.
     *
     * @param chore the chore
     */
    public void removeChore(Chore chore) {
        chores.remove(chore);
        chore.setHousehold(null);
    }

    /**
     * Add bill.
     *
     * @param bill the bill
     */
    public void addBill(Bill bill) {
        bills.add(bill);
        bill.setHousehold(this);
    }

    /**
     * Remove bill.
     *
     * @param bill the bill
     */
    public void removeBill(Bill bill) {
        bills.remove(bill);
        bill.setHousehold(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Household household = (Household) o;
        return id == household.id && Objects.equals(passwordHash, household.passwordHash) && Objects.equals(salt, household.salt) && Objects.equals(householdName, household.householdName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passwordHash, salt, householdName);
    }
}
