package by.epam.corporate_education.dao.api;

import by.epam.corporate_education.dao.exception.DAOException;
import by.epam.corporate_education.entity.NewsItem;

import java.util.List;

public interface NewsDAO {
    public List<NewsItem> getAllNews() throws DAOException;
}
