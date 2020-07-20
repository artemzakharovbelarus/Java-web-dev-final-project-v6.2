package by.epam.corporate_education.controller.command.impl;

import by.epam.corporate_education.controller.command.Command;
import by.epam.corporate_education.controller.command.CommandException;
import by.epam.corporate_education.controller.command.util.CommandUtilFactory;
import by.epam.corporate_education.controller.command.util.api.AttributesInitializer;
import by.epam.corporate_education.controller.command.util.api.NumberChecker;
import by.epam.corporate_education.controller.command.util.api.PathCreator;
import by.epam.corporate_education.entity.User;
import by.epam.corporate_education.service.ServiceFactory;
import by.epam.corporate_education.service.api.AdminService;
import by.epam.corporate_education.service.exception.ServiceException;
import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewUserCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandUtilFactory utilFactory = CommandUtilFactory.getINSTANCE();
        NumberChecker numberChecker = utilFactory.getNumberChecker();
        PathCreator pathCreator = utilFactory.getPathCreator();
        AttributesInitializer attributesInitializer = utilFactory.getAttributesInitializer();

        String path = pathCreator.getError();
        String id = request.getParameter("idUser");

        ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
        AdminService adminService = serviceFactory.getAdminServiceImpl();
        User user = new User();
        try{
            if (numberChecker.isNumber(id)) {
                user = adminService.getUserInformation(Integer.parseInt(id));
                attributesInitializer.setRequestAttributesUser(request, user);
                path = pathCreator.getUserPage();
            }
        } catch (ServiceException e){
            throw new CommandException(e);
        }
        return path;
    }
}
