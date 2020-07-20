package by.epam.corporate_education.controller.command.impl;

import by.epam.corporate_education.controller.command.Command;
import by.epam.corporate_education.controller.command.CommandException;
import by.epam.corporate_education.controller.command.util.CommandUtilFactory;
import by.epam.corporate_education.controller.command.util.api.AttributesInitializer;
import by.epam.corporate_education.entity.User;
import by.epam.corporate_education.service.ServiceFactory;
import by.epam.corporate_education.service.api.AdminService;
import by.epam.corporate_education.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ViewAllUsersCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String path = "error";
        ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
        AdminService adminService = serviceFactory.getAdminServiceImpl();

        CommandUtilFactory utilFactory = CommandUtilFactory.getINSTANCE();
        AttributesInitializer attributesInitializer = utilFactory.getAttributesInitializer();
        List<User> users = new ArrayList<>();
        try{
            users = adminService.getAllUsers();
            attributesInitializer.setRequestAttributesUsers(request, users);

            path = "users-page";
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return path;
    }
}
