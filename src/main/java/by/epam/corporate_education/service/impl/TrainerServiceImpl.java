package by.epam.corporate_education.service.impl;

import by.epam.corporate_education.dao.DAOFactory;
import by.epam.corporate_education.dao.api.QueryDAO;
import by.epam.corporate_education.dao.api.TrainingDAO;
import by.epam.corporate_education.dao.exception.DAOException;
import by.epam.corporate_education.entity.Query;
import by.epam.corporate_education.entity.Training;
import by.epam.corporate_education.service.api.TrainerService;
import by.epam.corporate_education.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class TrainerServiceImpl implements TrainerService {
    private DAOFactory daoFactory = DAOFactory.getINSTANCE();

    @Override
    public List<Query> getTrainingQueries(int idTraining) throws ServiceException {
        QueryDAO queryDAO = daoFactory.getQueryDAOImpl();

        List<Query> queries = new ArrayList<>();
        try{
            queries = queryDAO.getTrainingQueries(idTraining);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
        return queries;
    }

    @Override
    public List<Training> getAllTrainerTrainings(int idTrainer) throws ServiceException {
        TrainingDAO trainingDAO = daoFactory.getTrainingDAOImpl();

        List<Training> trainings = new ArrayList<>();
        try{

            trainings = trainingDAO.getAllTrainingsByIdTrainer(idTrainer);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
        return trainings;
    }
}
