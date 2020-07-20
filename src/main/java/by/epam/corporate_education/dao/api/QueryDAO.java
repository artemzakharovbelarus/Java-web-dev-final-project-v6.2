package by.epam.corporate_education.dao.api;

import by.epam.corporate_education.dao.exception.DAOException;
import by.epam.corporate_education.entity.Query;

import java.util.List;

public interface QueryDAO {
    public void addQuery(Query query) throws DAOException;
    public List<Query> getAllQueries(int idUser) throws DAOException;
    public void changeCanceledStatus(int idQuery) throws DAOException;
    public Query getQueryByIdTrainingIdUser(int idTraining, int idUser) throws DAOException;
    public List<Query> getTrainingQueries(int idTraining) throws DAOException;
}
