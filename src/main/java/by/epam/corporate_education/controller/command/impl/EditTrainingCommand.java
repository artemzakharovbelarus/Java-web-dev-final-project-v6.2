package by.epam.corporate_education.controller.command.impl;

import by.epam.corporate_education.controller.command.Command;
import by.epam.corporate_education.controller.command.CommandException;
import by.epam.corporate_education.controller.command.util.CommandUtilFactory;
import by.epam.corporate_education.controller.command.util.api.NumberChecker;
import by.epam.corporate_education.controller.command.util.api.PathCreator;
import by.epam.corporate_education.service.ServiceFactory;
import by.epam.corporate_education.service.api.AdminService;
import by.epam.corporate_education.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

public class EditTrainingCommand implements Command {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandUtilFactory utilFactory = CommandUtilFactory.getINSTANCE();
        NumberChecker numberChecker = utilFactory.getNumberChecker();
        PathCreator pathCreator = utilFactory.getPathCreator();

        String path = pathCreator.getError();
        String idTraining = request.getParameter("idTraining");

        String title = request.getParameter("title");
        String requirements = request.getParameter("requirements");
        String information = request.getParameter("information");
        String city = request.getParameter("city");
        int hoursAmount = Integer.parseInt(request.getParameter("hours-amount"));
        int minMembers = Integer.parseInt(request.getParameter("min-members"));
        int maxMembers = Integer.parseInt(request.getParameter("max-members"));
        LocalDate startDate = LocalDate.parse(request.getParameter("start-date"));
        LocalDate endDate = LocalDate.parse(request.getParameter("end-date"));
        int idTrainer = Integer.parseInt(request.getParameter("idTrainer"));
        String trainingPhoto = request.getParameter("training-photo");

        ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
        AdminService adminService = serviceFactory.getAdminServiceImpl();

        try{
            if (numberChecker.isNumber(idTraining)){
                int idTrainingInt = Integer.parseInt(idTraining);
                adminService.updateTrainingValues(idTrainingInt, title, requirements, information, city, hoursAmount,
                        minMembers, maxMembers, startDate, endDate, trainingPhoto, idTrainer);
                path = pathCreator.getViewTraining(request.getContextPath(), idTrainingInt);
            }
        } catch (ServiceException e){
            throw new CommandException(e);
        }
        return path;
    }
}
