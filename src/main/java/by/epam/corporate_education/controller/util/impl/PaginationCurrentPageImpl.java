package by.epam.corporate_education.controller.util.impl;

import by.epam.corporate_education.controller.util.api.PaginationCurrentPage;
import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.http.HttpServletRequest;

public class PaginationCurrentPageImpl implements PaginationCurrentPage {

    @Override
    public int getCurrentPage(HttpServletRequest request) {
        int currentPage = 0;
        Object currentPageObject = request.getParameter("currentPage");
        if (currentPageObject == null){
            currentPage = 1;
        } else {
            getCurrentPageValue(currentPageObject);
        }
        return currentPage;
    }

    private int getCurrentPageValue(Object currentPageObject){
        int currentPage = 0;
        String currentPageString = (String) currentPageObject;
        if (isNumber(currentPageString)){
            currentPage = Integer.parseInt(currentPageString);
        } else {
            currentPage = 1;
        }
        return currentPage;
    }

    private boolean isNumber(String currentPageString){
        boolean isNumber = NumberUtils.isCreatable(currentPageString);
        return isNumber;
    }
}
