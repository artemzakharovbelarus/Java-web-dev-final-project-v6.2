package by.epam.corporate_education.dao.impl;

import by.epam.corporate_education.dao.SQLRequest;
import by.epam.corporate_education.dao.exception.DAOException;
import by.epam.corporate_education.dao.api.UserDAO;
import by.epam.corporate_education.dao.pool.DBConnectionPool;
import by.epam.corporate_education.dao.util.DAOUtilFactory;
import by.epam.corporate_education.dao.util.api.*;
import by.epam.corporate_education.dao.util.impl.ValueCheckerImpl;
import by.epam.corporate_education.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private DBConnectionPool connectionPool = DBConnectionPool.getINSTANCE();
    private DAOUtilFactory utilFactory = DAOUtilFactory.getINSTANCE();
    private StatementInitializer statementInitializer = utilFactory.getStatementInitializer();
    private ResourceCloser resourceCloser = utilFactory.getResourceCloser();
    private ResultCreator resultCreator = utilFactory.getResultCreator();
    private ValueChecker valueChecker = new ValueCheckerImpl();

    @Override
    public User getUser(int idUser) throws DAOException {
        String request = SQLRequest.GET_USER_BY_ID;

        ResultSet resultSet = null;
        try(Connection connection = connectionPool.takeConnection();
            PreparedStatement statement = connection.prepareStatement(request)){

            statementInitializer.initUser(statement, idUser);
            resultSet = statement.executeQuery();

            if (resultSet.next()){
                return resultCreator.getNextUser(resultSet);
            }
        } catch (SQLException e){
            throw new DAOException(e);
        } finally {
            resourceCloser.close(resultSet);
        }
        throw new DAOException("No user with ID: " + idUser + " in database");
    }

    @Override
    public User getUser(String username, String password) throws DAOException {
        String request = SQLRequest.GET_USER;

        ResultSet resultSet = null;
        User user = new User();
        try(Connection connection = connectionPool.takeConnection();
            PreparedStatement statement = connection.prepareStatement(request)){

            valueChecker.checkPasswordsEquals(connection, username, password);
            statementInitializer.initUser(statement, username);
            resultSet = statement.executeQuery();

            if (resultSet.next()){
                user = resultCreator.getNextUser(resultSet);
            }
        } catch (SQLException e){
            throw new DAOException(e);
        } finally {
            resourceCloser.close(resultSet);
        }
        return user;
    }

    @Override
    public int addNewUser(User user, String password) throws DAOException {
        String request = SQLRequest.ADD_NEW_USER;
        int result;

        try(Connection connection = connectionPool.takeConnection();
            PreparedStatement statement = connection.prepareStatement(request)){

            valueChecker.checkAll(connection, user.getUsername(), user.getEmail());

            statementInitializer.initUser(statement, user, password);
            result = statement.executeUpdate();
        } catch (SQLException e){
            throw new DAOException(e);
        }
        return result;
    }

    @Override
    public List<User> getAllUsers() throws DAOException {
        String request = SQLRequest.GET_ALL_USERS;
        List<User> users = new ArrayList<>();

        ResultSet resultSet = null;
        try(Connection connection = connectionPool.takeConnection();
            Statement statement = connection.createStatement()){

            resultSet = statement.executeQuery(request);
            while (resultSet.next()){
                User user = resultCreator.getNextUser(resultSet);
                users.add(user);
            }
        } catch (SQLException e){
            throw new DAOException(e);
        } finally {
           resourceCloser.close(resultSet);
        }
        return users;
    }

    @Override
    public List<User> getAllUsersSorted(String parameter, String typeSorting) throws DAOException {
        List<User> usersSorted = new ArrayList<>();
        SortingManager sortingManager = utilFactory.getSortingManager();

        ResultSet resultSet = null;
        try (Connection connection = connectionPool.takeConnection();
            Statement statement = connection.createStatement()){
            String request = sortingManager.getSortingRequest(parameter, typeSorting);
            resultSet = statement.executeQuery(request);

            while (resultSet.next()){
                User user = resultCreator.getNextUser(resultSet);
                usersSorted.add(user);
            }
        } catch (SQLException e){
            throw new DAOException(e);
        } finally {
            resourceCloser.close(resultSet);
        }
        return usersSorted;
    }

    @Override
    public List<User> getUsersOnline() throws DAOException {
        return null;
    }

    @Override
    public void changeBannedStatus(int idUser, boolean status) throws DAOException {
        String request = SQLRequest.CHANGE_BANNED_STATUS;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(request)){

            statementInitializer.initUser(statement, status, idUser);
            statement.executeUpdate();
        } catch (SQLException e){
            throw new DAOException(e);
        }
    }

    @Override
    public void changeUserStatus(int idUser, int idStatus) throws DAOException {
        String request = SQLRequest.CHANGE_USER_STATUS;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(request)){

            statementInitializer.initUser(statement, idUser, idStatus);
            statement.executeUpdate();
        } catch (SQLException e){
            throw new DAOException(e);
        }
    }

    @Override
    public void changeLeaderStatus(int idUser) throws DAOException {
        String request = SQLRequest.CHANGE_LEADER_STATUS;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(request)){
            User user = getUser(idUser);

            statementInitializer.initUser(statement, idUser, user);
            statement.executeUpdate();
        } catch (SQLException e){
            throw new DAOException(e);
        }
    }

    @Override
    public User getAllUserInformation(int idUser) throws DAOException {
        String request = SQLRequest.GET_ALL_USER_INFORMATION;

        ResultSet resultSet = null;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(request);) {

            statementInitializer.initUserInfo(statement, idUser);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                return resultCreator.getFullUser(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            resourceCloser.close(resultSet);
        }
        throw new DAOException("No user with ID: " + idUser + " in database");
    }

    private void changeOnlineStatus(Connection connection, boolean status, int idUser) throws SQLException{
        String request = SQLRequest.CHANGE_ONLINE_STATUS;
        try (PreparedStatement statement = connection.prepareStatement(request)){
            statementInitializer.initUser(statement, status, idUser);
            statement.executeUpdate();
        }
    }

    @Override
    public void changeOnlineStatus(int idUser, boolean status) throws DAOException {
        try (Connection connection = connectionPool.takeConnection()){
            changeOnlineStatus(connection, status, idUser);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
