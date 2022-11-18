package com.ourhouse.persistence;

import com.ourhouse.entity.Chore;
import com.ourhouse.entity.Household;
import com.ourhouse.testUtils.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class is for testing the Chores dao
 *
 * @author Navy Jones
 */
public class ChoresDaoTest {
    GenericDao<Chore> dao;

    /**
     * Run set up tasks before each test:
     * 1. execute sql which deletes everything from the table and inserts records)
     * 2. Create any objects needed in the tests
     */
    @BeforeEach
    void setUp() {

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        dao = new GenericDao<>(Chore.class);
    }

    /**
     * Verify successful retrieval of a chore
     */
    @Test
    void getByIdSuccess() {
        Chore retrievedChore = dao.getById(1);
        assertEquals("firstChore", retrievedChore.getName());
        assertEquals("1st", retrievedChore.getCompleteBy());
    }

    /**
     * Verify successful insert of a chore
     */
    @Test
    void insertSuccess() {
        GenericDao<Household> householdDao = new GenericDao<>(Household.class);
        Household household = householdDao.getById(1);
        Chore newChore = new Chore("newName", "newDescription", "newCompleteBy", "newFreq", household);

        int id = dao.insert(newChore);
        assertNotEquals(0,id);
        Chore insertedChore = dao.getById(id);
        assertEquals(newChore, insertedChore);
    }

    /**
     * Verify successful update of a chore
     */
    @Test
    void updateSuccess() {
        String name = "new name";
        Chore choreToUpdate = dao.getById(1);
        choreToUpdate.setName(name);
        dao.saveOrUpdate(choreToUpdate);
        Chore choreAfterUpdate = dao.getById(1);
        assertEquals(choreToUpdate, choreAfterUpdate);
    }

    /**
     * Verify successful delete of a chore
     */
    @Test
    void deleteSuccess() {
        GenericDao<Household> householdDao = new GenericDao<>(Household.class);
        Chore chore = dao.getById(1);

        dao.delete(chore);
        Household household = householdDao.getById(1);

        assertNull(dao.getById(1));
        assertFalse(household.getChores().contains(chore));
    }

    /**
     * Verify successful retrieval of all chores
     */
    @Test
    void getAllSuccess() {
        List<Chore> chores = dao.getAll();
        assertEquals(3, chores.size());
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<Chore> chores = dao.getByPropertyEqual("name", "firstChore");
        assertEquals(1, chores.size());
        assertEquals(1, chores.get(0).getId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<Chore> chores = dao.getByPropertyLike("name", "Chore");
        assertEquals(3, chores.size());
    }
}
