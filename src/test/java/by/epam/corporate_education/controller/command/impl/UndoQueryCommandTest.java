package by.epam.corporate_education.controller.command.impl;

import by.epam.corporate_education.controller.command.Command;
import by.epam.corporate_education.controller.command.CommandException;
import by.epam.corporate_education.controller.util.ControllerUtilFactory;
import by.epam.corporate_education.controller.util.ParameterName;
import by.epam.corporate_education.controller.util.api.HttpRequestResponseKeeper;
import by.epam.corporate_education.controller.util.api.PathCreator;
import by.epam.corporate_education.controller.util.impl.HttpRequestResponseKeeperImpl;
import by.epam.corporate_education.service.api.UserService;
import by.epam.corporate_education.service.exception.ServiceException;
import by.epam.corporate_education.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;

public class UndoQueryCommandTest {

    ControllerUtilFactory utilFactory = ControllerUtilFactory.getINSTANCE();
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    UserService userService = Mockito.mock(UserServiceImpl.class);
    PathCreator pathCreator = utilFactory.getPathCreator();
    Command command;

    @Before
    public void init(){
        HttpRequestResponseKeeper keeper = new HttpRequestResponseKeeperImpl();
        keeper.setAll(request, response);
        utilFactory.setHttpRequestResponseKeeper(keeper);
        command = new UndoQueryCommand(userService, utilFactory);
    }

    @Test(expected = CommandException.class)
    public void execute_serviceExceptionFromUndoQuery_commandExcepion_errorPath() throws ServiceException, CommandException {
        //given
        //when
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute(ParameterName.ID_USER)).thenReturn(1);
        Mockito.when(request.getParameter(ParameterName.ID_QUERY)).thenReturn("1");
        Mockito.doThrow(ServiceException.class).when(userService).undoQuery(Mockito.anyInt());
        String result = command.execute();
        //then
        String expected = pathCreator.getError();
        assertEquals(expected, result);
    }

    @Test
    public void execute_validParameters_viewQueriesPath() throws ServiceException, CommandException {
        //given
        //when
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute(ParameterName.ID_USER)).thenReturn(1);
        Mockito.when(request.getParameter(ParameterName.ID_QUERY)).thenReturn("1");
        Mockito.doNothing().when(userService).undoQuery(Mockito.anyInt());
        String result = command.execute();
        //then
        String expected = pathCreator.getForwardQueries(request.getContextPath(), 1);
        assertEquals(expected, result);
    }
}