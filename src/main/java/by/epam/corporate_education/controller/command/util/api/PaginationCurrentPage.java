package by.epam.corporate_education.controller.command.util.api;

import javax.servlet.http.HttpServletRequest;

public interface PaginationCurrentPage {
    public int getCurrentPage(HttpServletRequest request);
}
