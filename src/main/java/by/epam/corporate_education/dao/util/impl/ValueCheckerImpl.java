package by.epam.corporate_education.dao.util.impl;

import by.epam.corporate_education.dao.SQLRequest;
import by.epam.corporate_education.dao.exception.NoUsernamePasswordException;
import by.epam.corporate_education.dao.exception.UsedEmailException;
import by.epam.corporate_education.dao.exception.UsedUsernameException;
import by.epam.corporate_education.dao.util.DAOUtilFactory;
import by.epam.corporate_education.dao.util.api.ResourceCloser;
import by.epam.corporate_education.dao.util.api.StatementInitializer;
import by.epam.corporate_education.dao.util.api.ValueChecker;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ValueCheckerImpl implements ValueChecker {

    private StatementInitializer statementInitializer = new StatementInitializerImpl();
    private ResourceCloser resourceCloser = new ResourceCloserImpl();

    @Override
    public void checkPasswordsEquals(Connection connection, String username, String password)
            throws SQLException, NoUsernamePasswordException {
        if (!arePasswordsEquals(connection, username, password)){
            throw new NoUsernamePasswordException("Username or password is incorrect!");
        }
    }

    @Override
    public void checkAll(Connection connection, String username, String email)
            throws SQLException, UsedUsernameException, UsedEmailException {
        if (usernameIsUsed(connection, username) != 0){
            throw new UsedUsernameException("Username is already taken!");
        } else if (emailIsUsed(connection, email) != 0){
            throw new UsedEmailException("E-mail is already taken!");
        }
    }

    private int usernameIsUsed(Connection connection, String username) throws SQLException {
        String request = SQLRequest.GET_USER_BY_USERNAME;
        ResultSet resultSet = null;
        int result = 0;
        try (PreparedStatement statement = connection.prepareStatement(request)) {

            statementInitializer.initChecker(statement, username);
            resultSet = statement.executeQuery();
            result = getID(resultSet);
        } finally {
            resourceCloser.close(resultSet);
        }
        return result;
    }

    private int emailIsUsed(Connection connection, String email) throws SQLException {
        String request = SQLRequest.GET_USER_BY_EMAIL;
        ResultSet resultSet = null;
        int result = 0;
        try (PreparedStatement statement = connection.prepareStatement(request)) {

            statementInitializer.initChecker(statement, email);
            resultSet = statement.executeQuery();
            result = getID(resultSet);
        } finally {
            resourceCloser.close(resultSet);
        }
        return result;
    }

    private int getID(ResultSet resultSet) throws SQLException {
        int result = 0;
        while(resultSet.next()){
            result = resultSet.getInt(1);
        }
        return result;
    }

    private boolean arePasswordsEquals(Connection connection, String username, String password) throws SQLException {
        String request = SQLRequest.GET_PASSWORD_BY_USERNAME;
        ResultSet resultSet = null;
        String passwordDatabase = null;
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(request)){

            statementInitializer.initChecker(statement, username);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                passwordDatabase = resultSet.getString(1);
                result = BCrypt.checkpw(password, passwordDatabase);
            }
        } finally {
            resourceCloser.close(resultSet);
        }
        return result;
    }
}
