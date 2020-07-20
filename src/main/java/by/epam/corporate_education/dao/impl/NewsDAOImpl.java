package by.epam.corporate_education.dao.impl;

import by.epam.corporate_education.dao.SQLRequest;
import by.epam.corporate_education.dao.api.NewsDAO;
import by.epam.corporate_education.dao.exception.DAOException;
import by.epam.corporate_education.dao.pool.DBConnectionPool;
import by.epam.corporate_education.entity.NewsItem;
import by.epam.corporate_education.entity.Training;
import lombok.extern.log4j.Log4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class NewsDAOImpl implements NewsDAO {
    private DBConnectionPool connectionPool = DBConnectionPool.getINSTANCE();

    @Override
    public List<NewsItem> getAllNews() throws DAOException {
        String request = SQLRequest.GET_ALL_NEWS;
        List<NewsItem> news = new ArrayList<>();

        ResultSet resultSet = null;
        try(Connection connection = connectionPool.takeConnection();
            Statement statement = connection.createStatement()){

            resultSet = statement.executeQuery(request);
            while (resultSet.next()){
                NewsItem newsItem = getNextNews(resultSet);
                news.add(newsItem);
            }
        } catch (SQLException e){
            throw new DAOException(e);
        } finally {
            try{
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e){
                log.error(e);
            }
        }
        return news;
    }

    private NewsItem getNextNews(ResultSet resultSet) throws SQLException {
        int idNews = resultSet.getInt(1);
        String newsIntro = resultSet.getString(2);
        String newsText = resultSet.getString(3);
        String newsImage = resultSet.getString(4);
        int idAdmin = resultSet.getInt(5);

        NewsItem newsItem = new NewsItem(idNews, newsIntro, newsText, newsImage, idAdmin);
        return newsItem;
    }
}
