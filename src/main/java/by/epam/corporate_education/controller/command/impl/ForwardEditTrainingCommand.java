package by.epam.corporate_education.controller.command.impl;

import by.epam.corporate_education.controller.command.Command;
import by.epam.corporate_education.controller.command.CommandException;
import by.epam.corporate_education.controller.command.util.CommandUtilFactory;
import by.epam.corporate_education.controller.command.util.api.AttributesInitializer;
import by.epam.corporate_education.controller.command.util.api.NumberChecker;
import by.epam.corporate_education.controller.command.util.api.PathCreator;
import by.epam.corporate_education.entity.Training;
import by.epam.corporate_education.service.ServiceFactory;
import by.epam.corporate_education.service.api.UserService;
import by.epam.corporate_education.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardEditTrainingCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandUtilFactory utilFactory = CommandUtilFactory.getINSTANCE();
        NumberChecker numberChecker = utilFactory.getNumberChecker();
        AttributesInitializer attributesInitializer = utilFactory.getAttributesInitializer();
        PathCreator pathCreator = utilFactory.getPathCreator();

        String path = pathCreator.getError();
        String idTraining = request.getParameter("idTraining");

        ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
        UserService userService = serviceFactory.getUserServiceImpl();
        Training training = new Training();
        try {
            if (numberChecker.isNumber(idTraining)){
                int idTrainingInt = Integer.parseInt(idTraining);
                training = userService.getTraining(idTrainingInt);
                attributesInitializer.setRequestAttributesTraining(request, training);
                path = pathCreator.getForwardEditTraining();
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return path;
    }
}
