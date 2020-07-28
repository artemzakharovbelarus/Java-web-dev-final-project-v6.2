package by.epam.corporate_education.controller.command.impl;

import by.epam.corporate_education.controller.command.Command;
import by.epam.corporate_education.controller.command.CommandException;
import by.epam.corporate_education.controller.util.ControllerUtilFactory;
import by.epam.corporate_education.controller.util.ParameterName;
import by.epam.corporate_education.controller.util.api.HttpRequestResponseKeeper;
import by.epam.corporate_education.controller.util.api.PathCreator;
import by.epam.corporate_education.controller.util.impl.HttpRequestResponseKeeperImpl;
import by.epam.corporate_education.entity.User;
import by.epam.corporate_education.entity.UserStatus;
import by.epam.corporate_education.service.api.UserService;
import by.epam.corporate_education.service.exception.InvalidSignInValuesException;
import by.epam.corporate_education.service.exception.ServiceException;
import by.epam.corporate_education.service.exception.UserBannedException;
import by.epam.corporate_education.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;

public class SignInCommandTest {

    ControllerUtilFactory utilFactory = ControllerUtilFactory.getINSTANCE();
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    UserService userService = Mockito.mock(UserServiceImpl.class);
    PathCreator pathCreator = utilFactory.getPathCreator();
    Command command;

    @Before
    public void init() {
        HttpRequestResponseKeeper keeper = new HttpRequestResponseKeeperImpl();
        keeper.setAll(request, response);
        utilFactory.setHttpRequestResponseKeeper(keeper);
        command = new SignInCommand(userService, utilFactory);
    }

    @Test(expected = CommandException.class)
    public void execute_serviceExceptionFromSignIn_commandException_errorPath() throws ServiceException, CommandException {
        //given
        //when
        Mockito.when(request.getParameter(ParameterName.USERNAME)).thenReturn("username");
        Mockito.when(request.getParameter(ParameterName.PASSWORD)).thenReturn("password");
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.doThrow(ServiceException.class).when(userService).signIn(Mockito.anyString(), Mockito.anyString());
        String result = command.execute();
        //then
        String expected = pathCreator.getError();
        assertEquals(expected, result);
    }

    @Test
    public void execute_userBannedExceptionFromSignIn_commandException_signInPath() throws ServiceException, CommandException {
        //given
        //when
        Mockito.when(request.getParameter(ParameterName.USERNAME)).thenReturn("username");
        Mockito.when(request.getParameter(ParameterName.PASSWORD)).thenReturn("password");
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.doThrow(UserBannedException.class).when(userService).signIn(Mockito.anyString(), Mockito.anyString());
        String result = command.execute();
        //then
        String expected = pathCreator.getSignIn();
        assertEquals(expected, result);
    }

    @Test
    public void execute_invalidSignInValuesFromSignIn_commandException_signInPath() throws ServiceException, CommandException {
        //given
        //when
        Mockito.when(request.getParameter(ParameterName.USERNAME)).thenReturn("username");
        Mockito.when(request.getParameter(ParameterName.PASSWORD)).thenReturn("password");
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.doThrow(InvalidSignInValuesException.class).when(userService).signIn(Mockito.anyString(), Mockito.anyString());
        String result = command.execute();
        //then
        String expected = pathCreator.getSignIn();
        assertEquals(expected, result);
    }

    @Test
    public void execute_validParameter_signInPath() throws ServiceException, CommandException {
        //given
        //when
        Mockito.when(request.getParameter(ParameterName.USERNAME)).thenReturn("username");
        Mockito.when(request.getParameter(ParameterName.PASSWORD)).thenReturn("password");
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        User user = Mockito.mock(User.class);
        UserStatus status = UserStatus.ADMIN;
        Mockito.doReturn(user).when(userService).signIn(Mockito.anyString(), Mockito.anyString());
        Mockito.when(user.getStatus()).thenReturn(status);
        String result = command.execute();
        //then
        String expected = pathCreator.getForwardMainPage(request.getContextPath());
        assertEquals(expected, result);
    }
}