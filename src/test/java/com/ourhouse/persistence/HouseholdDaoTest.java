package com.ourhouse.persistence;

import com.ourhouse.entity.Household;
import com.ourhouse.entity.User;
import com.ourhouse.testUtils.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class is for testing the Household dao
 *
 * @author Navy Jones
 */
public class HouseholdDaoTest {
    GenericDao<Household> dao;

    /**
     * Run set up tasks before each test:
     * 1. execute sql which deletes everything from the table and inserts records)
     * 2. Create any objects needed in the tests
     */
    @BeforeEach
    void setUp() {

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        dao = new GenericDao<>(Household.class);
    }

    /**
     * Verify successful retrieval of a household
     */
    @Test
    void getByIdSuccess() {
        Household retrievedHousehold = dao.getById(1);
        assertEquals("testHousehold", retrievedHousehold.getHouseholdName());
        assertEquals("salt", retrievedHousehold.getSalt());
    }

    /**
     * Verify successful insert of a household
     */
    @Test
    void insertSuccess() {
        Household newHousehold = new Household("hash", "salt", "householdName");

        int id = dao.insert(newHousehold);
        assertNotEquals(0,id);
        Household insertedHousehold = dao.getById(id);
        assertEquals(newHousehold, insertedHousehold);
    }

    /**
     * Verify successful update of a household
     */
    @Test
    void updateSuccess() {
        String householdName = "new household name";
        Household householdToUpdate = dao.getById(1);
        householdToUpdate.setHouseholdName(householdName);
        dao.saveOrUpdate(householdToUpdate);
        Household householdAfterUpdate = dao.getById(1);
        assertEquals(householdToUpdate, householdAfterUpdate);
    }

    /**
     * Verify successful delete of a household
     */
    @Test
    void deleteSuccess() {
        GenericDao<User> userDao = new GenericDao<>(User.class);
        Household household = dao.getById(1);

        dao.delete(household);
        User user = userDao.getById(1);

        assertNull(dao.getById(1));
        assertNull(user);
    }

    /**
     * Verify successful retrieval of all households
     */
    @Test
    void getAllSuccess() {
        List<Household> households = dao.getAll();
        assertEquals(3, households.size());
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<Household> households = dao.getByPropertyEqual("householdName", "testHousehold");
        assertEquals(1, households.size());
        assertEquals(1, households.get(0).getId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<Household> households = dao.getByPropertyLike("salt", "salt");
        assertEquals(3, households.size());
    }
}
