package by.epam.corporate_education.controller.command.impl;

import by.epam.corporate_education.controller.command.Command;
import by.epam.corporate_education.controller.command.CommandException;
import by.epam.corporate_education.controller.command.util.CommandUtilFactory;
import by.epam.corporate_education.controller.command.util.api.NumberChecker;
import by.epam.corporate_education.controller.command.util.api.PathCreator;
import by.epam.corporate_education.service.ServiceFactory;
import by.epam.corporate_education.service.api.TrainerService;
import by.epam.corporate_education.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetQueryAnswerCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandUtilFactory utilFactory = CommandUtilFactory.getINSTANCE();
        NumberChecker numberChecker = utilFactory.getNumberChecker();
        PathCreator pathCreator = utilFactory.getPathCreator();

        String path = pathCreator.getError();
        String idTraining = request.getParameter("idTraining");
        String idQuery = request.getParameter("idQuery");
        String answer = request.getParameter("answer");

        ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
        TrainerService trainerService = serviceFactory.getTrainerServiceImpl();

        try{
            if (numberChecker.isNumber(idQuery) && numberChecker.isNumber(answer) && numberChecker.isNumber(idTraining)){
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
