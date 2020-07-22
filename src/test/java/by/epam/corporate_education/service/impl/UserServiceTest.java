package by.epam.corporate_education.service.impl;

import by.epam.corporate_education.dao.api.*;
import by.epam.corporate_education.dao.exception.DAOException;
import by.epam.corporate_education.dao.exception.DBPoolException;
import by.epam.corporate_education.dao.impl.*;
import by.epam.corporate_education.dao.pool.DBConnectionPool;
import by.epam.corporate_education.entity.Training;
import by.epam.corporate_education.service.api.UserService;
import by.epam.corporate_education.service.exception.ServiceException;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserServiceTest {

    DBConnectionPool connectionPool = DBConnectionPool.getINSTANCE();
    UserService userService = new UserServiceImpl();
    LikeDAO likeDAO;
    DislikeDAO dislikeDAO;
    QueryDAO queryDAO;
    UserDAO userDAO;
    TrainingDAO trainingDAO;
    NewsDAO newsDAO;

    @Before
    public void init() throws DBPoolException {
        likeDAO = Mockito.mock(LikeDAOImpl.class);
        dislikeDAO = Mockito.mock(DislikeDAOImpl.class);
        queryDAO = Mockito.mock(QueryDAOImpl.class);
        userDAO = Mockito.mock(UserDAOImpl.class);
        trainingDAO = Mockito.mock(TrainingDAOImpl.class);
        newsDAO = Mockito.mock(NewsDAOImpl.class);
        connectionPool.init();
    }


    @After
    public void destroy(){
        connectionPool.destroy();
    }
}