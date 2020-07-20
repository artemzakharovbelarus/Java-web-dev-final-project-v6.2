package by.epam.corporate_education.controller.command.impl;

import by.epam.corporate_education.controller.command.Command;
import by.epam.corporate_education.controller.command.CommandException;
import by.epam.corporate_education.controller.command.util.CommandUtilFactory;
import by.epam.corporate_education.controller.command.util.api.NumberChecker;
import by.epam.corporate_education.controller.command.util.api.PathCreator;
import by.epam.corporate_education.service.ServiceFactory;
import by.epam.corporate_education.service.api.UserService;
import by.epam.corporate_education.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

public class EnrollTrainingCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandUtilFactory utilFactory = CommandUtilFactory.getINSTANCE();
        NumberChecker numberChecker = utilFactory.getNumberChecker();
        PathCreator pathCreator = utilFactory.getPathCreator();

        String path = pathCreator.getError();
        String idTraining = request.getParameter("idTraining");
        HttpSession session = request.getSession();
        Integer idUser = (Integer) session.getAttribute("idUser");

        ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
        UserService userService = serviceFactory.getUserServiceImpl();
        try{
            if (numberChecker.isNumber(idTraining)) {
                int idTrainingInt = Integer.parseInt(idTraining);
                userService.writeQuery(idTrainingInt, idUser);
                path = pathCreator.getEnrolTraining(request.getContextPath(), idTrainingInt);
            }
        } catch (ServiceException e){
            throw new CommandException(e);
        }
        return path;
    }
}
