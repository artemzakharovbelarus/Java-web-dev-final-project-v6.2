package by.epam.corporate_education.dao.util.api;

import by.epam.corporate_education.dao.exception.NoUsernamePasswordException;
import by.epam.corporate_education.dao.exception.UsedEmailException;
import by.epam.corporate_education.dao.exception.UsedUsernameException;

import java.sql.Connection;
import java.sql.SQLException;

public interface ValueChecker {
    public void checkAll(Connection connection, String username, String password)
            throws SQLException, UsedUsernameException, UsedEmailException;
    public void checkPasswordsEquals(Connection connection, String username, String password)
            throws SQLException, NoUsernamePasswordException;
}
