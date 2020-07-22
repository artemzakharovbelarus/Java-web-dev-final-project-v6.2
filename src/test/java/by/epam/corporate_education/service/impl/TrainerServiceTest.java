package by.epam.corporate_education.service.impl;

import by.epam.corporate_education.dao.api.QueryDAO;
import by.epam.corporate_education.dao.api.TrainingDAO;
import by.epam.corporate_education.dao.exception.DAOException;
import by.epam.corporate_education.dao.impl.QueryDAOImpl;
import by.epam.corporate_education.dao.impl.TrainingDAOImpl;
import by.epam.corporate_education.entity.Query;
import by.epam.corporate_education.service.exception.ServiceException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.*;

public class TrainerServiceTest {

    TrainerServiceImpl trainerService = new TrainerServiceImpl();
    QueryDAO queryDAO = Mockito.mock(QueryDAOImpl.class);
    TrainingDAO trainingDAO = Mockito.mock(TrainingDAOImpl.class);

    @Before
    public void init(){
        trainerService.setQueryDAO(queryDAO);
        trainerService.setTrainingDAO(trainingDAO);
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
}