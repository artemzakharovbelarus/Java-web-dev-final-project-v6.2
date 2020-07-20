package by.epam.corporate_education.service.api;

import by.epam.corporate_education.entity.Query;
import by.epam.corporate_education.entity.Training;
import by.epam.corporate_education.service.exception.ServiceException;

import java.util.List;

public interface TrainerService {
    public List<Training> getAllTrainerTrainings(int idTrainer) throws ServiceException;
    public List<Query> getTrainingQueries(int idTraining) throws ServiceException;
}
