package com.ourhouse.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * The Bill object.
 *
 * @author Navy Jones
 */
@Entity(name = "Bill")
@Table(name = "bills")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;
    private String title;
    private String description;
    private String dueDate;
    private String amount;
    private String frequency;
    @ManyToOne
    private Household household;

    /**
     * Instantiates a new Bill.
     */
    public Bill() {
    }

    /**
     * Instantiates a new Bill.
     *
     * @param id          the id
     * @param title       the title
     * @param description the description
     * @param dueDate     the due date
     * @param amount      the amount
     * @param frequency   the frequency
     * @param household   the household
     */
    public Bill(int id, String title, String description, String dueDate, String amount, String frequency, Household household) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.amount = amount;
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
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
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
     * Gets due date.
     *
     * @return the due date
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     * Sets due date.
     *
     * @param dueDate the due date
     */
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Sets amount.
     *
     * @param amount the amount
     */
    public void setAmount(String amount) {
        this.amount = amount;
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
