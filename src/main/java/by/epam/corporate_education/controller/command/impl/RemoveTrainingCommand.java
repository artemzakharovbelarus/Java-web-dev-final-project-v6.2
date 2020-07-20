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

public class RemoveTrainingCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandUtilFactory utilFactory = CommandUtilFactory.getINSTANCE();
        NumberChecker numberChecker = utilFactory.getNumberChecker();
        PathCreator pathCreator = utilFactory.getPathCreator();

        String path = pathCreator.getError();
        String idTraining = request.getParameter("idTraining");

        ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
        AdminService adminService = serviceFactory.getAdminServiceImpl();
        int result = 0;
        try{
            if (numberChecker.isNumber(idTraining)){
                result = adminService.changeTrainingDeletedStatus(Integer.parseInt(idTraining));
                path = getResultPath(request.getContextPath(), pathCreator, result);
            }
        } catch (ServiceException e){
            throw new CommandException(e);
        }
        return path;
    }

    private String getResultPath(String contextPath, PathCreator pathCreator, int result){
        if (result == 1){
            return pathCreator.getForwardTrainingsPage(contextPath);
        } else {
            return pathCreator.getError();
        }
    }
}
