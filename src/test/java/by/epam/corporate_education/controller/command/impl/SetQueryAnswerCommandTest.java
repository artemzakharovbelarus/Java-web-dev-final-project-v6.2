package by.epam.corporate_education.controller.command.impl;

import by.epam.corporate_education.controller.command.Command;
import by.epam.corporate_education.controller.command.CommandException;
import by.epam.corporate_education.controller.util.ControllerUtilFactory;
import by.epam.corporate_education.controller.util.ParameterName;
import by.epam.corporate_education.controller.util.api.HttpRequestResponseKeeper;
import by.epam.corporate_education.controller.util.api.PathCreator;
import by.epam.corporate_education.controller.util.impl.HttpRequestResponseKeeperImpl;
import by.epam.corporate_education.service.api.TrainerService;
import by.epam.corporate_education.service.exception.ServiceException;
import by.epam.corporate_education.service.impl.TrainerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;

public class SetQueryAnswerCommandTest {

    ControllerUtilFactory utilFactory = ControllerUtilFactory.getINSTANCE();
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    TrainerService trainerService = Mockito.mock(TrainerServiceImpl.class);
    PathCreator pathCreator = utilFactory.getPathCreator();
    Command command;

    @Before
    public void init(){
        HttpRequestResponseKeeper keeper = new HttpRequestResponseKeeperImpl();
        keeper.setAll(request, response);
        utilFactory.setHttpRequestResponseKeeper(keeper);
        command = new SetQueryAnswerCommand(trainerService, utilFactory);
    }

    @Test(expected = CommandException.class)
    public void execute_serviceExceptionFromSetQueryAnswer_commandException_errorPath()
            throws ServiceException, CommandException {
        //given
        //when
        Mockito.when(request.getParameter(ParameterName.ID_TRAINING)).thenReturn("1");
        Mockito.when(request.getParameter(ParameterName.ID_QUERY)).thenReturn("1");
        Mockito.when(request.getParameter(ParameterName.ANSWER)).thenReturn("1");
        Mockito.doThrow(ServiceException.class).when(trainerService).setQueryAnswer(Mockito.anyInt(), Mockito.anyInt());
        String result = command.execute();
        //then
        String expected = pathCreator.getError();
        assertEquals(expected, result);
    }

    @Test
    public void execute_validParameter_viewTrainingQueriesPath()
            throws ServiceException, CommandException {
        //given
        //when
        Mockito.when(request.getParameter(ParameterName.ID_TRAINING)).thenReturn("1");
        Mockito.when(request.getParameter(ParameterName.ID_QUERY)).thenReturn("1");
        Mockito.when(request.getParameter(ParameterName.ANSWER)).thenReturn("1");
        Mockito.doNothing().when(trainerService).setQueryAnswer(Mockito.anyInt(), Mockito.anyInt());
        String result = command.execute();
        //then
        String expected = pathCreator.getTrainingQueries(request.getContextPath(), 1);
        assertEquals(expected, result);
    }
}