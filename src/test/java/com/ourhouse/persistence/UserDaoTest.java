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
 * This class is for testing the User dao
 *
 * @author Navy Jones
 */
public class UserDaoTest {
    GenericDao<User> dao;

    /**
     * Run set up tasks before each test:
     * 1. execute sql which deletes everything from the table and inserts records)
     * 2. Create any objects needed in the tests
     */
    @BeforeEach
    void setUp() {

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        dao = new GenericDao<>(User.class);
    }

    /**
     * Verify successful retrieval of a user
     */
    @Test
    void getByIdSuccess() {
        User retrievedUser = dao.getById(1);
        assertEquals("Bobby", retrievedUser.getFirstName());
        assertEquals("Joe", retrievedUser.getLastName());
    }

    /**
     * Verify successful insert of a user
     */
    @Test
    void insertSuccess() {
        GenericDao<Household> householdDao = new GenericDao<>(Household.class);
        Household household = householdDao.getById(1);
        User newUser = new User("newUsername", "newFirstName", "newLastName", "newEmail", "user", household);

        int id = dao.insert(newUser);
        assertNotEquals(0,id);
        User insertedUser = dao.getById(id);
        assertEquals(newUser, insertedUser);
    }

    /**
     * Verify successful update of a user
     */
    @Test
    void updateSuccess() {
        String firstName = "new first name";
        User userToUpdate = dao.getById(1);
        userToUpdate.setFirstName(firstName);
        dao.saveOrUpdate(userToUpdate);
        User userAfterUpdate = dao.getById(1);
        assertEquals(userToUpdate, userAfterUpdate);
    }

    /**
     * Verify successful delete of a user
     */
    @Test
    void deleteSuccess() {
        GenericDao<Household> householdDao = new GenericDao<>(Household.class);
        User user = dao.getById(1);

        dao.delete(user);
        Household household = householdDao.getById(1);

        assertNull(dao.getById(1));
        assertFalse(household.getMembers().contains(user));
    }

    /**
     * Verify successful retrieval of all users
     */
    @Test
    void getAllSuccess() {
        List<User> users = dao.getAll();
        assertEquals(3, users.size());
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<User> users = dao.getByPropertyEqual("firstName", "Bobby");
        assertEquals(1, users.size());
        assertEquals(1, users.get(0).getId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<User> users = dao.getByPropertyLike("username", "joe");
        assertEquals(2, users.size());
    }
}
