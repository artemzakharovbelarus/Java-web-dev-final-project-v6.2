package by.epam.corporate_education.controller.command.impl;

import by.epam.corporate_education.controller.command.Command;
import by.epam.corporate_education.controller.command.CommandException;
import by.epam.corporate_education.controller.util.ControllerUtilFactory;
import by.epam.corporate_education.controller.util.ParameterName;
import by.epam.corporate_education.controller.util.api.HttpRequestResponseKeeper;
import by.epam.corporate_education.controller.util.api.PathCreator;
import by.epam.corporate_education.controller.util.impl.HttpRequestResponseKeeperImpl;
import by.epam.corporate_education.entity.Query;
import by.epam.corporate_education.entity.Training;
import by.epam.corporate_education.service.api.AdminService;
import by.epam.corporate_education.service.api.UserService;
import by.epam.corporate_education.service.exception.ServiceException;
import by.epam.corporate_education.service.impl.AdminServiceImpl;
import by.epam.corporate_education.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;

public class ViewTrainingCommandTest {

    ControllerUtilFactory utilFactory = ControllerUtilFactory.getINSTANCE();
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    AdminService adminService = Mockito.mock(AdminServiceImpl.class);
    UserService userService = Mockito.mock(UserServiceImpl.class);
    PathCreator pathCreator = utilFactory.getPathCreator();
    Command command;

    @Before
    public void init(){
        HttpRequestResponseKeeper keeper = new HttpRequestResponseKeeperImpl();
        keeper.setAll(request, response);
        utilFactory.setHttpRequestResponseKeeper(keeper);
        command = new ViewTrainingCommand(adminService, userService, utilFactory);
    }

