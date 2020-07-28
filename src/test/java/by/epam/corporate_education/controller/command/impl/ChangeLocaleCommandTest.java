package by.epam.corporate_education.controller.command.impl;

import by.epam.corporate_education.controller.command.Command;
import by.epam.corporate_education.controller.command.CommandException;
import by.epam.corporate_education.controller.util.ControllerUtilFactory;
import by.epam.corporate_education.controller.util.ParameterName;
import by.epam.corporate_education.controller.util.api.HttpRequestResponseKeeper;
import by.epam.corporate_education.controller.util.api.PathCreator;
import by.epam.corporate_education.controller.util.impl.HttpRequestResponseKeeperImpl;
import by.epam.corporate_education.service.api.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;

public class ChangeLocaleCommandTest {

    ControllerUtilFactory utilFactory = ControllerUtilFactory.getINSTANCE();
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    PathCreator pathCreator = utilFactory.getPathCreator();
    Command command;

    @Before
    public void init(){
        HttpRequestResponseKeeper keeper = new HttpRequestResponseKeeperImpl();
        keeper.setAll(request, response);
        utilFactory.setHttpRequestResponseKeeper(keeper);
        command = new ChangeLocaleCommand(utilFactory);
    }

    @Test
    public void execute_validParameter_correct() throws CommandException {
        //given
        //when
        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getParameter(ParameterName.LOCALE)).thenReturn("locale");
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.doNothing().when(session).setAttribute(ParameterName.LOCALE, "");
        String result = command.execute();
        //then
        String expected = pathCreator.getChangeLocale(request.getContextPath());
        assertEquals(result, expected);
    }
}