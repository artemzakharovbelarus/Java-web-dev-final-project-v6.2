package by.epam.corporate_education.controller.command.impl;

import by.epam.corporate_education.controller.command.Command;
import by.epam.corporate_education.controller.command.CommandException;
import by.epam.corporate_education.controller.util.ControllerUtilFactory;
import by.epam.corporate_education.controller.util.ParameterName;
import by.epam.corporate_education.controller.util.api.AttributesInitializer;
import by.epam.corporate_education.controller.util.api.HttpRequestResponseKeeper;
import by.epam.corporate_education.controller.util.api.PathCreator;
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

    private ControllerUtilFactory utilFactory = ControllerUtilFactory.getINSTANCE();
    private ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private UserService userService;

    public SignInCommand(){
        userService = serviceFactory.getUserServiceImpl();
    }

    //annotation
    public SignInCommand(UserService userService, ControllerUtilFactory utilFactory){
        this.userService = userService;
        this.utilFactory = utilFactory;
    }

    @Override
    public String execute() throws CommandException {

        AttributesInitializer attributesInitializer = utilFactory.getAttributesInitializer();
        PathCreator pathCreator = utilFactory.getPathCreator();
        HttpRequestResponseKeeper keeper = utilFactory.getHttpRequestResponseKeeper();

        HttpServletRequest request = keeper.getRequest();
        HttpServletResponse response = keeper.getResponse();

        String path = pathCreator.getError();

        String username = request.getParameter(ParameterName.USERNAME);
        String password = request.getParameter(ParameterName.PASSWORD);

        HttpSession session = request.getSession();
        try {
            User user = userService.signIn(username, password);
            attributesInitializer.setSessionAttributesUser(session, user);
            path = pathCreator.getForwardMainPage(request.getContextPath());
        } catch (UserBannedException e) {
            path = pathCreator.getSignIn();
            attributesInitializer.setSessionAttributesMessage(session, ParameterName.USER_BANNED_MESSAGE,
                    ParameterName.LOCAL_USER_BANNED);
            session.setMaxInactiveInterval(1);
        } catch (InvalidSignInValuesException e){
            path = pathCreator.getSignIn();
            attributesInitializer.setSessionAttributesMessage(session, ParameterName.SIGN_IN_MESSAGE,
                    ParameterName.LOCAL_INVALID_SIGN_IN_VALUES);
            session.setMaxInactiveInterval(1);
        } catch (ServiceException e){
            session.setMaxInactiveInterval(1);
            throw new CommandException(e);
        }
        return path;
    }
}
