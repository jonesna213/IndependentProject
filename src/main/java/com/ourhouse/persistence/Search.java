package com.ourhouse.persistence;

import com.ourhouse.entity.Chore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * This class is for searching the database for chores.
 *
 * @author Navy Jones
 */
public class Search {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final GenericDao<Chore> dao = new GenericDao<>(Chore.class);

    /**
     * Calls execute search function which returns either null or a list of the items.
     *
     * @param searchTerm the search term
     * @param searchType the search type
     * @return either null or the list of chores
     */
    public List<Chore> searchChores(String searchTerm, String searchType) {
        return executeSearch(searchTerm, searchType);
    }

    /**
     * Executes the search in the database
     * @param searchTerm the search term
     * @param searchType the search type
     * @return either null or the list of chores
     */
    private List<Chore> executeSearch(String searchTerm, String searchType) {
        if (!searchType.equals("name") && !searchType.equals("description") && !searchType.equals("completeBy") &&
                !searchType.equals("frequency")) {
            return null;
        }

        List<Chore> results = dao.getByPropertyLike(searchType, searchTerm);

        if (results.size() == 0) {
            return null;
        }
        return results;
    }
}
