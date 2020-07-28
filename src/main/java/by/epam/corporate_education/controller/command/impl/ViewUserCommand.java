package by.epam.corporate_education.controller.command.impl;

import by.epam.corporate_education.controller.command.Command;
import by.epam.corporate_education.controller.command.CommandException;
import by.epam.corporate_education.controller.util.ControllerUtilFactory;
import by.epam.corporate_education.controller.util.ParameterName;
import by.epam.corporate_education.controller.util.api.AttributesInitializer;
import by.epam.corporate_education.controller.util.api.HttpRequestResponseKeeper;
import by.epam.corporate_education.controller.util.api.ControllerValueChecker;
import by.epam.corporate_education.controller.util.api.PathCreator;
import by.epam.corporate_education.entity.User;
import by.epam.corporate_education.service.ServiceFactory;
import by.epam.corporate_education.service.api.AdminService;
import by.epam.corporate_education.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ViewUserCommand implements Command {

    private ControllerUtilFactory utilFactory = ControllerUtilFactory.getINSTANCE();
    private ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private AdminService adminService;

    public ViewUserCommand(){
        adminService = serviceFactory.getAdminServiceImpl();
    }

    //annotation
    public ViewUserCommand(AdminService adminService, ControllerUtilFactory utilFactory){
        this.adminService = adminService;
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
        String id = request.getParameter(ParameterName.ID_USER);

        User user = new User();
        try{
            if (valueChecker.isNumber(id) && valueChecker.isAdmin(idStatus)) {
                user = adminService.getUserInformation(Integer.parseInt(id));
                attributesInitializer.setRequestAttributesUser(request, user);
                path = pathCreator.getUserPage();
            } else {
                path = pathCreator.getError();
            }
        } catch (ServiceException e){
            throw new CommandException(e);
        }
        return path;
    }
}