    @Test(expected = CommandException.class)
    public void execute_serviceExceptionFromGetTraining_commandException_errorPath() throws ServiceException, CommandException {
        //given
        //when
        Mockito.when(request.getParameter(ParameterName.ID_TRAINING)).thenReturn("1");
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute(ParameterName.ID_USER)).thenReturn(1);
        Mockito.doThrow(ServiceException.class).when(userService).getTraining(Mockito.anyInt());
        String result = command.execute();
        //then
        String expected = pathCreator.getError();
        assertEquals(expected, result);
    }

    @Test(expected = CommandException.class)
    public void execute_serviceExceptionFromGetQuery_commandException_errorPath() throws ServiceException, CommandException {
        //given
        //when
        Mockito.when(request.getParameter(ParameterName.ID_TRAINING)).thenReturn("1");
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute(ParameterName.ID_USER)).thenReturn(1);
        Mockito.doThrow(ServiceException.class).when(userService).getQueryByIdTrainingIdUser(Mockito.anyInt(), Mockito.anyInt());
        String result = command.execute();
        //then
        String expected = pathCreator.getError();
        assertEquals(expected, result);
    }

    @Test(expected = CommandException.class)
    public void execute_serviceExceptionFromIsLikeEnabled_commandException_errorPath() throws ServiceException, CommandException {
        //given
        //when
        Mockito.when(request.getParameter(ParameterName.ID_TRAINING)).thenReturn("1");
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute(ParameterName.ID_USER)).thenReturn(1);
        Training training = Mockito.mock(Training.class);
        Mockito.doReturn(training).when(userService).getTraining(Mockito.anyInt());
        Query query = Mockito.mock(Query.class);
        Mockito.doReturn(query).when(userService).getQueryByIdTrainingIdUser(Mockito.anyInt(), Mockito.anyInt());
        Mockito.doThrow(ServiceException.class).when(userService).isLikeEnabled(Mockito.anyInt(), Mockito.anyInt());
        String result = command.execute();
        //then
        String expected = pathCreator.getError();
        assertEquals(expected, result);
    }

    @Test(expected = CommandException.class)
    public void execute_serviceExceptionFromIsDislikeEnabled_commandException_errorPath() throws ServiceException, CommandException {
        //given
        //when
        Mockito.when(request.getParameter(ParameterName.ID_TRAINING)).thenReturn("1");
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute(ParameterName.ID_USER)).thenReturn(1);
        Training training = Mockito.mock(Training.class);
        Mockito.doReturn(training).when(userService).getTraining(Mockito.anyInt());
        Query query = Mockito.mock(Query.class);
        Mockito.doReturn(query).when(userService).getQueryByIdTrainingIdUser(Mockito.anyInt(), Mockito.anyInt());
        boolean likeEnabled = false;
        Mockito.doReturn(likeEnabled).when(userService).isLikeEnabled(Mockito.anyInt(), Mockito.anyInt());
        Mockito.doThrow(ServiceException.class).when(userService).isDislikeEnabled(Mockito.anyInt(), Mockito.anyInt());
        String result = command.execute();
        //then
        String expected = pathCreator.getError();
        assertEquals(expected, result);
    }

    @Test(expected = CommandException.class)
    public void execute_serviceExceptionFromGetLikesAmount_commandException_errorPath() throws ServiceException, CommandException {
        //given
        //when
        Mockito.when(request.getParameter(ParameterName.ID_TRAINING)).thenReturn("1");
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute(ParameterName.ID_USER)).thenReturn(1);
        Training training = Mockito.mock(Training.class);
        Mockito.doReturn(training).when(userService).getTraining(Mockito.anyInt());
        Query query = Mockito.mock(Query.class);
        Mockito.doReturn(query).when(userService).getQueryByIdTrainingIdUser(Mockito.anyInt(), Mockito.anyInt());
        boolean likeEnabled = false;
        Mockito.doReturn(likeEnabled).when(userService).isLikeEnabled(Mockito.anyInt(), Mockito.anyInt());
        boolean dislikeEnabled = false;
        Mockito.doReturn(dislikeEnabled).when(userService).isDislikeEnabled(Mockito.anyInt(), Mockito.anyInt());
        Mockito.doThrow(ServiceException.class).when(adminService).getLikesAmount(Mockito.anyInt());
        String result = command.execute();
        //then
        String expected = pathCreator.getError();
        assertEquals(expected, result);
    }

    @Test(expected = CommandException.class)
    public void execute_serviceExceptionFromGetDislikesAmount_commandException_errorPath() throws ServiceException, CommandException {
        //given
        //when
        Mockito.when(request.getParameter(ParameterName.ID_TRAINING)).thenReturn("1");
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute(ParameterName.ID_USER)).thenReturn(1);
        Training training = Mockito.mock(Training.class);
        Mockito.doReturn(training).when(userService).getTraining(Mockito.anyInt());
        Query query = Mockito.mock(Query.class);
        Mockito.doReturn(query).when(userService).getQueryByIdTrainingIdUser(Mockito.anyInt(), Mockito.anyInt());
        boolean likeEnabled = false;
        Mockito.doReturn(likeEnabled).when(userService).isLikeEnabled(Mockito.anyInt(), Mockito.anyInt());
        boolean dislikeEnabled = false;
        Mockito.doReturn(dislikeEnabled).when(userService).isDislikeEnabled(Mockito.anyInt(), Mockito.anyInt());
        int likesAmount = 0;
        Mockito.doReturn(likesAmount).when(adminService).getLikesAmount(Mockito.anyInt());
        Mockito.doThrow(ServiceException.class).when(adminService).getDislikesAmount(Mockito.anyInt());
        String result = command.execute();
        //then
        String expected = pathCreator.getError();
        assertEquals(expected, result);
    }

    @Test
    public void execute_validParameters_viewTrainingPath() throws ServiceException, CommandException {
        //given
        //when
        Mockito.when(request.getParameter(ParameterName.ID_TRAINING)).thenReturn("1");
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute(ParameterName.ID_USER)).thenReturn(1);
        Training training = Mockito.mock(Training.class);
        Mockito.doReturn(training).when(userService).getTraining(Mockito.anyInt());
        Query query = Mockito.mock(Query.class);
        Mockito.doReturn(query).when(userService).getQueryByIdTrainingIdUser(Mockito.anyInt(), Mockito.anyInt());
        boolean likeEnabled = false;
        Mockito.doReturn(likeEnabled).when(userService).isLikeEnabled(Mockito.anyInt(), Mockito.anyInt());
        boolean dislikeEnabled = false;
        Mockito.doReturn(dislikeEnabled).when(userService).isDislikeEnabled(Mockito.anyInt(), Mockito.anyInt());
        int likesAmount = 0;
        Mockito.doReturn(likesAmount).when(adminService).getLikesAmount(Mockito.anyInt());
        int dislikesAmount = 0;
        Mockito.doReturn(dislikesAmount).when(adminService).getDislikesAmount(Mockito.anyInt());
        String result = command.execute();
        //then
        String expected = pathCreator.getTrainingPage();
        assertEquals(expected, result);
    }
}