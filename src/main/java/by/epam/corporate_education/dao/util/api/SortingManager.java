package by.epam.corporate_education.dao.util.api;

import by.epam.corporate_education.dao.exception.InvalidParameterTypeSortingException;

public interface SortingManager {
    public String getSortingRequest(String parameter, String typeSorting) throws InvalidParameterTypeSortingException;
}
