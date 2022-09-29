package com.ourhouse.entity;

import java.util.HashSet;
import java.util.Set;

public class Household {
    private int id;
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
