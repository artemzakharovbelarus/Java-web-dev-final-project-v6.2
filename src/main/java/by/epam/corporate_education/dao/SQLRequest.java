package by.epam.corporate_education.dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SQLRequest {
    public static final String CHANGE_ONLINE_STATUS = "UPDATE users SET online_status = ? WHERE idUser = ?";
    public static final String GET_USER = "SELECT idUser, username, email, leader_status, " +
            "banned_status, online_status, user_photo, idRole FROM users WHERE username = ?";
    public static final String GET_PASSWORD_BY_USERNAME = "SELECT password FROM users WHERE username = ?";
    public static final String GET_USER_BY_ID = "SELECT idUser, username, email, leader_status, " +
            "banned_status, online_status, user_photo, idRole FROM users WHERE idUser = ?";
    public static final String ADD_NEW_USER = "INSERT INTO users (username, password, email, online_status)" +
            "VALUES (?, ?, ?, ?)";
    public static final String GET_ALL_USERS = "SELECT idUser, username, email, leader_status, banned_status, " +
            "online_status, user_photo, idRole FROM users";
    public static final String GET_ALL_USERS_SORTED_BY_ID_ASC = "SELECT idUser, username, email, online_status, " +
            "user_photo leader_status, banned_status, FROM users ORDER BY idUser ASC";
    public static final String GET_ALL_USERS_SORTED_BY_ID_DESC = "SELECT idUser, username, email, online_status, " +
            "user_photo leader_status, banned_status, FROM users ORDER BY idUser DESC";
    public static final String CHANGE_BANNED_STATUS = "UPDATE users SET banned_status = ? WHERE idUser = ?";
    public static final String CHANGE_USER_STATUS = "UPDATE users SET idRole = ? WHERE idUser = ?";
    public static final String CHANGE_LEADER_STATUS = "UPDATE users SET leader_status = ? WHERE idUser = ?";
    public static final String GET_ALL_TRAININGS = "SELECT idTraining, title, requirements, information, deleted_status, " +
            "city, hours_amount, min_members, max_members, start_date, end_date, training_image, idUser FROM trainings";
    public static final String GET_TRAINING_BY_ID = "SELECT idTraining, title, requirements, information, deleted_status, " +
            "city, hours_amount, min_members, max_members, start_date, end_date, training_image, idUser FROM trainings " +
            "WHERE idTraining = ?";
    public static final String GET_ALL_TRAININGS_SORTED_BY_ID_ASC = "SELECT idTraining, title, level, city, " +
            "hours_amount, min_members, max_members, start_date, end_date, idUser " +
            "ORDER BY idTraining ASC";
    public static final String GET_ALL_TRAININGS_SORTED_BY_ID_DESC = "SELECT idTraining, title, level, city, " +
            "hours_amount, min_members, max_members, start_date, end_date, idUser " +
            "ORDER BY idTraining DESC";
    public static final String GET_ALL_TEAMS = "SELECT idTeam, people_amount, idTraining, idUser FROM teams";
    public static final String GET_USER_BY_EMAIL = "SELECT idUser FROM users WHERE email = ?";
    public static final String GET_USER_BY_USERNAME = "SELECT idUser FROM users WHERE username = ?";
    public static final String GET_ROWS_COUNT_TRAININGS = "SELECT COUNT(idTraining) FROM trainings";
    public static final String GET_ROWS_COUNT_USERS = "SELECT COUNT(idUser) FROM users";
    public static final String GET_ALL_NEWS = "SELECT idNews, news_intro, news_text, news_image, idUser FROM news";
    public static final String GET_ALL_USER_INFORMATION = "SELECT * FROM users INNER JOIN users_additional_info " +
            "ON users.idUser = users_additional_info.idUser WHERE users.idUser = ?";
    public static final String CHANGE_TRAINING_DELETED_STATUS = "UPDATE trainings SET deleted_status = ? WHERE idTraining = ?";
    public static final String UPDATE_TRAINING_VALUES = "UPDATE trainings SET title = ?, requirements = ?, information = ?," +
            " city = ?, hours_amount = ?, min_members = ?, max_members = ?, start_date = ?, end_date = ?, training_image = ?, " +
            " idUser = ? WHERE idTraining = ?";
    public static final String ADD_QUERY = "INSERT INTO queries (idTraining, idUser) VALUES (?, ?)";
    public static final String GET_ALL_QUERIES_BY_ID_USER = "SELECT queries.idQuery, queries.accepted_status, queries.idTraining, queries.idUser, queries.canceled_status, " +
            "trainings.title, trainings.start_date FROM queries INNER JOIN trainings ON queries.idTraining = trainings.idTraining " +
            "AND queries.idUser = ?";
    public static final String CHANGE_CANCELED_STATUS = "UPDATE queries SET canceled_status = true WHERE idQuery = ?";
    public static final String GET_QUERY_BY_ID_TRAINING_ID_USER = "SELECT idQuery, idUser, canceled_status, idTraining FROM queries WHERE idTraining = ? AND idUser = ? AND canceled_status = false";
    public static final String ADD_TRAINING_LIKE = "INSERT INTO likes (enabled_status, idUser, idTraining) VALUES (true, ?, ?)";
    public static final String GET_TRAINING_LIKES = "SELECT COUNT(*) AS total FROM likes WHERE idTraining = ? AND enabled_status = true";
    public static final String ADD_TRAINING_DISLIKE = "INSERT INTO dislikes (enabled_status, idUser, idTraining) VALUES (true, ?, ?)";
    public static final String GET_TRAINING_DISLIKES = "SELECT COUNT(*) AS total FROM dislikes WHERE idTraining = ? AND enabled_status = true";
    public static final String GET_ALL_TRAININGS_BY_ID_TRAINER = "SELECT idTraining, title, requirements, information, deleted_status, " +
            "city, hours_amount, min_members, max_members, start_date, end_date, training_image, idUser FROM trainings WHERE idUser = ?";
    public static final String GET_DISLIKE_ENABLED_STATUS = "SELECT enabled_status FROM dislikes WHERE idTraining = ? AND idUser = ?";
    public static final String GET_LIKE_ENABLED_STATUS = "SELECT enabled_status FROM likes WHERE idTraining = ? AND idUser = ?";
    public static final String CHANGE_DISLIKE_ENABLED_STATUS = "UPDATE dislikes SET enabled_status = false WHERE idTraining = ? AND idUser = ?";
    public static final String CHANGE_LIKE_ENABLED_STATUS = "UPDATE likes SET enabled_status = false WHERE idTraining = ? AND idUser = ?";
    public static final String GET_TRAINING_QUERIES = "SELECT queries.idQuery, queries.idTraining, queries.accepted_status, users.username, trainings.title, trainings.start_date FROM queries JOIN trainings ON queries.idTraining = trainings.idTraining JOIN users ON queries.idUser = users.idUser WHERE queries.accepted_status = 0 AND queries.canceled_status = false AND queries.idTraining = ?";
    public static final String CHANGE_ACCEPTED_STATUS = "UPDATE queries SET accepted_status = ? WHERE idQuery = ?";
}
