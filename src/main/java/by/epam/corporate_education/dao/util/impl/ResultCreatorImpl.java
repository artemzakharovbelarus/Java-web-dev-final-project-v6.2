package by.epam.corporate_education.dao.util.impl;

import by.epam.corporate_education.dao.util.ColumnName;
import by.epam.corporate_education.dao.util.TableName;
import by.epam.corporate_education.dao.util.api.ResultCreator;
import by.epam.corporate_education.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Base64;

public class ResultCreatorImpl implements ResultCreator {

    @Override
    public NewsItem getNextNews(ResultSet resultSet) throws SQLException {
        int idNews = resultSet.getInt(ColumnName.NEWS_ID_NEWS);
        String newsIntro = resultSet.getString(ColumnName.NEWS_INTRO_NEWS);
        String newsText = resultSet.getString(ColumnName.NEWS_TEXT_NEWS);
        String newsImage = resultSet.getString(ColumnName.NEWS_IMAGE_NEWS);
        int idAdmin = resultSet.getInt(ColumnName.NEWS_ID_USER);

        NewsItem newsItem = new NewsItem(idNews, newsIntro, newsText, newsImage, idAdmin);
        return newsItem;
    }

    @Override
    public Query getFullQueryToTraining(ResultSet resultSet) throws SQLException {
        int idQuery = resultSet.getInt(ColumnName.QUERY_ID_QUERY);
        String username = resultSet.getString(TableName.USERS + ColumnName.USER_USERNAME);
        String title = resultSet.getString(TableName.TRAININGS + ColumnName.TRAINING_TITLE);
        int acceptedStatus = resultSet.getInt(TableName.QUERIES + ColumnName.QUERY_ACCEPTED_STATUS);
        LocalDate startDate =
                resultSet.getDate(TableName.TRAININGS + ColumnName.TRAINING_START_DATE).toLocalDate();
        int idTraining = resultSet.getInt(TableName.QUERIES + ColumnName.TRAINING_ID_TRAINING);

        Query query = new Query(idQuery, username, title, acceptedStatus, startDate, idTraining);
        return query;
    }

    @Override
    public boolean getEnabledStatus(ResultSet resultSet) throws SQLException {
        boolean result = resultSet.getBoolean(ColumnName.ENABLED_STATUS);
        return result;
    }

    @Override
    public int getLikesAmount(ResultSet resultSet) throws SQLException {
        int likesAmount = resultSet.getInt(ColumnName.TOTAL);
        return likesAmount;
    }

    @Override
    public int getDislikesAmount(ResultSet resultSet) throws SQLException {
        int dislikesAmount = resultSet.getInt(ColumnName.TOTAL);
        return dislikesAmount;
    }

    @Override
    public Query getQuery(ResultSet resultSet) throws SQLException {
        int idQuery = resultSet.getInt(ColumnName.QUERY_ID_QUERY);
        boolean canceledStatus = resultSet.getBoolean(ColumnName.QUERY_CANCELED_STATUS);
        int idTraining = resultSet.getInt( ColumnName.TRAINING_ID_TRAINING);
        int idUser = resultSet.getInt(ColumnName.TRAINING_ID_USER);

        Query query = new Query(idQuery, idTraining, idUser, canceledStatus);
        return query;
    }

    @Override
    public Query getFullQueryInfo(ResultSet resultSet) throws SQLException {
        int idQuery = resultSet.getInt(TableName.QUERIES + ColumnName.QUERY_ID_QUERY);
        boolean canceledStatus = resultSet.getBoolean(TableName.QUERIES + ColumnName.QUERY_CANCELED_STATUS);
        int idTraining = resultSet.getInt(TableName.QUERIES + ColumnName.TRAINING_ID_TRAINING);
        int idUser = resultSet.getInt(TableName.QUERIES + ColumnName.TRAINING_ID_USER);
        String trainingTitle = resultSet.getString(TableName.TRAININGS +  ColumnName.TRAINING_TITLE);
        LocalDate startDate = resultSet.getDate(TableName.TRAININGS + ColumnName.TRAINING_START_DATE).toLocalDate();
        int acceptedStatus = resultSet.getInt(TableName.QUERIES + ColumnName.QUERY_ACCEPTED_STATUS);

        Query query = new Query(idQuery, idTraining, idUser, canceledStatus, trainingTitle, startDate, acceptedStatus);
        return query;
    }

