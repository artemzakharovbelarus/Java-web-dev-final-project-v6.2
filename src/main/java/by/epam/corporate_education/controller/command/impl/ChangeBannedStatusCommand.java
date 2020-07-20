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

public class ChangeBannedStatusCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandUtilFactory utilFactory = CommandUtilFactory.getINSTANCE();
        NumberChecker numberChecker = utilFactory.getNumberChecker();
        PathCreator pathCreator = utilFactory.getPathCreator();

        String path = pathCreator.getError();

        String idUser = request.getParameter("idUser");
        String bannedStatus = request.getParameter("bann");

        ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
        AdminService adminService = serviceFactory.getAdminServiceImpl();

        try {
            if (numberChecker.isNumber(idUser)){
                int idUserInt = Integer.parseInt(idUser);
                boolean bannedStatusBoolean = Boolean.parseBoolean(bannedStatus);
                adminService.changeBannedStatus(idUserInt, bannedStatusBoolean);
                path = pathCreator.getViewAllUsers(request.getContextPath());
            }
        } catch (ServiceException e){
            throw new CommandException(e);
        }
        return path;
    }
}
