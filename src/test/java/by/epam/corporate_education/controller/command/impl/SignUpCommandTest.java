package by.epam.corporate_education.controller.command.impl;

import by.epam.corporate_education.controller.command.Command;
import by.epam.corporate_education.controller.command.CommandException;
import by.epam.corporate_education.controller.util.ControllerUtilFactory;
import by.epam.corporate_education.controller.util.ParameterName;
import by.epam.corporate_education.controller.util.api.HttpRequestResponseKeeper;
import by.epam.corporate_education.controller.util.api.PathCreator;
import by.epam.corporate_education.controller.util.impl.HttpRequestResponseKeeperImpl;
import by.epam.corporate_education.entity.User;
import by.epam.corporate_education.service.api.UserService;
import by.epam.corporate_education.service.exception.*;
import by.epam.corporate_education.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;

public class SignUpCommandTest {

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
        command = new SignUpCommand(userService, utilFactory);
    }

    @Test(expected = CommandException.class)
    public void execute_serviceExceptionFromSignIn_commandException_errorPath() throws ServiceException, CommandException {
        //given
        //when
        Mockito.when(request.getParameter(ParameterName.USERNAME)).thenReturn("username");
        Mockito.when(request.getParameter(ParameterName.EMAIL)).thenReturn("email@gmail.com");
        Mockito.when(request.getParameter(ParameterName.PASSWORD)).thenReturn("password");
        Mockito.when(request.getParameter(ParameterName.CONFIRMED_PASSWORD)).thenReturn("password");
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.doThrow(ServiceException.class).when(userService).signUp(Mockito.anyString(), Mockito.anyString(),
                Mockito.anyString(), Mockito.anyString());
        String result = command.execute();
        //then
        String expected = pathCreator.getError();
        assertEquals(expected, result);
    }

    @Test
    public void execute_invalidUsernameExceptionFromSignIn_commandException_signUpPath() throws ServiceException, CommandException {
        //given
        //when
        Mockito.when(request.getParameter(ParameterName.USERNAME)).thenReturn("use");
        Mockito.when(request.getParameter(ParameterName.EMAIL)).thenReturn("email@gmail.com");
        Mockito.when(request.getParameter(ParameterName.PASSWORD)).thenReturn("password");
        Mockito.when(request.getParameter(ParameterName.CONFIRMED_PASSWORD)).thenReturn("password");
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.doThrow(InvalidUsernameException.class).when(userService).signUp(Mockito.anyString(), Mockito.anyString(),
                Mockito.anyString(), Mockito.anyString());
        String result = command.execute();
        //then
        String expected = pathCreator.getSignUp();
        assertEquals(expected, result);
    }

    @Test
    public void execute_invalidEmailExceptionFromSignIn_commandException_signUpPath() throws ServiceException, CommandException {
        //given
        //when
        Mockito.when(request.getParameter(ParameterName.USERNAME)).thenReturn("username");
        Mockito.when(request.getParameter(ParameterName.EMAIL)).thenReturn("email");
        Mockito.when(request.getParameter(ParameterName.PASSWORD)).thenReturn("password");
        Mockito.when(request.getParameter(ParameterName.CONFIRMED_PASSWORD)).thenReturn("password");
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.doThrow(InvalidEmailException.class).when(userService).signUp(Mockito.anyString(), Mockito.anyString(),
                Mockito.anyString(), Mockito.anyString());
        String result = command.execute();
        //then
        String expected = pathCreator.getSignUp();
        assertEquals(expected, result);
    }

    @Test
    public void execute_invalidPasswordExceptionFromSignIn_commandException_signUpPath() throws ServiceException, CommandException {
        //given
        //when
        Mockito.when(request.getParameter(ParameterName.USERNAME)).thenReturn("username");
        Mockito.when(request.getParameter(ParameterName.EMAIL)).thenReturn("email@gmail.com");
        Mockito.when(request.getParameter(ParameterName.PASSWORD)).thenReturn("pass");
        Mockito.when(request.getParameter(ParameterName.CONFIRMED_PASSWORD)).thenReturn("password");
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.doThrow(InvalidPasswordException.class).when(userService).signUp(Mockito.anyString(), Mockito.anyString(),
                Mockito.anyString(), Mockito.anyString());
        String result = command.execute();
        //then
        String expected = pathCreator.getSignUp();
        assertEquals(expected, result);
    }

    @Test
    public void execute_EquivalencePasswordExceptionFromSignIn_commandException_signUpPath() throws ServiceException, CommandException {
        //given
        //when
        Mockito.when(request.getParameter(ParameterName.USERNAME)).thenReturn("use");
        Mockito.when(request.getParameter(ParameterName.EMAIL)).thenReturn("email@gmail.com");
        Mockito.when(request.getParameter(ParameterName.PASSWORD)).thenReturn("password");
        Mockito.when(request.getParameter(ParameterName.CONFIRMED_PASSWORD)).thenReturn("password");
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.doThrow(EquivalencePasswordException.class).when(userService).signUp(Mockito.anyString(), Mockito.anyString(),
                Mockito.anyString(), Mockito.anyString());
        String result = command.execute();
        //then
        String expected = pathCreator.getSignUp();
        assertEquals(expected, result);
    }

    @Test
    public void execute_validParameters_commandException_signInPath() throws ServiceException, CommandException {
        //given
        //when
        Mockito.when(request.getParameter(ParameterName.USERNAME)).thenReturn("username");
        Mockito.when(request.getParameter(ParameterName.EMAIL)).thenReturn("email@gmail.com");
        Mockito.when(request.getParameter(ParameterName.PASSWORD)).thenReturn("password");
        Mockito.when(request.getParameter(ParameterName.CONFIRMED_PASSWORD)).thenReturn("password");
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.doNothing().when(userService).signUp(Mockito.anyString(), Mockito.anyString(),
                Mockito.anyString(), Mockito.anyString());
        String result = command.execute();
        //then
        String expected = pathCreator.getSignIn();
        assertEquals(expected, result);
    }
}