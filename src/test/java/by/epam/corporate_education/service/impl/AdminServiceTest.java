package by.epam.corporate_education.service.impl;

import by.epam.corporate_education.dao.api.DislikeDAO;
import by.epam.corporate_education.dao.api.LikeDAO;
import by.epam.corporate_education.dao.api.TrainingDAO;
import by.epam.corporate_education.dao.api.UserDAO;
import by.epam.corporate_education.dao.exception.DAOException;
import by.epam.corporate_education.dao.impl.DislikeDAOImpl;
import by.epam.corporate_education.dao.impl.LikeDAOImpl;
import by.epam.corporate_education.dao.impl.TrainingDAOImpl;
import by.epam.corporate_education.dao.impl.UserDAOImpl;
import by.epam.corporate_education.entity.Training;
import by.epam.corporate_education.entity.User;
import by.epam.corporate_education.service.exception.ServiceException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class AdminServiceTest {

    AdminServiceImpl adminService;

    LikeDAO likeDAO = Mockito.mock(LikeDAOImpl.class);
    DislikeDAO dislikeDAO = Mockito.mock(DislikeDAOImpl.class);
    UserDAO userDAO = Mockito.mock(UserDAOImpl.class);
    TrainingDAO trainingDAO = Mockito.mock(TrainingDAOImpl.class);

    @Before
    public void init(){
        adminService = new AdminServiceImpl(likeDAO, dislikeDAO, userDAO, trainingDAO);
    }

    @Test(expected = ServiceException.class)
    public void getLikesAmount_DAOExceptionFromGetTrainingLikesAmount_ServiceException()
            throws DAOException, ServiceException {
        //given
        //when
        Mockito.doThrow(DAOException.class).when(likeDAO).getTrainingLikesAmount(Mockito.anyInt());
        adminService.getLikesAmount(Mockito.anyInt());
        //then
        //expected ServiceException
    }

    @Test
    public void getLikesAmount_validParameterIdTraining_correct() throws DAOException, ServiceException {
        //given
        int likesAmount = 1;
        Mockito.doReturn(likesAmount).when(likeDAO).getTrainingLikesAmount(Mockito.anyInt());
        int result = adminService.getLikesAmount(Mockito.anyInt());
        //then
        assertEquals(result, likesAmount);
    }

    @Test(expected = ServiceException.class)
    public void getDislikesAmount_DAOExceptionFromGetTrainingDislikesAmount_ServiceException()
            throws DAOException, ServiceException {
        //given
        //when
        Mockito.doThrow(DAOException.class).when(dislikeDAO).getTrainingDislikesAmount(Mockito.anyInt());
        adminService.getDislikesAmount(Mockito.anyInt());
        //then
        //expected ServiceException
    }

    @Test
    public void getDislikesAmount_validParameterIdTraining_correct() throws DAOException, ServiceException {
        //given
        int dislikesAmount = 1;
        Mockito.doReturn(dislikesAmount).when(dislikeDAO).getTrainingDislikesAmount(Mockito.anyInt());
        int result = adminService.getDislikesAmount(Mockito.anyInt());
        //then
        assertEquals(result, dislikesAmount);
    }

    @Test(expected = ServiceException.class)
    public void changeBannedStatus_DAOExceptionFromChangeBannedStatus_ServiceException()
            throws DAOException, ServiceException {
        //given
        //when
        Mockito.doThrow(DAOException.class).when(userDAO).changeBannedStatus(Mockito.anyInt(), Mockito.anyBoolean());
        adminService.changeBannedStatus(Mockito.anyInt(), Mockito.anyBoolean());
        //then
        //expected ServiceException
    }

    @Test
    public void changeBannedStatus_validParameters_void_correct() throws DAOException, ServiceException {
        //given
        //when
        Mockito.doNothing().when(userDAO).changeBannedStatus(Mockito.anyInt(), Mockito.anyBoolean());
        adminService.changeBannedStatus(Mockito.anyInt(), Mockito.anyBoolean());
        //then
    }

    @Test(expected = ServiceException.class)
    public void updateTrainingValues_DAOExceptionFromUpdateTraining_ServiceException()
            throws DAOException, ServiceException {
        //given
        //when
        Mockito.doThrow(DAOException.class).when(trainingDAO).
                updateTraining(Mockito.any(Training.class), Mockito.any(InputStream.class));
        int idTraining = -2;
        String title = "title";
        String requirements = "req";
        String information = "info";
        String city = "city";
        int hoursAmount = 10;
        int minMembers = 1;
        int maxMembers = 2;
        String statDate = "2222-10-10";
        String endDate = "2222-10-10";
        InputStream image = Mockito.mock(InputStream.class);
        int idTrainer = 1;
        adminService.updateTrainingValues(idTraining, title, requirements, information, city, hoursAmount,
                minMembers, maxMembers, LocalDate.parse(statDate), LocalDate.parse(endDate), image, idTrainer);
        //then
        //expected ServiceException
    }

    @Test
    public void updateTrainingValues_validParameters_void_correct() throws DAOException, ServiceException {
        //given
        Mockito.doNothing().when(trainingDAO).updateTraining(Mockito.mock(Training.class), Mockito.mock(InputStream.class));
        int idTraining = 1;
        String title = "title";
        String requirements = "req";
        String information = "info";
        String city = "city";
        int hoursAmount = 10;
        int minMembers = 1;
        int maxMembers = 2;
        String statDate = "2222-10-10";
        String endDate = "2222-10-10";
        InputStream image = null;
        int idTrainer = 1;
        adminService.updateTrainingValues(idTraining, title, requirements, information, city, hoursAmount,
                minMembers, maxMembers, LocalDate.parse(statDate), LocalDate.parse(endDate), image, idTrainer);
        //then
    }

    @Test(expected = ServiceException.class)
    public void changeTrainingDeletedStatus_DAOExceptionFromChangeTrainingDeletedStatus_ServiceException()
            throws DAOException, ServiceException {
        //given
        //when
        Mockito.doThrow(DAOException.class).when(trainingDAO).changeDeletedStatus(Mockito.anyInt());
        adminService.changeTrainingDeletedStatus(Mockito.anyInt());
        //then
        //expected ServiceException
    }

    @Test
    public void changeTrainingDeletedStatus_validParameterIdTraining_correct() throws DAOException, ServiceException {
        //given
        int deletedStatus = 0;
        Mockito.doReturn(deletedStatus).when(trainingDAO).changeDeletedStatus(Mockito.anyInt());
        int result = adminService.changeTrainingDeletedStatus(Mockito.anyInt());
        //then
        assertEquals(result, deletedStatus);
    }

    @Test(expected = ServiceException.class)
    public void getAllUsers_DAOExceptionFromGetAllUsers_ServiceException() throws DAOException, ServiceException {
        //given
        //when
        Mockito.doThrow(DAOException.class).when(userDAO).getAllUsers();
        adminService.viewAllUsers();
        //then
        //expected ServiceException
    }

    @Test
    public void getAllUsers_validParameterList_correct() throws DAOException, ServiceException {
        //given
        List<User> users = (List<User>) Mockito.mock(List.class);
        Mockito.doReturn(users).when(userDAO).getAllUsers();
        List<User> result = adminService.viewAllUsers();
        //then
        assertEquals(result, users);
    }

    @Test(expected = ServiceException.class)
    public void getUsersInformation_DAOExceptionFromGetUsersInformation_ServiceException()
            throws DAOException, ServiceException {
        //given
        //when
        Mockito.doThrow(DAOException.class).when(userDAO).getAllUserInformation(Mockito.anyInt());
        adminService.getUserInformation(Mockito.anyInt());
        //then
        //excepted ServiceException
    }

    @Test
    public void getUsersInformation_validParameterIdUser_correct() throws DAOException, ServiceException {
        //given
        User user = Mockito.mock(User.class);
        Mockito.doReturn(user).when(userDAO).getAllUserInformation(Mockito.anyInt());
        User result = adminService.getUserInformation(Mockito.anyInt());
        //then
        assertEquals(result, user);
    }
}