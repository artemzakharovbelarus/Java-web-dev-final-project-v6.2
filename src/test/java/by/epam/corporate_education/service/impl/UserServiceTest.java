package by.epam.corporate_education.service.impl;

import by.epam.corporate_education.dao.api.*;
import by.epam.corporate_education.dao.exception.DAOException;
import by.epam.corporate_education.dao.exception.UsedEmailException;
import by.epam.corporate_education.dao.exception.UsedUsernameException;
import by.epam.corporate_education.dao.impl.*;
import by.epam.corporate_education.entity.*;
import by.epam.corporate_education.service.exception.*;
import by.epam.corporate_education.service.util.ServiceUtilFactory;
import by.epam.corporate_education.service.util.ValidatorManager;
import by.epam.corporate_education.service.util.api.UserValidator;
import by.epam.corporate_education.service.util.impl.UserValidatorImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.*;

public class UserServiceTest {

    UserValidator userValidator = Mockito.mock(UserValidatorImpl.class);
    ValidatorManager validatorManager = ValidatorManager.getINSTANCE();
    ServiceUtilFactory serviceUtilFactory = ServiceUtilFactory.getINSTANCE();

    UserServiceImpl userService;

    LikeDAO likeDAO = Mockito.mock(LikeDAOImpl.class);
    DislikeDAO dislikeDAO = Mockito.mock(DislikeDAO.class);
    QueryDAO queryDAO = Mockito.mock(QueryDAOImpl.class);
    UserDAO userDAO = Mockito.mock(UserDAOImpl.class);
    NewsDAO newsDAO = Mockito.mock(NewsDAOImpl.class);
    TrainingDAO trainingDAO = Mockito.mock(TrainingDAOImpl.class);

    @Before
    public void init(){
        validatorManager.setUserValidator(userValidator);
        serviceUtilFactory.setValidatorManager(validatorManager);
        userService = new UserServiceImpl(serviceUtilFactory, likeDAO, dislikeDAO,
                                          queryDAO, userDAO, newsDAO, trainingDAO);
    }

    @Test(expected = ServiceException.class)
    public void signIn_invalidParameters_ServiceException() throws DAOException, ServiceException {
        //given
        //when
        Mockito.doThrow(DAOException.class).when(userDAO).getUser(Mockito.anyString(), Mockito.anyString());
        String username = "Username";
        String password = "Password";
        userService.signIn(username,password);
        //then
        //expecting ServiceException
    }

    @Test(expected = UserBannedException.class)
    public void signIn_bannedUser_UserBannedException() throws ServiceException, DAOException {
        //given
        User user = Mockito.mock(User.class);
        //when
        Mockito.doReturn(user).when(userDAO).getUser(Mockito.anyString(), Mockito.anyString());
        Mockito.when(user.isBannedStatus()).thenReturn(true);
        String username = "Username";
        String password = "Password";
        userService.signIn(username,password);
        //then
        //expecting UserBannedException
    }

    @Test
    public void signIn_validParameters_correct() throws ServiceException, DAOException {
        //given
        User user = Mockito.mock(User.class);
        //when
        Mockito.doReturn(user).when(userDAO).getUser(Mockito.anyString(), Mockito.anyString());
        Mockito.when(user.isBannedStatus()).thenReturn(false);
        String username = "Username";
        String password = "Password";
        User result = userService.signIn(username,password);
        //then
        assertEquals(result, user);
    }

