package com.ourhouse.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * The Chore object.
 *
 * @author Navy Jones
 */
@Entity(name = "Chore")
@Table(name = "chores")
public class Chore {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;
    private String name;
    private String description;
    private String completeBy;
    private String frequency;
    @ManyToOne
    private Household household;

    /**
     * Instantiates a new Chore.
     */
    public Chore() {
    }

    /**
     * Instantiates a new Chore.
     *
     * @param name        the name
     * @param description the description
     * @param completeBy  the complete by
     * @param frequency   the frequency
     * @param household   the household
     */
    public Chore(String name, String description, String completeBy, String frequency, Household household) {
        this.name = name;
        this.description = description;
        this.completeBy = completeBy;
        this.frequency = frequency;
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets complete by.
     *
     * @return the complete by
     */
    public String getCompleteBy() {
        return completeBy;
    }

    /**
     * Sets complete by.
     *
     * @param completeBy the complete by
     */
    public void setCompleteBy(String completeBy) {
        this.completeBy = completeBy;
    }

    /**
     * Gets frequency.
     *
     * @return the frequency
     */
    public String getFrequency() {
        return frequency;
    }

    /**
     * Sets frequency.
     *
     * @param frequency the frequency
     */
    public void setFrequency(String frequency) {
        this.frequency = frequency;
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
     * Sets household.
     *
     * @param household the household
     */
    public void setHousehold(Household household) {
        this.household = household;
    }
}