    @Override
    public Training getNextTraining(ResultSet resultSet) throws SQLException {
        int idTraining = resultSet.getInt(ColumnName.TRAINING_ID_TRAINING);
        String title = resultSet.getString(ColumnName.TRAINING_TITLE);
        String requirements = resultSet.getString(ColumnName.TRAINING_REQUIREMENTS);
        String information = resultSet.getString(ColumnName.TRAINING_INFORMATION);
        boolean deletedStatus = resultSet.getBoolean(ColumnName.TRAINING_DELETED_STATUS);
        String city = resultSet.getString(ColumnName.TRAINING_CITY);
        int hoursAmount = resultSet.getInt(ColumnName.TRAINING_HOURS_AMOUNT);
        int minMembers = resultSet.getInt(ColumnName.TRAINING_MIN_MEMBERS);
        int maxMembers = resultSet.getInt(ColumnName.TRAINING_MAX_MEMBERS);
        LocalDate startDate = resultSet.getDate(ColumnName.TRAINING_START_DATE).toLocalDate();
        LocalDate endDate = resultSet.getDate(ColumnName.TRAINING_END_DATE).toLocalDate();
        byte[] imageArray = resultSet.getBytes(ColumnName.TRAINING_IMAGE);
        String img = encodeImage(imageArray);

        int idTrainer = resultSet.getInt(ColumnName.TRAINING_ID_USER);

        Training training = new Training(idTraining, title,
                requirements, information,
                deletedStatus, city,
                hoursAmount, minMembers,
                maxMembers, startDate,
                endDate, idTrainer, img);
        return training;
    }

    @Override
    public User getNextUser(ResultSet resultSet) throws SQLException {
        int idUser = resultSet.getInt(ColumnName.USER_ID_USER);
        String username = resultSet.getString(ColumnName.USER_USERNAME);
        String email = resultSet.getString(ColumnName.USER_EMAIL);
        boolean leaderStatus = resultSet.getBoolean(ColumnName.USER_LEADER_STATUS);
        boolean bannedStatus = resultSet.getBoolean(ColumnName.USER_BANNED_STATUS);
        boolean onlineStatus = resultSet.getBoolean(ColumnName.USER_ONLINE_STATUS);
        String userPhoto = resultSet.getString(ColumnName.USER_USER_PHOTO);
        int idStatus = resultSet.getInt(ColumnName.USER_ID_ROLE);
        UserStatus status = UserStatus.values()[idStatus-1];

        User user = new User(idUser, username,
                email, leaderStatus,
                onlineStatus, bannedStatus,
                status, userPhoto);
        return user;
    }

    @Override
    public User getFullUser(ResultSet resultSet) throws SQLException {
        User user = getNextUser(resultSet);
        user.setName(resultSet.getString(ColumnName.USER_INFO_NAME));
        user.setSurname(resultSet.getString(ColumnName.USER_INFO_SURNAME));
        user.setSex(resultSet.getInt(ColumnName.USER_INFO_SEX));
        user.setBirthDate(resultSet.getDate(ColumnName.USER_INFO_BIRTH_DATE).toLocalDate());
        user.setGithubLink(resultSet.getString(ColumnName.USER_INFO_GITHUB));
        user.setLinkedInLink(resultSet.getString(ColumnName.USER_INFO_LINKEDIN));

        return user;
    }

    private String encodeImage(byte[] imgArray){
        Base64.Encoder base64 = Base64.getEncoder();
        String img = "";
        if (imgArray != null) {
            img = base64.encodeToString(imgArray);
        }
        return img;
    }
}