    @Test(expected = InvalidUsernameException.class)
    public void signUp_invalidUsername_InvalidUsernameException()
            throws ServiceException {
        //given
        //when
        Mockito.doThrow(InvalidUsernameException.class).when(userValidator)
                .validateSignUp(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        String username = "qwe";
        String email = "temax359x@gmail.com";
        String password = "QWE123QW";
        String confirmedPassword = "QWE123QW";
        userService.signUp(username, email, password, confirmedPassword);
        //then
        //expected InvalidUsernameException
    }

    @Test(expected = InvalidEmailException.class)
    public void signUp_invalidEmail_InvalidEmailException()
            throws ServiceException {
        //given
        //when
        Mockito.doThrow(InvalidEmailException.class).when(userValidator)
                .validateSignUp(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        String username = "ArtemZakharovBY";
        String email = "temax359x@gmail,com";
        String password = "QWE123QW";
        String confirmedPassword = "QWE123QW";
        userService.signUp(username, email, password, confirmedPassword);
        //then
        //expected InvalidEmailException
    }

    @Test(expected = InvalidPasswordException.class)
    public void signUp_invalidPassword_InvalidPasswordException()
            throws ServiceException {
        //given
        //when
        Mockito.doThrow(InvalidPasswordException.class).when(userValidator)
                .validateSignUp(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        String username = "ArtemZakharovBY";
        String email = "temax359x@gmail.com";
        String password = "QWE";
        String confirmedPassword = "QWE";
        userService.signUp(username, email, password, confirmedPassword);
        //then
        //expected InvalidPasswordException
    }

    @Test(expected = EquivalencePasswordException.class)
    public void signUp_notEqualsPasswords_EquivalencePasswordException()
            throws ServiceException {
        //given
        //when
        Mockito.doThrow(EquivalencePasswordException.class).when(userValidator)
                .validateSignUp(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        String username = "ArtemZakharovBY";
        String email = "temax359x@gmail.com";
        String password = "QWE123QW";
        String confirmedPassword = "QWE123";
        userService.signUp(username, email, password, confirmedPassword);
        //then
        //expected EquivalencePasswordException
    }

    @Test(expected = ServiceException.class)
    public void signUp_DAOExceptionFromAddNewUser_ServiceException()
            throws ServiceException, DAOException {
        //given
        //when
        Mockito.doThrow(DAOException.class).when(userDAO)
                .addNewUser(Mockito.any(User.class), Mockito.anyString());
        String username = "ArtemZakharovBY";
        String email = "temax359x@gmail.com";
        String password = "QWE123QW";
        String confirmedPassword = "QWE123";
        userService.signUp(username, email, password, confirmedPassword);
        //then
        //expected ServiceException
    }

    @Test(expected = InvalidUsernameException.class)
    public void signUp_UsedUsernameExceptionFromAddNewUser_InvalidUsernameException()
            throws ServiceException, DAOException {
        //given
        //when
        Mockito.doThrow(UsedUsernameException.class).when(userDAO)
                .addNewUser(Mockito.any(User.class), Mockito.anyString());
        String username = "ArtemZakharovBY";
        String email = "temax359x@gmail.com";
        String password = "QWE123QW";
        String confirmedPassword = "QWE123";
        userService.signUp(username, email, password, confirmedPassword);
        //then
        //expected InvalidUsernameException
    }

    @Test(expected = InvalidEmailException.class)
    public void signUp_UsedEmailExceptionFromAddNewUser_InvalidEmailException()
            throws ServiceException, DAOException {
        //given
        //when
        Mockito.doThrow(UsedEmailException.class).when(userDAO)
                .addNewUser(Mockito.any(User.class), Mockito.anyString());
        String username = "ArtemZakharovBY";
        String email = "temax359x@gmail.com";
        String password = "QWE123QW";
        String confirmedPassword = "QWE123";
        userService.signUp(username, email, password, confirmedPassword);
        //then
        //expected InvalidEmailException
    }

    @Test
    public void signUp_validParameters_correct()
            throws ServiceException, DAOException {
        //given
        //when
        Mockito.doNothing().when(userValidator)
                .validateSignUp(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        Mockito.when(userDAO.addNewUser(Mockito.any(User.class), Mockito.anyString())).thenReturn(1);

        String username = "ArtemZakharovBY";
        String email = "temax359x@gmail.com";
        String password = "QWE123QW";
        String confirmedPassword = "QWE123QW";
        int result = userService.signUp(username, email, password, confirmedPassword);
        //then
        assertEquals(1, result);
    }

    @Test(expected = ServiceException.class)
    public void viewAllTrainings_DAOExceptionFromGetAllTrainings_ServiceException() throws DAOException, ServiceException {
        //given
        //when
        Mockito.doThrow(DAOException.class).when(trainingDAO).getAllTrainings();
        userService.viewAllTrainings();
        //then
        //expected ServiceException
    }

    @Test
    public void viewAllTrainings_validParametersList_correct() throws DAOException, ServiceException {
        //given
        List<Training> trainings = (List<Training>) Mockito.mock(List.class);
        //when
        Mockito.doReturn(trainings).when(trainingDAO).getAllTrainings();
        List<Training> result = userService.viewAllTrainings();
        //then
        assertEquals(result, trainings);
    }

    @Test(expected = ServiceException.class)
    public void viewAllNews_DAOExceptionFromGetAllNews_ServiceException() throws DAOException, ServiceException {
        //given
        //when
        Mockito.doThrow(DAOException.class).when(newsDAO).getAllNews();
        userService.viewAllNews();
        //then
        //expected ServiceException
    }

    @Test
    public void viewAllNews_validParametersList_correct() throws DAOException, ServiceException {
        //given
        List<NewsItem> news = (List<NewsItem>) Mockito.mock(List.class);
        //when
        Mockito.doReturn(news).when(newsDAO).getAllNews();
        List<NewsItem> result = userService.viewAllNews();
        //then
        assertEquals(result, news);
    }

    @Test(expected = ServiceException.class)
    public void getTraining_DAOExceptionFromGetTrainingById_ServiceException() throws DAOException, ServiceException {
        //given
        //when
        Mockito.doThrow(DAOException.class).when(trainingDAO).getTraining(Mockito.anyInt());
        userService.getTraining(Mockito.anyInt());
        //then
        //expected ServiceException
    }

    @Test
    public void getTraining_validParameterId_correct() throws DAOException, ServiceException {
        //given
        Training training = Mockito.mock(Training.class);
        //when
        Mockito.doReturn(training).when(trainingDAO).getTraining(Mockito.anyInt());
        Training result = userService.getTraining(Mockito.anyInt());
        //then
        assertEquals(result, training);
    }

    @Test(expected = ServiceException.class)
    public void signOut_DAOExceptionFromChangeOnlineStatus_ServiceException() throws DAOException, ServiceException {
        //given
        //when
        Mockito.doThrow(DAOException.class).when(userDAO).changeOnlineStatus(Mockito.anyInt(), Mockito.anyBoolean());
        userService.signOut(Mockito.anyInt(), Mockito.anyBoolean());
        //then
        //expected ServiceException
    }

    @Test
    public void signOut_validParametersIdAndStatus_void_correct() throws DAOException, ServiceException {
        //given
        Mockito.doNothing().when(userDAO).changeOnlineStatus(Mockito.anyInt(), Mockito.anyBoolean());
        userService.signOut(Mockito.anyInt(), Mockito.anyBoolean());
        //then
    }

    @Test(expected = ServiceException.class)
    public void writeQuery_DAOExceptionFromAddNewQuery_ServiceException() throws DAOException, ServiceException {
        //given
        //when
        Mockito.doThrow(DAOException.class).when(queryDAO).addQuery(Mockito.any(Query.class));
        int idTraining = 1;
        int idUser = 1;
        userService.writeQuery(idTraining, idUser);
        //then
        //expected ServiceException
    }

    @Test
    public void writeQuery_validParameterIdUserAndTraining_void_correct() throws DAOException, ServiceException {
        //given
        //when
        Mockito.doNothing().when(queryDAO).addQuery(Mockito.mock(Query.class));
        int idTraining = 1;
        int idUser = 1;
        userService.writeQuery(idTraining, idUser);
        //then
    }

    @Test(expected = ServiceException.class)
    public void viewAllQueries_DAOExceptionFromGetAllQueries_ServiceException() throws DAOException, ServiceException {
        //given
        //when
        Mockito.doThrow(DAOException.class).when(queryDAO).getAllQueries(Mockito.anyInt());
        userService.viewAllQueries(Mockito.anyInt());
        //then
        //expected ServiceException
    }

    @Test
    public void viewAllQueries_validParameterIdUser_Correct() throws DAOException, ServiceException {
        //given
        List<Query> queries = (List<Query>) (Mockito.mock(List.class));
        //when
        Mockito.doReturn(queries).when(queryDAO).getAllQueries(Mockito.anyInt());
        int idUser = 1;
        List<Query> result = userService.viewAllQueries(idUser);
        //then
        assertEquals(result, queries);
    }

    @Test(expected = ServiceException.class)
    public void undoQuery_DAOExceptionFromChangeQueryCanceledStatus_ServiceException() throws DAOException, ServiceException {
        //given
        //when
        Mockito.doThrow(DAOException.class).when(queryDAO).changeCanceledStatus(Mockito.anyInt());
        userService.undoQuery(Mockito.anyInt());
        //then
        //expected ServiceException
    }

    @Test
    public void undoQuery_validParameterIdQuery_void_correct() throws DAOException, ServiceException {
        //given
        Mockito.doNothing().when(queryDAO).changeCanceledStatus(Mockito.anyInt());
        int idQuery = 1;
        userService.undoQuery(idQuery);
        //then
    }

    @Test(expected = ServiceException.class)
    public void getQueryByIdUserIdTraining_DAOExceptionFromGetQueryByIdUserIdTraining_ServiceException()
            throws DAOException, ServiceException {
        //given
        //when
        Mockito.doThrow(DAOException.class).when(queryDAO).getQueryByIdTrainingIdUser(Mockito.anyInt(), Mockito.anyInt());
        userService.getQueryByIdTrainingIdUser(Mockito.anyInt(), Mockito.anyInt());
        //then
        //expected ServiceException
    }

    @Test
    public void getQueryByIdUserIdTraining_validParametersIdUserIdTraining_correct() throws DAOException, ServiceException {
        //given
        Query query = Mockito.mock(Query.class);
        Mockito.doReturn(query).when(queryDAO).getQueryByIdTrainingIdUser(Mockito.anyInt(), Mockito.anyInt());
        int idTraining = 1;
        int idUser = 1;
        Query result = userService.getQueryByIdTrainingIdUser(idTraining, idUser);
        //then
        assertEquals(result, query);
    }

    @Test(expected = ServiceException.class)
    public void addTrainingLike_DAOExceptionFromAddTrainingLike_ServiceException() throws DAOException, ServiceException {
        //given
        //when
        Mockito.doThrow(DAOException.class).when(likeDAO).addTrainingLike(Mockito.any(Like.class));
        int idTraining = 1;
        int idUser = 1;
        userService.addTrainingLike(idTraining, idUser);
        //then
        //expected ServiceException
    }

    @Test
    public void addTrainingLike_validParameters_void_correct() throws DAOException, ServiceException {
        //given
        Mockito.doNothing().when(likeDAO).addTrainingLike(Mockito.mock(Like.class));
        int idTraining = 1;
        int idUser = 1;
        userService.addTrainingLike(idTraining, idUser);
        //then
    }

    @Test(expected = ServiceException.class)
    public void addTrainingDislike_DAOExceptionFromAddTrainingDislike_ServiceException()
            throws DAOException, ServiceException {
        //given
        //when
        Mockito.doThrow(DAOException.class).when(dislikeDAO).addTrainingDislike(Mockito.any(Dislike.class));
        int idTraining = 1;
        int idUser = 1;
        userService.addTrainingDislike(idTraining, idUser);
        //then
        //expected ServiceException
    }

    @Test
    public void addTrainingDislike_validParameters_void_correct() throws DAOException, ServiceException {
        //given
        Mockito.doNothing().when(dislikeDAO).addTrainingDislike(Mockito.mock(Dislike.class));
        int idTraining = 1;
        int idUser = 1;
        userService.addTrainingDislike(idTraining, idUser);
        //then
    }

    @Test(expected = ServiceException.class)
    public void isLikeEnabled_DAOExceptionFromGetLikeEnabledStatus_ServiceException()
            throws DAOException, ServiceException {
        //given
        //when
        Mockito.doThrow(DAOException.class).when(likeDAO).getLikeEnabledStatus(Mockito.any(Like.class));
        int idTraining = 1;
        int idUser = 1;
        userService.isLikeEnabled(idTraining, idUser);
        //then
        //expected ServiceException
    }

    @Test
    public void isLikeEnabled_validParametersIdUserIdTraining_correct() throws DAOException, ServiceException {
        //given
        boolean isEnabled = false;
        Mockito.doReturn(isEnabled).when(likeDAO).getLikeEnabledStatus(Mockito.mock(Like.class));
        int idTraining = 1;
        int idUser = 1;
        boolean result = userService.isLikeEnabled(idTraining, idUser);
        //then
        assertEquals(result, isEnabled);
    }

    @Test(expected = ServiceException.class)
    public void isDislikeEnabled_DAOExceptionFromGetDislikeEnabledStatus_ServiceException()
            throws DAOException, ServiceException {
        //given
        //when
        Mockito.doThrow(DAOException.class).when(dislikeDAO).getDislikeEnabledStatus(Mockito.any(Dislike.class));
        int idTraining = 1;
        int idUser = 1;
        userService.isDislikeEnabled(idTraining, idUser);
        //then
        //expected ServiceException
    }

    @Test
    public void isDislikeEnabled_validParametersIdUserIdTraining_correct() throws DAOException, ServiceException {
        //given
        boolean isEnabled = false;
        Mockito.doReturn(isEnabled).when(dislikeDAO).getDislikeEnabledStatus(Mockito.mock(Dislike.class));
        int idTraining = 1;
        int idUser = 1;
        boolean result = userService.isDislikeEnabled(idTraining, idUser);
        //then
        assertEquals(result, isEnabled);
    }

    @Test(expected = ServiceException.class)
    public void putOffLike_DAOExceptionFromChangeEnabledStatus_ServiceException() throws DAOException, ServiceException {
        //given
        //when
        Mockito.doThrow(DAOException.class).when(likeDAO).changeEnabledStatus(Mockito.any(Like.class));
        int idTraining = 1;
        int idUser = 1;
        userService.putOffLike(idTraining, idUser);
        //then
        //expected ServiceException
    }

    @Test
    public void putOffLike_validParametersIdTrainingIdUser_void_correct() throws DAOException, ServiceException {
        //given
        Mockito.doNothing().when(likeDAO).changeEnabledStatus(Mockito.mock(Like.class));
        int idTraining = 1;
        int idUser = 1;
        userService.putOffLike(idTraining, idUser);
        //then
        //expected
    }

    @Test(expected = ServiceException.class)
    public void putOffDislike_DAOExceptionFromChangeEnabledStatus_ServiceException() throws DAOException, ServiceException {
        //given
        //when
        Mockito.doThrow(DAOException.class).when(dislikeDAO).changeEnabledStatus(Mockito.any(Dislike.class));
        int idTraining = 1;
        int idUser = 1;
        userService.putOffDislike(idTraining, idUser);
        //then
        //expected ServiceException
    }

    @Test
    public void putOffDislike_validParametersIdTrainingIdUser_void_correct() throws DAOException, ServiceException {
        //given
        Mockito.doNothing().when(dislikeDAO).changeEnabledStatus(Mockito.mock(Dislike.class));
        int idTraining = 1;
        int idUser = 1;
        userService.putOffDislike(idTraining, idUser);
        //then
        //expected
    }
}