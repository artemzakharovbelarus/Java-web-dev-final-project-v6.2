package by.epam.corporate_education.dao.impl;

import by.epam.corporate_education.dao.SQLRequest;
import by.epam.corporate_education.dao.api.TrainingDAO;
import by.epam.corporate_education.dao.exception.DAOException;
import by.epam.corporate_education.dao.pool.DBConnectionPool;
import by.epam.corporate_education.dao.util.DAOUtilFactory;
import by.epam.corporate_education.dao.util.api.*;
import by.epam.corporate_education.entity.Training;
import lombok.extern.log4j.Log4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class TrainingDAOImpl implements TrainingDAO {
    private DBConnectionPool connectionPool = DBConnectionPool.getINSTANCE();
    private DAOUtilFactory utilFactory = DAOUtilFactory.getINSTANCE();
    private StatementInitializer statementInitializer = utilFactory.getStatementInitializer();
    private ResourceCloser resourceCloser = utilFactory.getResourceCloser();
    private ResultCreator resultCreator = utilFactory.getResultCreator();

    @Override
    public List<Training> getAllTrainingsByIdTrainer(int idTrainer) throws DAOException {
        String request = SQLRequest.GET_ALL_TRAININGS_BY_ID_TRAINER;

        List<Training> trainings = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(request)){

            statementInitializer.initTrainingTrainerId(statement, idTrainer);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                Training training = resultCreator.getNextTraining(resultSet);
                trainings.add(training);
            }
        } catch (SQLException e){
            throw new DAOException(e);
        }
        return trainings;
    }

    @Override
    public Training getTraining(int idTraining) throws DAOException {
        String request = SQLRequest.GET_TRAINING_BY_ID;

        ResultSet resultSet = null;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(request)){

            statementInitializer.initTrainingId(statement, idTraining);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                return resultCreator.getNextTraining(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            resourceCloser.close(resultSet);
        }
        throw new DAOException("No training with ID: " + idTraining + " in database");
    }

    @Override
    public List<Training> getAllTrainings() throws DAOException {
        String request = SQLRequest.GET_ALL_TRAININGS;
        List<Training> trainings = new ArrayList<>();

        ResultSet resultSet = null;
        try(Connection connection = connectionPool.takeConnection();
            PreparedStatement statement = connection.prepareStatement(request)){

            resultSet = statement.executeQuery();
            while (resultSet.next()){
                Training training = resultCreator.getNextTraining(resultSet);
                trainings.add(training);
            }
        } catch (SQLException e){
            throw new DAOException(e);
        } finally {
            resourceCloser.close(resultSet);
        }
        return trainings;
    }

    @Override
    public List<Training> getAllTrainingsSorted(String parameter, String typeSorting) throws DAOException {
        List<Training> trainingsSorted = new ArrayList<>();
        SortingManager sortingManager = utilFactory.getSortingManager();

        ResultSet resultSet = null;
        try (Connection connection = connectionPool.takeConnection();
             Statement statement = connection.createStatement()){
             String request = sortingManager.getSortingRequest(parameter, typeSorting);
            resultSet = statement.executeQuery(request);

            while (resultSet.next()){
                Training training = resultCreator.getNextTraining(resultSet);
                trainingsSorted.add(training);
            }
        } catch (SQLException e){
            throw new DAOException(e);
        } finally {
            resourceCloser.close(resultSet);
        }
        return trainingsSorted;
    }

    @Override
    public void addTraining(Training training) throws DAOException {
        String request = "";
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(request)){

            statement.executeUpdate();
        } catch (SQLException e){
            throw new DAOException(e);
        }
    }

    @Override
    public int changeDeletedStatus(int idTraining) throws DAOException {
        String request = SQLRequest.CHANGE_TRAINING_DELETED_STATUS;

        int result = 0;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(request)){

            Training training = getTraining(idTraining);
            statementInitializer.initTrainingDeleted(statement, !training.isDeletedStatus(), idTraining);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return result;
    }

    @Override
    public int getRowsAmount() throws DAOException {
        String request = SQLRequest.GET_ROWS_COUNT_TRAININGS;
        RowsCounter rowsCounter = utilFactory.getRowsCounter();
        int rowsAmount = 0;
        try (Connection connection = connectionPool.takeConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(request)){

             rowsAmount = rowsCounter.getRowsAmount(resultSet);
        } catch (SQLException e){
            throw new DAOException(e);
        }
        return rowsAmount;
    }

    @Override
    public void updateTraining(Training training) throws DAOException {
        String request = SQLRequest.UPDATE_TRAINING_VALUES;

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(request)){

            statementInitializer.initTrainingUpdating(statement, training);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
