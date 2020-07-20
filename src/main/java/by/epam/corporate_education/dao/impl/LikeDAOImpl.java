package by.epam.corporate_education.dao.impl;

import by.epam.corporate_education.dao.SQLRequest;
import by.epam.corporate_education.dao.api.LikeDAO;
import by.epam.corporate_education.dao.exception.DAOException;
import by.epam.corporate_education.dao.pool.DBConnectionPool;
import by.epam.corporate_education.dao.util.DAOUtilFactory;
import by.epam.corporate_education.dao.util.api.ResourceCloser;
import by.epam.corporate_education.dao.util.api.ResultCreator;
import by.epam.corporate_education.dao.util.api.StatementInitializer;
import by.epam.corporate_education.entity.Like;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LikeDAOImpl implements LikeDAO {
    private DBConnectionPool connectionPool = DBConnectionPool.getINSTANCE();
    private DAOUtilFactory utilFactory = DAOUtilFactory.getINSTANCE();
    private StatementInitializer statementInitializer = utilFactory.getStatementInitializer();
    private ResourceCloser resourceCloser = utilFactory.getResourceCloser();
    private ResultCreator resultCreator = utilFactory.getResultCreator();

    @Override
    public void changeEnabledStatus(Like like) throws DAOException {
        String request = SQLRequest.CHANGE_LIKE_ENABLED_STATUS;

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(request)){

            statementInitializer.initDislikeEnabled(statement, like.getIdTraining(), like.getIdUser());
            statement.executeUpdate();
        } catch (SQLException e){
            throw new DAOException(e);
        }
    }

    @Override
    public boolean getLikeEnabledStatus(Like like) throws DAOException {
        String request = SQLRequest.GET_LIKE_ENABLED_STATUS;

        ResultSet resultSet = null;
        boolean result = false;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(request)){

            statementInitializer.initDislikeEnabled(statement, like.getIdTraining(), like.getIdUser());
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                result = resultCreator.getEnabledStatus(resultSet);
            }
        } catch (SQLException e){
            throw new DAOException(e);
        }
        return result;
    }

    @Override
    public int getTrainingLikesAmount(int idTraining) throws DAOException {
        String request = SQLRequest.GET_TRAINING_LIKES;

        ResultSet resultSet = null;
        int result = 0;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(request)){

            statementInitializer.initLikes(statement, idTraining);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                result = resultCreator.getLikesAmount(resultSet);
            } else {
                result = 0;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            resourceCloser.close(resultSet);
        }
        return result;
    }

    @Override
    public void addTrainingLike(Like like) throws DAOException {
        String request = SQLRequest.ADD_TRAINING_LIKE;

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(request)){

            statementInitializer.initLike(statement, like.getIdTraining(), like.getIdUser());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
