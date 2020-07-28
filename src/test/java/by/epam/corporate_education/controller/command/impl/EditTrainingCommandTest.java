package by.epam.corporate_education.controller.command.impl;

import by.epam.corporate_education.controller.command.Command;
import by.epam.corporate_education.controller.command.CommandException;
import by.epam.corporate_education.controller.util.ControllerUtilFactory;
import by.epam.corporate_education.controller.util.ParameterName;
import by.epam.corporate_education.controller.util.api.HttpRequestResponseKeeper;
import by.epam.corporate_education.controller.util.api.PathCreator;
import by.epam.corporate_education.controller.util.impl.HttpRequestResponseKeeperImpl;
import by.epam.corporate_education.service.api.AdminService;
import by.epam.corporate_education.service.exception.ServiceException;
import by.epam.corporate_education.service.impl.AdminServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class EditTrainingCommandTest {

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
        command = new EditTrainingCommand(adminService, utilFactory);
    }

    @Test
    public void execute_validParameters_viewTrainingPath() throws ServiceException, CommandException {
        //given
        //when
        Mockito.when(request.getParameter(ParameterName.ID_TRAINING)).thenReturn("1");
        Mockito.when(request.getParameter(ParameterName.TITLE)).thenReturn("Php web development");
        Mockito.when(request.getParameter(ParameterName.REQUIREMENTS)).thenReturn("Have som knowledge in OOP");
        Mockito.when(request.getParameter(ParameterName.INFORMATION)).thenReturn("Best training ever!");
        Mockito.when(request.getParameter(ParameterName.HOURS_AMOUNT)).thenReturn("20");
        Mockito.when(request.getParameter(ParameterName.MIN_MEMBERS)).thenReturn("10");
        Mockito.when(request.getParameter(ParameterName.MAX_MEMBERS)).thenReturn("15");
        Mockito.when(request.getParameter(ParameterName.START_DATE)).thenReturn("2020-10-10");
        Mockito.when(request.getParameter(ParameterName.END_DATE)).thenReturn("2222-11-10");
        Mockito.when(request.getParameter(ParameterName.ID_TRAINER)).thenReturn("2");
        Mockito.when(request.getParameter(ParameterName.TRAINING_PHOTO)).thenReturn("img");
        Mockito.doNothing().when(adminService).updateTrainingValues(Mockito.anyInt(),
                Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyInt(),
                Mockito.anyInt(), Mockito.anyInt(), Mockito.any(LocalDate.class), Mockito.any(LocalDate.class),
                Mockito.anyString(), Mockito.anyInt());
        String result = command.execute();
        //then
        String expected = pathCreator.getViewTraining(request.getContextPath(), 1);
        assertEquals(expected, result);
    }
}