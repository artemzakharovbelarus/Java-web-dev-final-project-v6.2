package by.epam.corporate_education.dao.impl;

import by.epam.corporate_education.dao.SQLRequest;
import by.epam.corporate_education.dao.api.QueryDAO;
import by.epam.corporate_education.dao.exception.DAOException;
import by.epam.corporate_education.dao.pool.DBConnectionPool;
import by.epam.corporate_education.dao.util.DAOUtilFactory;
import by.epam.corporate_education.dao.util.api.ResultCreator;
import by.epam.corporate_education.dao.util.api.ResourceCloser;
import by.epam.corporate_education.dao.util.api.StatementInitializer;
import by.epam.corporate_education.entity.Query;
import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class QueryDAOImpl implements QueryDAO {
    private DBConnectionPool connectionPool = DBConnectionPool.getINSTANCE();
    private DAOUtilFactory utilFactory = DAOUtilFactory.getINSTANCE();
    private StatementInitializer statementInitializer = utilFactory.getStatementInitializer();
    private ResultCreator resultCreator = utilFactory.getResultCreator();
    private ResourceCloser resourceCloser = utilFactory.getResourceCloser();

    @Override
    public void changeAcceptedStatus(int idQuery, int answer) throws DAOException {
        String request = SQLRequest.CHANGE_ACCEPTED_STATUS;

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(request)){

            statementInitializer.initQuery(statement, answer, idQuery);
            statement.executeUpdate();
        } catch (SQLException e){
            throw new DAOException(e);
        }
    }

    @Override
    public List<Query> getTrainingQueries(int idTraining) throws DAOException {
        String request = SQLRequest.GET_TRAINING_QUERIES;

        List<Query> queries = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(request)){

            statementInitializer.initQueries(statement, idTraining);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                Query query = resultCreator.getFullQueryToTraining(resultSet);
                queries.add(query);
            }
        } catch (SQLException e){
            throw new DAOException();
        }
        return queries;
    }

    @Override
    public void changeCanceledStatus(int idQuery) throws DAOException {
        String request = SQLRequest.CHANGE_CANCELED_STATUS;

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(request)){

            statementInitializer.initQuery(statement, idQuery);
            statement.executeUpdate();
        } catch (SQLException e){
            throw new DAOException(e);
        }
    }

    @Override
    public List<Query> getAllQueries(int idUser) throws DAOException {
        String request = SQLRequest.GET_ALL_QUERIES_BY_ID_USER;

        ResultSet resultSet = null;
        List<Query> queries = new ArrayList<>();
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(request)){

            statementInitializer.initQuery(statement, idUser);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                Query query = resultCreator.getFullQueryInfo(resultSet);
                queries.add(query);
            }
        } catch (SQLException e){
            throw new DAOException(e);
        } finally {
            resourceCloser.close(resultSet);
        }
        return queries;
    }

    @Override
    public void addQuery(Query query) throws DAOException {
        String request = SQLRequest.ADD_QUERY;

        try(Connection connection = connectionPool.takeConnection();
            PreparedStatement statement = connection.prepareStatement(request)){

            statementInitializer.initQuery(statement, query.getIdTraining(), query.getIdUser());
            statement.executeUpdate();
        } catch (SQLException e){
            throw new DAOException(e);
        }
    }

    @Override
    public Query getQueryByIdTrainingIdUser(int idTraining, int idUser) throws DAOException {
        String request = SQLRequest.GET_QUERY_BY_ID_TRAINING_ID_USER;

        ResultSet resultSet = null;
        Query query = new Query();
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(request)){

            statementInitializer.initQuery(statement, idTraining, idUser);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                query = resultCreator.getQuery(resultSet);
            } else {
                query = new Query();
            }
        } catch (SQLException e){
            throw new DAOException(e);
        } finally {
            resourceCloser.close(resultSet);
        }
        return query;
    }
}
