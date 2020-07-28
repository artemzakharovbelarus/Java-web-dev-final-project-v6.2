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
import java.util.List;

import static org.junit.Assert.*;

public class ChangeBannedStatusCommandTest {

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
        command = new ChangeBannedStatusCommand(adminService, utilFactory);
    }

    @Test(expected = CommandException.class)
    public void execute_serviceExceptionFromChangeBannedStatus_commandException() throws ServiceException, CommandException {
        //give
        //when
        Mockito.when(request.getParameter(ParameterName.ID_USER)).thenReturn("1");
        Mockito.when(request.getParameter(ParameterName.BANN)).thenReturn("true");
        Mockito.doThrow(ServiceException.class)
                .when(adminService).changeBannedStatus(Mockito.anyInt(), Mockito.anyBoolean());
        command.execute();
        //then
        //expected CommandException
    }

    @Test
    public void execute_validParameters_correct() throws ServiceException, CommandException {
        //given
        //when
        Mockito.when(request.getParameter(ParameterName.ID_USER)).thenReturn("1");
        Mockito.when(request.getParameter(ParameterName.BANN)).thenReturn("true");
        List<User> users = (List<User>) Mockito.mock(List.class);
        Mockito.doReturn(users).when(adminService).viewAllUsers();
        String result = command.execute();
        //then
        String expected = pathCreator.getViewAllUsers(request.getContextPath());
        assertEquals(result, expected);
    }
}