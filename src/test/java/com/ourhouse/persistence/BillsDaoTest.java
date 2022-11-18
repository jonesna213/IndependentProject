package com.ourhouse.persistence;

import com.ourhouse.entity.Bill;
import com.ourhouse.entity.Household;
import com.ourhouse.testUtils.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class is for testing the Bills dao
 *
 * @author Navy Jones
 */
public class BillsDaoTest {
    GenericDao<Bill> dao;

    /**
     * Run set up tasks before each test:
     * 1. execute sql which deletes everything from the table and inserts records)
     * 2. Create any objects needed in the tests
     */
    @BeforeEach
    void setUp() {

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        dao = new GenericDao<>(Bill.class);
    }

    /**
     * Verify successful retrieval of a bill
     */
    @Test
    void getByIdSuccess() {
        Bill retrievedBill = dao.getById(1);
        assertEquals("firstBill", retrievedBill.getTitle());
        assertEquals("firstAmount", retrievedBill.getAmount());
    }

    /**
     * Verify successful insert of a bill
     */
    @Test
    void insertSuccess() {
        GenericDao<Household> householdDao = new GenericDao<>(Household.class);
        Household household = householdDao.getById(1);
        Bill newBill = new Bill("newTitle", "newDescription", "newDueDate", "newAmount", "newFreq", household);

        int id = dao.insert(newBill);
        assertNotEquals(0,id);
        Bill insertedBill = dao.getById(id);
        assertEquals(newBill, insertedBill);
    }

    /**
     * Verify successful update of a bill
     */
    @Test
    void updateSuccess() {
        String title = "new title";
        Bill billToUpdate = dao.getById(1);
        billToUpdate.setTitle(title);
        dao.saveOrUpdate(billToUpdate);
        Bill billAfterUpdate = dao.getById(1);
        assertEquals(billToUpdate, billAfterUpdate);
    }

    /**
     * Verify successful delete of a bill
     */
    @Test
    void deleteSuccess() {
        GenericDao<Household> householdDao = new GenericDao<>(Household.class);
        Bill bill = dao.getById(1);

        dao.delete(bill);
        Household household = householdDao.getById(1);

        assertNull(dao.getById(1));
        assertFalse(household.getBills().contains(bill));
    }

    /**
     * Verify successful retrieval of all bills
     */
    @Test
    void getAllSuccess() {
        List<Bill> bills = dao.getAll();
        assertEquals(3, bills.size());
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<Bill> bills = dao.getByPropertyEqual("title", "firstBill");
        assertEquals(1, bills.size());
        assertEquals(1, bills.get(0).getId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<Bill> bills = dao.getByPropertyLike("title", "Bill");
        assertEquals(3, bills.size());
    }
}
