package by.epam.corporate_education.service.impl;

import by.epam.corporate_education.dao.DAOFactory;
import by.epam.corporate_education.dao.api.QueryDAO;
import by.epam.corporate_education.dao.api.TrainingDAO;
import by.epam.corporate_education.dao.exception.DAOException;
import by.epam.corporate_education.entity.Query;
import by.epam.corporate_education.entity.Training;
import by.epam.corporate_education.service.api.TrainerService;
import by.epam.corporate_education.service.exception.ServiceException;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class TrainerServiceImpl implements TrainerService {
    private DAOFactory daoFactory = DAOFactory.getINSTANCE();

    @Setter
    private QueryDAO queryDAO = daoFactory.getQueryDAOImpl();
    @Setter
    private TrainingDAO trainingDAO = daoFactory.getTrainingDAOImpl();

    @Override
    public void setQueryAnswer(int idQuery, int answer) throws ServiceException {
        try{
            queryDAO.changeAcceptedStatus(idQuery, answer);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Query> getTrainingQueries(int idTraining) throws ServiceException {
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
        List<Training> trainings = new ArrayList<>();
        try{

            trainings = trainingDAO.getAllTrainingsByIdTrainer(idTrainer);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
        return trainings;
    }


}
