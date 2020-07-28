package by.epam.corporate_education.dao.impl;

import by.epam.corporate_education.dao.SQLRequest;
import by.epam.corporate_education.dao.api.NewsDAO;
import by.epam.corporate_education.dao.exception.DAOException;
import by.epam.corporate_education.dao.pool.DBConnectionPool;
import by.epam.corporate_education.dao.util.DAOUtilFactory;
import by.epam.corporate_education.dao.util.api.ResourceCloser;
import by.epam.corporate_education.dao.util.api.ResultCreator;
import by.epam.corporate_education.entity.NewsItem;
import by.epam.corporate_education.entity.Training;
import lombok.extern.log4j.Log4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class NewsDAOImpl implements NewsDAO {
    private DBConnectionPool connectionPool = DBConnectionPool.getINSTANCE();
    private DAOUtilFactory utilFactory = DAOUtilFactory.getINSTANCE();
    private ResourceCloser resourceCloser = utilFactory.getResourceCloser();
    private ResultCreator resultCreator = utilFactory.getResultCreator();

    @Override
    public List<NewsItem> getAllNews() throws DAOException {
        String request = SQLRequest.GET_ALL_NEWS;
        List<NewsItem> news = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(request);

            resultSet = statement.executeQuery(request);
            while (resultSet.next()){
                NewsItem newsItem = resultCreator.getNextNews(resultSet);
                news.add(newsItem);
            }
        } catch (SQLException e){
            throw new DAOException(e);
        } finally {
            resourceCloser.close(connection, statement, resultSet);
        }
        return news;
    }
}
