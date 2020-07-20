package by.epam.corporate_education.dao.api;

import by.epam.corporate_education.dao.exception.DAOException;
import by.epam.corporate_education.entity.Training;

import java.util.List;

public interface TrainingDAO {

    public List<Training> getAllTrainings() throws DAOException;
    public List<Training> getAllTrainingsSorted(String parameter, String typeSorting) throws DAOException;
    public void addTraining(Training training) throws DAOException;
    public int changeDeletedStatus(int idTraining) throws DAOException;
    public int getRowsAmount() throws DAOException;
    public Training getTraining(int idTraining) throws DAOException;
    public void updateTraining(Training training) throws DAOException;
    public List<Training> getAllTrainingsByIdTrainer(int idTrainer) throws DAOException;
}
