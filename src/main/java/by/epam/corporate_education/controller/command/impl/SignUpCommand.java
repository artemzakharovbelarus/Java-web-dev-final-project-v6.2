package by.epam.corporate_education.controller.command.impl;

import by.epam.corporate_education.controller.command.Command;
import by.epam.corporate_education.controller.command.CommandException;
import by.epam.corporate_education.controller.util.ControllerUtilFactory;
import by.epam.corporate_education.controller.util.ParameterName;
import by.epam.corporate_education.controller.util.api.AttributesInitializer;
import by.epam.corporate_education.controller.util.api.HttpRequestResponseKeeper;
import by.epam.corporate_education.controller.util.api.PathCreator;
import by.epam.corporate_education.service.ServiceFactory;
import by.epam.corporate_education.service.api.UserService;
import by.epam.corporate_education.service.exception.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignUpCommand implements Command {

    private ControllerUtilFactory utilFactory = ControllerUtilFactory.getINSTANCE();
    private ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private UserService userService;

    public SignUpCommand(){
        userService = serviceFactory.getUserServiceImpl();
    }

    //annotation
    public SignUpCommand(UserService userService, ControllerUtilFactory utilFactory){
        this.userService = userService;
        this.utilFactory = utilFactory;
    }

    @Override
    public String execute() throws CommandException {
        PathCreator pathCreator = utilFactory.getPathCreator();
        AttributesInitializer attributesInitializer = utilFactory.getAttributesInitializer();
        HttpRequestResponseKeeper keeper = utilFactory.getHttpRequestResponseKeeper();

        HttpServletRequest request = keeper.getRequest();
        HttpServletResponse response = keeper.getResponse();


        String path = pathCreator.getError();

        String username = request.getParameter(ParameterName.USERNAME);
        String email = request.getParameter(ParameterName.EMAIL);
        String password = request.getParameter(ParameterName.PASSWORD);
        String confirmedPassword = request.getParameter(ParameterName.CONFIRMED_PASSWORD);
        /*
         * undone!
         */

        HttpSession session = request.getSession();
        try {
            //checkSession
            userService.signUp(username, email, password, confirmedPassword);
            path = pathCreator.getSignIn();
            attributesInitializer.setSessionAttributesMessage(session, ParameterName.SUCCESS_SIGN_UP,
                    ParameterName.MESSAGE_SUCCESS_SIGN_UP);
            session.setMaxInactiveInterval(1);
        } catch (InvalidUsernameException e) {
            path = pathCreator.getSignUp();
            attributesInitializer.setSessionAttributesMessage(session, ParameterName.INVALID_USERNAME,
                    ParameterName.MESSAGE_INVALID_USERNAME);
            session.setMaxInactiveInterval(1);
        } catch (InvalidEmailException e) {
            path = pathCreator.getSignUp();
            attributesInitializer.setSessionAttributesMessage(session,ParameterName.INVALID_EMAIL,
                    ParameterName.MESSAGE_INVALID_EMAIL);
            session.setMaxInactiveInterval(1);
        } catch (InvalidPasswordException e){
            path = pathCreator.getSignUp();
            attributesInitializer.setSessionAttributesMessage(session, ParameterName.INVALID_PASSWORD,
                    ParameterName.MESSAGE_INVALID_PASSWORD);
            session.setMaxInactiveInterval(1);
        } catch (EquivalencePasswordException e){
            path = pathCreator.getSignUp();
            attributesInitializer.setSessionAttributesMessage(session, ParameterName.NO_EQUALS_PASSWORD,
                    ParameterName.MESSAGE_NOT_EQUALS_PASSWORDS);
            session.setMaxInactiveInterval(1);
        } catch (ServiceException e){
            session.setMaxInactiveInterval(1);
            throw new CommandException(e);
        }
        return path;
    }
}
