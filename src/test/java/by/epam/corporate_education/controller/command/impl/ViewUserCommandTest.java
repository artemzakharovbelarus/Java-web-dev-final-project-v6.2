package by.epam.corporate_education.controller.command.impl;

import by.epam.corporate_education.controller.command.Command;
import by.epam.corporate_education.controller.command.CommandException;
import by.epam.corporate_education.controller.util.ControllerUtilFactory;
import by.epam.corporate_education.controller.util.ParameterName;
import by.epam.corporate_education.controller.util.api.HttpRequestResponseKeeper;
import by.epam.corporate_education.controller.util.api.PathCreator;
import by.epam.corporate_education.controller.util.impl.HttpRequestResponseKeeperImpl;
import by.epam.corporate_education.entity.User;
import by.epam.corporate_education.service.api.AdminService;
import by.epam.corporate_education.service.exception.ServiceException;
import by.epam.corporate_education.service.impl.AdminServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;

public class ViewUserCommandTest {

    ControllerUtilFactory utilFactory = ControllerUtilFactory.getINSTANCE();
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    AdminService adminService = Mockito.mock(AdminServiceImpl.class);
    PathCreator pathCreator = utilFactory.getPathCreator();
    Command command;

    @Before
    public void init(){
        HttpRequestResponseKeeper keeper = new HttpRequestResponseKeeperImpl();
        keeper.setAll(request, response);
        utilFactory.setHttpRequestResponseKeeper(keeper);
        command = new ViewUserCommand(adminService, utilFactory);
    }

    @Test(expected = CommandException.class)
    public void execute_serviceExceptionViewUser_CommandException() throws ServiceException, CommandException {
        //given
        //when
        Mockito.when(request.getParameter(ParameterName.ID_USER)).thenReturn("1");
        Mockito.doThrow(ServiceException.class).when(adminService).getUserInformation(Mockito.anyInt());
        command.execute();
        //then
        //expected CommandException
    }

    @Test
    public void execute_validParameter_correct() throws ServiceException, CommandException {
        //given
        //when
        Mockito.when(request.getParameter(ParameterName.ID_USER)).thenReturn("1");
        User user = Mockito.mock(User.class);
        Mockito.doReturn(user).when(adminService).getUserInformation(Mockito.anyInt());
        String result = command.execute();
        //then
        String expected = pathCreator.getUserPage();
        assertEquals(result, expected);
    }
}