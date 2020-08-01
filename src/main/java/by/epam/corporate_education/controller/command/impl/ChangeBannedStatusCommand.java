package by.epam.corporate_education.controller.command.impl;

import by.epam.corporate_education.controller.command.Command;
import by.epam.corporate_education.controller.command.CommandException;
import by.epam.corporate_education.controller.util.ControllerUtilFactory;
import by.epam.corporate_education.controller.util.ParameterName;
import by.epam.corporate_education.controller.util.api.ControllerValueChecker;
import by.epam.corporate_education.controller.util.api.HttpRequestResponseKeeper;
import by.epam.corporate_education.controller.util.api.PathCreator;
import by.epam.corporate_education.service.ServiceFactory;
import by.epam.corporate_education.service.api.AdminService;
import by.epam.corporate_education.service.exception.ServiceException;
import by.epam.corporate_education.util.annotation.ConstructorForTest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeBannedStatusCommand implements Command {

    private ControllerUtilFactory utilFactory = ControllerUtilFactory.getINSTANCE();
    private ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private AdminService adminService;

    public ChangeBannedStatusCommand(){
        adminService = serviceFactory.getAdminServiceImpl();
    }

    @ConstructorForTest
    public ChangeBannedStatusCommand(AdminService adminService, ControllerUtilFactory utilFactory){
        this.adminService = adminService;
        this.utilFactory = utilFactory;
    }

    @Override
    public String execute() throws CommandException {
        ControllerValueChecker controllerValueChecker = utilFactory.getControllerValueChecker();
        PathCreator pathCreator = utilFactory.getPathCreator();
        HttpRequestResponseKeeper keeper = utilFactory.getHttpRequestResponseKeeper();

        HttpServletRequest request = keeper.getRequest();
        HttpServletResponse response = keeper.getResponse();

        String path = pathCreator.getError();

        String idUser = request.getParameter(ParameterName.ID_USER);
        String bannedStatus = request.getParameter(ParameterName.BANN);

        try {
            if (controllerValueChecker.isNumber(idUser)){
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
