package by.epam.corporate_education.controller.command.impl;

import by.epam.corporate_education.controller.command.Command;
import by.epam.corporate_education.controller.command.CommandException;
import by.epam.corporate_education.controller.util.ControllerUtilFactory;
import by.epam.corporate_education.controller.util.ParameterName;
import by.epam.corporate_education.controller.util.api.AttributesInitializer;
import by.epam.corporate_education.controller.util.api.HttpRequestResponseKeeper;
import by.epam.corporate_education.controller.util.api.ControllerValueChecker;
import by.epam.corporate_education.controller.util.api.PathCreator;
import by.epam.corporate_education.entity.Query;
import by.epam.corporate_education.service.ServiceFactory;
import by.epam.corporate_education.service.api.TrainerService;
import by.epam.corporate_education.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ViewTrainingQueriesCommand implements Command {

    private ControllerUtilFactory utilFactory = ControllerUtilFactory.getINSTANCE();
    private ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private TrainerService trainerService;

    public ViewTrainingQueriesCommand(){
        trainerService = serviceFactory.getTrainerServiceImpl();
    }

    //annotation
    public ViewTrainingQueriesCommand(TrainerService trainerService, ControllerUtilFactory utilFactory){
        this.trainerService = trainerService;
        this.utilFactory = utilFactory;
    }

    @Override
    public String execute() throws CommandException {
        ControllerValueChecker valueChecker = utilFactory.getControllerValueChecker();
        PathCreator pathCreator = utilFactory.getPathCreator();
        AttributesInitializer attributesInitializer = utilFactory.getAttributesInitializer();
        HttpRequestResponseKeeper keeper = utilFactory.getHttpRequestResponseKeeper();

        HttpServletRequest request = keeper.getRequest();
        HttpServletResponse response = keeper.getResponse();

        HttpSession session = request.getSession();
        int idStatus = (Integer) session.getAttribute(ParameterName.STATUS);

        String path = pathCreator.getError();
        String idTraining = request.getParameter(ParameterName.ID_TRAINING);

        List<Query> queries = new ArrayList<>();
        try{
            if (valueChecker.isNumber(idTraining) && valueChecker.isTeacher(idStatus)){
                int idTrainingInt = Integer.parseInt(idTraining);
                queries = trainerService.getTrainingQueries(idTrainingInt);
                attributesInitializer.setRequestAttributesQueries(request, queries);
                path = pathCreator.getTrainingQueries();
            } else {
                path = pathCreator.getError();
            }
        } catch (ServiceException e){
            throw new CommandException(e);
        }
        return path;
    }
}
