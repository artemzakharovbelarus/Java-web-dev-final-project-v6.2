package by.epam.corporate_education.controller.command.impl;

import by.epam.corporate_education.controller.command.Command;
import by.epam.corporate_education.controller.command.CommandException;
import by.epam.corporate_education.controller.command.util.CommandUtilFactory;
import by.epam.corporate_education.controller.command.util.api.AttributesInitializer;
import by.epam.corporate_education.controller.command.util.api.NumberChecker;
import by.epam.corporate_education.controller.command.util.api.PathCreator;
import by.epam.corporate_education.entity.Query;
import by.epam.corporate_education.service.ServiceFactory;
import by.epam.corporate_education.service.api.TrainerService;
import by.epam.corporate_education.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ViewTrainingQueriesCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandUtilFactory utilFactory = CommandUtilFactory.getINSTANCE();
        NumberChecker numberChecker = utilFactory.getNumberChecker();
        PathCreator pathCreator = utilFactory.getPathCreator();
        AttributesInitializer attributesInitializer = utilFactory.getAttributesInitializer();

        String path = pathCreator.getError();
        String idTraining = request.getParameter("idTraining");

        ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
        TrainerService trainerService = serviceFactory.getTrainerServiceImpl();

        List<Query> queries = new ArrayList<>();
        try{
            if (numberChecker.isNumber(idTraining)){
                int idTrainingInt = Integer.parseInt(idTraining);
                queries = trainerService.getTrainingQueries(idTrainingInt);
                attributesInitializer.setRequestAttributesQueries(request, queries);
                path = "training-queries";
            }
        } catch (ServiceException e){
            throw new CommandException(e);
        }
        return path;
    }
}
