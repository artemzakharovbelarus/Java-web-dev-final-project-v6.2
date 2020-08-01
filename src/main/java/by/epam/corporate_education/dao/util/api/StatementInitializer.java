package by.epam.corporate_education.dao.util.api;

import by.epam.corporate_education.entity.Training;
import by.epam.corporate_education.entity.User;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public interface StatementInitializer {
    public void initUser(PreparedStatement statement, boolean status, int value) throws SQLException;
    public void initUser(PreparedStatement statement, int value) throws SQLException;
    public void initUser(PreparedStatement statement, String value) throws SQLException;
    public void initUser(PreparedStatement statement, User user, String encodedPassword) throws SQLException;
    public void initUser(PreparedStatement statement, User user, int value) throws SQLException;
    public void initUser(PreparedStatement statement, int value, int value2) throws SQLException;
    public void initUser(PreparedStatement statement, int value, User user) throws SQLException;
    public void initTraining(PreparedStatement statement, int start, int recordsPerPage) throws SQLException;
    public void initUserInfo(PreparedStatement statement, int value) throws SQLException;
    public void initTrainingId(PreparedStatement statement, int value) throws SQLException;
    public void initTrainingDeleted(PreparedStatement statement, boolean value, int value2) throws SQLException;
    public void initTrainingUpdating(PreparedStatement statement, Training training, InputStream stream) throws SQLException;
    public void initChecker(PreparedStatement statement, String value) throws SQLException;
    public void initQuery(PreparedStatement statement, int value, int value2) throws SQLException;
    public void initQuery(PreparedStatement statement, int value) throws SQLException;
    public void initQueries(PreparedStatement statement, int value) throws SQLException;
    public void initLikes(PreparedStatement statement, int value) throws SQLException;
    public void initLike(PreparedStatement statement, int value, int value2) throws SQLException;
    public void initDislikes(PreparedStatement statement, int value) throws SQLException;
    public void initDislike(PreparedStatement statement, int value, int value2) throws SQLException;
    public void initTrainingTrainerId(PreparedStatement statement, int value) throws SQLException;
    public void initDislikeEnabled(PreparedStatement statement, int value, int value2) throws SQLException;
    public void initLikeEnabled(PreparedStatement statement, int value, int value2) throws SQLException;
}
