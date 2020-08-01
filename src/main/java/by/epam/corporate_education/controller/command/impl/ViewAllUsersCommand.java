package by.epam.corporate_education.controller.command.impl;

import by.epam.corporate_education.controller.command.Command;
import by.epam.corporate_education.controller.command.CommandException;
import by.epam.corporate_education.controller.util.ControllerUtilFactory;
import by.epam.corporate_education.controller.util.ParameterName;
import by.epam.corporate_education.controller.util.api.AttributesInitializer;
import by.epam.corporate_education.controller.util.api.ControllerValueChecker;
import by.epam.corporate_education.controller.util.api.HttpRequestResponseKeeper;
import by.epam.corporate_education.controller.util.api.PathCreator;
import by.epam.corporate_education.entity.User;
import by.epam.corporate_education.service.ServiceFactory;
import by.epam.corporate_education.service.api.AdminService;
import by.epam.corporate_education.service.exception.ServiceException;
import by.epam.corporate_education.util.annotation.ConstructorForTest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ViewAllUsersCommand implements Command {

    private ControllerUtilFactory utilFactory = ControllerUtilFactory.getINSTANCE();
    private ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private AdminService adminService;

    public ViewAllUsersCommand(){
        adminService = serviceFactory.getAdminServiceImpl();
    }

    @ConstructorForTest
    public ViewAllUsersCommand(AdminService adminService, ControllerUtilFactory utilFactory){
        this.adminService = adminService;
        this.utilFactory = utilFactory;
    }

    @Override
    public String execute() throws CommandException {
        PathCreator pathCreator = utilFactory.getPathCreator();
        AttributesInitializer attributesInitializer = utilFactory.getAttributesInitializer();
        HttpRequestResponseKeeper keeper = utilFactory.getHttpRequestResponseKeeper();
        ControllerValueChecker valueChecker = utilFactory.getControllerValueChecker();

        String path = pathCreator.getError();

        HttpServletRequest request = keeper.getRequest();
        HttpServletResponse response = keeper.getResponse();

        HttpSession session = request.getSession();
        int idStatus = (Integer) session.getAttribute(ParameterName.STATUS);

        List<User> users = new ArrayList<>();
        try{
            if (valueChecker.isAdmin(idStatus)) {
                users = adminService.viewAllUsers();
                attributesInitializer.setRequestAttributesUsers(request, users);
                path = pathCreator.getUsersPage();
            } else {
                path = pathCreator.getError();
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return path;
    }
}
