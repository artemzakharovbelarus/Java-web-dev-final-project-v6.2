package by.epam.corporate_education.dao.util.api;

import by.epam.corporate_education.entity.Training;
import by.epam.corporate_education.entity.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public interface StatementInitializer {
    public void initUser(PreparedStatement statement, boolean status, int idUser) throws SQLException;
    public void initUser(PreparedStatement statement, int idUser) throws SQLException;
    public void initUser(PreparedStatement statement, String username) throws SQLException;
    public void initUser(PreparedStatement statement, User user, String encodedPassword) throws SQLException;
    public void initUser(PreparedStatement statement, User user, int idUser) throws SQLException;
    public void initUser(PreparedStatement statement, int idUser, int idStatus) throws SQLException;
    public void initUser(PreparedStatement statement, int idUser, User user) throws SQLException;
    public void initTraining(PreparedStatement statement, int start, int recordsPerPage) throws SQLException;
    public void initUserInfo(PreparedStatement statement, int idUser) throws SQLException;
    public void initTrainingId(PreparedStatement statement, int idTraining) throws SQLException;
    public void initTrainingDeleted(PreparedStatement statement, boolean deletedValue, int idTraining) throws SQLException;
    public void initTrainingUpdating(PreparedStatement statement, Training training) throws SQLException;
    public void initChecker(PreparedStatement statement, String value) throws SQLException;
    public void initQuery(PreparedStatement statement, int idTraining, int idUser) throws SQLException;
    public void initQuery(PreparedStatement statement, int idUser) throws SQLException;
    public void initQueries(PreparedStatement statement, int idTraining) throws SQLException;
    public void initLikes(PreparedStatement statement, int idTraining) throws SQLException;
    public void initLike(PreparedStatement statement, int idTraining, int idUser) throws SQLException;
    public void initDislikes(PreparedStatement statement, int idTraining) throws SQLException;
    public void initDislike(PreparedStatement statement, int idTraining, int idUser) throws SQLException;
    public void initTrainingTrainerId(PreparedStatement statement, int idTrainer) throws SQLException;
    public void initDislikeEnabled(PreparedStatement statement, int idTraining, int idUser) throws SQLException;
    public void initLikeEnabled(PreparedStatement statement, int idTraining, int idUser) throws SQLException;
}
