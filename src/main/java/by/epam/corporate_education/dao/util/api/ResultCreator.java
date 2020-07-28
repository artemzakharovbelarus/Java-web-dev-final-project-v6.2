package by.epam.corporate_education.dao.util.api;

import by.epam.corporate_education.entity.NewsItem;
import by.epam.corporate_education.entity.Query;
import by.epam.corporate_education.entity.Training;
import by.epam.corporate_education.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultCreator {
    public Training getNextTraining(ResultSet resultSet) throws SQLException;
    public User getNextUser(ResultSet resultSet) throws SQLException;
    public User getFullUser(ResultSet resultSet) throws SQLException;
    public Query getQuery(ResultSet resultSet) throws SQLException;
    public Query getFullQueryInfo(ResultSet resultSet) throws SQLException;
    public int getLikesAmount(ResultSet resultSet) throws SQLException;
    public int getDislikesAmount(ResultSet resultSet) throws SQLException;
    public boolean getEnabledStatus(ResultSet resultSet) throws SQLException;
    public Query getFullQueryToTraining(ResultSet resultSet) throws SQLException;
    public NewsItem getNextNews(ResultSet resultSet) throws SQLException;
}
