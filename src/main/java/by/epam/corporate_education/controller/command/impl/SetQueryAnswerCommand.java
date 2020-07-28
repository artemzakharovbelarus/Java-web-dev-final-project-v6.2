package by.epam.corporate_education.controller.command.impl;

import by.epam.corporate_education.controller.command.Command;
import by.epam.corporate_education.controller.command.CommandException;
import by.epam.corporate_education.controller.util.ControllerUtilFactory;
import by.epam.corporate_education.controller.util.ParameterName;
import by.epam.corporate_education.controller.util.api.AttributesInitializer;
import by.epam.corporate_education.controller.util.api.ControllerValueChecker;
import by.epam.corporate_education.controller.util.api.HttpRequestResponseKeeper;
import by.epam.corporate_education.controller.util.api.PathCreator;
import by.epam.corporate_education.service.ServiceFactory;
import by.epam.corporate_education.service.api.TrainerService;
import by.epam.corporate_education.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetQueryAnswerCommand implements Command {

    private ControllerUtilFactory utilFactory = ControllerUtilFactory.getINSTANCE();
    private ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private TrainerService trainerService;

    public SetQueryAnswerCommand(){
        trainerService = serviceFactory.getTrainerServiceImpl();
    }

    //annotation
    public SetQueryAnswerCommand(TrainerService trainerService, ControllerUtilFactory utilFactory){
        this.trainerService = trainerService;
        this.utilFactory = utilFactory;
    }

    @Override
    public String execute() throws CommandException {
        ControllerValueChecker controllerValueChecker = utilFactory.getControllerValueChecker();
        PathCreator pathCreator = utilFactory.getPathCreator();
        HttpRequestResponseKeeper keeper = utilFactory.getHttpRequestResponseKeeper();
        AttributesInitializer attributesInitializer = utilFactory.getAttributesInitializer();

        HttpServletRequest request = keeper.getRequest();
        HttpServletResponse response = keeper.getResponse();

        String path = pathCreator.getError();
        String idTraining = request.getParameter(ParameterName.ID_TRAINING);
        String idQuery = request.getParameter(ParameterName.ID_QUERY);
        String answer = request.getParameter(ParameterName.ANSWER);

        try{
            if (controllerValueChecker.isNumber(idQuery) && controllerValueChecker.isNumber(answer) && controllerValueChecker.isNumber(idTraining)){
                int idQueryInt = Integer.parseInt(idQuery);
                int answerInt = Integer.parseInt(answer);
                int idTrainingInt = Integer.parseInt(idTraining);
                trainerService.setQueryAnswer(idQueryInt, answerInt);
                path = pathCreator.getTrainingQueries(request.getContextPath(), idTrainingInt);
            }
        } catch (ServiceException e){
            throw new CommandException(e);
        }
        return path;
    }
}
