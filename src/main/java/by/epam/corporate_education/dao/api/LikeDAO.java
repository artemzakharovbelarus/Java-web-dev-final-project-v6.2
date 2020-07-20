package by.epam.corporate_education.dao.api;

import by.epam.corporate_education.dao.exception.DAOException;
import by.epam.corporate_education.entity.Like;

public interface LikeDAO {
    public void addTrainingLike(Like like) throws DAOException;
    public int getTrainingLikesAmount(int idTraining) throws DAOException;
    public boolean getLikeEnabledStatus(Like like) throws DAOException;
    public void changeEnabledStatus(Like like) throws DAOException;
}
