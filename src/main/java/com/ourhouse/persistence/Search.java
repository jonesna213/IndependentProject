package com.ourhouse.persistence;

import com.ourhouse.entity.Chore;

import java.util.List;

/**
 * This class is for searching the database for chores.
 * @param <T> the type of entity you want the search to be for
 * @author Navy Jones
 */
public class Search<T> {
    private Class<T> type;
    /**
     * Instantiates a new Search.
     *
     * @param type the entity type, for example, User.
     */
    public Search(Class<T> type) {
        this.type = type;
    }

    /**
     * Executes the search in the database
     * @param searchTerm the search term
     * @param searchType the search type
     * @return either null or the list of items
     */
    public List<T> executeSearch(String searchTerm, String searchType) {
        GenericDao<T> dao = new GenericDao<>(type);
        if (!searchType.equals("name") && !searchType.equals("description") && !searchType.equals("completeBy") &&
                !searchType.equals("frequency") && !searchType.equals("title") && !searchType.equals("dueDate") &&
                !searchType.equals("amount")) {
            return null;
        }

        List<T> results = dao.getByPropertyLike(searchType, searchTerm);

        if (results.size() == 0) {
            return null;
        }
        return results;
    }
}
