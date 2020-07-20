package by.epam.corporate_education.dao.api;

import by.epam.corporate_education.dao.exception.DAOException;
import by.epam.corporate_education.entity.Dislike;

public interface DislikeDAO {
    public void addTrainingDislike(Dislike dislike) throws DAOException;
    public int getTrainingDislikesAmount(int idTraining) throws DAOException;
    public boolean getDislikeEnabledStatus(Dislike dislike) throws DAOException;
    public void changeEnabledStatus(Dislike dislike) throws DAOException;
}
