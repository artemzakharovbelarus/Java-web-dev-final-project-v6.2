package by.epam.corporate_education.controller.command.impl;

import by.epam.corporate_education.controller.command.Command;
import by.epam.corporate_education.controller.command.CommandException;
import by.epam.corporate_education.controller.command.util.CommandUtilFactory;
import by.epam.corporate_education.controller.command.util.api.AttributesInitializer;
import by.epam.corporate_education.controller.command.util.api.PathCreator;
import by.epam.corporate_education.entity.User;
import by.epam.corporate_education.service.ServiceFactory;
import by.epam.corporate_education.service.api.UserService;
import by.epam.corporate_education.service.exception.InvalidSignInValuesException;
import by.epam.corporate_education.service.exception.ServiceException;
import by.epam.corporate_education.service.exception.UserBannedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignInCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandUtilFactory utilFactory = CommandUtilFactory.getINSTANCE();
        AttributesInitializer attributesInitializer = utilFactory.getAttributesInitializer();
        PathCreator pathCreator = utilFactory.getPathCreator();

        String path = pathCreator.getError();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();

        ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
        UserService userService = serviceFactory.getUserServiceImpl();
        try {
            User user = userService.signIn(username, password);
            attributesInitializer.setSessionAttributesUser(session, user);

            path = pathCreator.getForwardMainPage(request.getContextPath());
        } catch (UserBannedException e) {
            path = pathCreator.getSignIn();
            attributesInitializer.setSessionAttributesMessage(session, "user_banned_message",
                    "local.user-banned");
            session.setMaxInactiveInterval(1);
        } catch (InvalidSignInValuesException e){
            path = pathCreator.getSignIn();
            attributesInitializer.setSessionAttributesMessage(session, "sign_in_message",
                    "local.invalid-sign-in-values");
            session.setMaxInactiveInterval(1);
        } catch (ServiceException e){
            session.setMaxInactiveInterval(1);
            throw new CommandException(e);
        }
        return path;
    }
}
