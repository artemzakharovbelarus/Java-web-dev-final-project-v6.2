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

public class SignOutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandUtilFactory utilFactory = CommandUtilFactory.getINSTANCE();
        NumberChecker numberChecker = utilFactory.getNumberChecker();
        PathCreator pathCreator = utilFactory.getPathCreator();

        String path = pathCreator.getError();

        String idUser = request.getParameter("idUser");
        String onlineStatus = request.getParameter("online");
        HttpSession session = request.getSession();

        ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
        UserService userService = serviceFactory.getUserServiceImpl();
        try {
            if (numberChecker.isNumber(idUser)){
                int idUserInt = Integer.parseInt(idUser);
                boolean onlineStatusBoolean = Boolean.parseBoolean(onlineStatus);
                userService.signOut(idUserInt, onlineStatusBoolean);
                session.invalidate();
                path = pathCreator.getSignIn();
            }
        } catch (ServiceException e){
            throw new CommandException(e);
        }
        return path;
    }
}
