package by.epam.corporate_education.dao.util.impl;

import by.epam.corporate_education.dao.util.api.StatementInitializer;
import by.epam.corporate_education.entity.Training;
import by.epam.corporate_education.entity.User;

import java.io.InputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class StatementInitializerImpl implements StatementInitializer {

    @Override
    public void initDislikeEnabled(PreparedStatement statement, int value, int value2) throws SQLException {
        statement.setInt(1, value);
        statement.setInt(2, value2);
    }

    @Override
    public void initLikeEnabled(PreparedStatement statement, int value, int value2) throws SQLException {
        statement.setInt(1, value);
        statement.setInt(2, value2);
    }

    @Override
    public void initTrainingTrainerId(PreparedStatement statement, int value) throws SQLException {
        statement.setInt(1, value);
    }

    @Override
    public void initDislikes(PreparedStatement statement, int value) throws SQLException {
        statement.setInt(1, value);
    }

    @Override
    public void initDislike(PreparedStatement statement, int value, int value2) throws SQLException {
        statement.setInt(1, value2);
        statement.setInt(2, value);
    }

    @Override
    public void initLike(PreparedStatement statement, int value, int value2) throws SQLException {
        statement.setInt(1, value2);
        statement.setInt(2, value);
    }

    @Override
    public void initLikes(PreparedStatement statement, int value) throws SQLException {
        statement.setInt(1, value);
    }

    @Override
    public void initQueries(PreparedStatement statement, int value) throws SQLException {
        statement.setInt(1, value);
    }

    @Override
    public void initQuery(PreparedStatement statement, int value) throws SQLException {
        statement.setInt(1, value);
    }

    @Override
    public void initQuery(PreparedStatement statement, int value, int value2)
            throws SQLException {
        statement.setInt(1, value);
        statement.setInt(2, value2);
    }

    @Override
    public void initUser(PreparedStatement statement, boolean status, int value) throws SQLException {
        statement.setBoolean(1, status);
        statement.setInt(2, value);
    }

    @Override
    public void initUserInfo(PreparedStatement statement, int value) throws SQLException {
        statement.setInt(1, value);
    }

    @Override
    public void initUser(PreparedStatement statement, int value) throws SQLException {
        statement.setInt(1, value);
    }

    @Override
    public void initUser(PreparedStatement statement, String value) throws SQLException {
        statement.setString(1, value);
    }

    @Override
    public void initUser(PreparedStatement statement, User user, String encodedPassword) throws SQLException {
        statement.setString(1, user.getUsername());
        statement.setString(2, encodedPassword);
        statement.setString(3, user.getEmail());
        statement.setBoolean(4, user.isOnlineStatus());
    }

    @Override
    public void initUser(PreparedStatement statement, User user, int value) throws SQLException {
        statement.setBoolean(1, !user.isBannedStatus());
        statement.setInt(2, value);
    }

    @Override
    public void initUser(PreparedStatement statement, int value, int value2) throws SQLException{
        statement.setInt(1, value2);
        statement.setInt(2, value);
    }

    @Override
    public void initUser(PreparedStatement statement, int value, User user) throws SQLException {
        statement.setBoolean(1, !user.isLeaderStatus());
        statement.setInt(2, value);
    }

    @Override
    public void initTraining(PreparedStatement statement, int start, int recordsPerPage) throws SQLException {
        statement.setInt(1, start);
        statement.setInt(2, recordsPerPage);
    }

    @Override
    public void initTrainingId(PreparedStatement statement, int value) throws SQLException {
        statement.setInt(1, value);
    }

    @Override
    public void initTrainingDeleted(PreparedStatement statement, boolean deletedValue, int value) throws SQLException {
        statement.setBoolean(1, deletedValue);
        statement.setInt(2, value);
    }

    @Override
    public void initTrainingUpdating(PreparedStatement statement, Training training, InputStream stream) throws SQLException {
        statement.setString(1, training.getTitle());
        statement.setString(2, training.getRequirements());
        statement.setString(3, training.getInformation());
        statement.setString(4, training.getCity());
        statement.setInt(5, training.getHoursAmount());
        statement.setInt(6, training.getMinMembers());
        statement.setInt(7, training.getMaxMembers());
        statement.setDate(8, Date.valueOf(training.getStartDate()));
        statement.setDate(9, Date.valueOf(training.getEndDate()));
        statement.setBinaryStream(10, stream);
        statement.setInt(11, training.getIdTrainer());
        statement.setInt(12, training.getIdTraining());
    }

    @Override
    public void initChecker(PreparedStatement statement, String value) throws SQLException {
        statement.setString(1, value);
    }
}
