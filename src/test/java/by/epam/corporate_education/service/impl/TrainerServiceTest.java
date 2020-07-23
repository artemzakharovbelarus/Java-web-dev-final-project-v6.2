package by.epam.corporate_education.service.impl;

import by.epam.corporate_education.dao.api.QueryDAO;
import by.epam.corporate_education.dao.api.TrainingDAO;
import by.epam.corporate_education.dao.exception.DAOException;
import by.epam.corporate_education.dao.impl.QueryDAOImpl;
import by.epam.corporate_education.dao.impl.TrainingDAOImpl;
import by.epam.corporate_education.entity.Query;
import by.epam.corporate_education.entity.Training;
import by.epam.corporate_education.service.exception.ServiceException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.*;

public class TrainerServiceTest {

    TrainerServiceImpl trainerService;
    QueryDAO queryDAO = Mockito.mock(QueryDAOImpl.class);
    TrainingDAO trainingDAO = Mockito.mock(TrainingDAOImpl.class);

    @Before
    public void init(){
        trainerService = new TrainerServiceImpl(queryDAO, trainingDAO);
    }

    @Test(expected = ServiceException.class)
    public void setQueryAnswer_DAOExceptionFromChangeAcceptedStatus() throws DAOException, ServiceException {
        //given
        //when
        Mockito.doThrow(DAOException.class).when(queryDAO).changeAcceptedStatus(Mockito.anyInt(), Mockito.anyInt());
        int idQuery = 1;
        int answer = 1;
        trainerService.setQueryAnswer(idQuery, answer);
        //then
        //expected ServiceException
    }

    @Test
    public void setQueryAnswer_validParametersIdQueRyIdAnswer_void_correct() throws DAOException, ServiceException {
        //given
        Mockito.doNothing().when(queryDAO).changeAcceptedStatus(Mockito.anyInt(), Mockito.anyInt());
        int idQuery = 1;
        int answer = 1;
        trainerService.setQueryAnswer(idQuery, answer);
        //then
    }

    @Test(expected = ServiceException.class)
    public void getTrainingQueries_DAOExceptionFromGetTrainingQueries_ServiceException()
            throws DAOException, ServiceException {
        //given
        //when
        Mockito.doThrow(DAOException.class).when(queryDAO).getTrainingQueries(Mockito.anyInt());
        trainerService.getTrainingQueries(Mockito.anyInt());
        //then
        //expected ServiceException
    }

    @Test
    public void getTrainingQueries_validParameterIdTraining_correct() throws DAOException, ServiceException {
        //given
        List<Query> queries = (List<Query>) Mockito.mock(List.class);
        Mockito.doReturn(queries).when(queryDAO).getTrainingQueries(Mockito.anyInt());
        List<Query> result = trainerService.getTrainingQueries(Mockito.anyInt());
        //then
        assertEquals(result, queries);
    }

    @Test(expected = ServiceException.class)
    public void getAllTrainerTrainings_DAOExceptionFromGetAllTrainingsByIdTrainer_ServiceException()
            throws DAOException, ServiceException {
        //given
        //when
        Mockito.doThrow(DAOException.class).when(trainingDAO).getAllTrainingsByIdTrainer(Mockito.anyInt());
        trainerService.getAllTrainerTrainings(Mockito.anyInt());
        //then
        //excepted ServiceException
    }

    @Test
    public void getAllTrainerTraining_validParameterIdTrainer_correct() throws DAOException, ServiceException {
        //given
        List<Training> trainings = (List<Training>) Mockito.mock(List.class);
        Mockito.doReturn(trainings).when(trainingDAO).getAllTrainingsByIdTrainer(Mockito.anyInt());
        List<Training> result = trainerService.getAllTrainerTrainings(Mockito.anyInt());
        //then
        assertEquals(result, trainings);
    }


}