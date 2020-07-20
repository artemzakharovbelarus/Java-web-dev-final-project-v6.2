package by.epam.corporate_education.controller.command.impl;

import by.epam.corporate_education.controller.command.Command;
import by.epam.corporate_education.controller.command.CommandException;
import by.epam.corporate_education.controller.command.util.CommandUtilFactory;
import by.epam.corporate_education.controller.command.util.api.AttributesInitializer;
import by.epam.corporate_education.controller.command.util.api.PathCreator;
import by.epam.corporate_education.service.ServiceFactory;
import by.epam.corporate_education.service.api.UserService;
import by.epam.corporate_education.service.exception.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignUpCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandUtilFactory utilFactory = CommandUtilFactory.getINSTANCE();
        PathCreator pathCreator = utilFactory.getPathCreator();
        AttributesInitializer attributesInitializer = utilFactory.getAttributesInitializer();

        String path = pathCreator.getError();

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmedPassword = request.getParameter("confirmed-password");
        /*
         * undone!
         */

        HttpSession session = request.getSession();
        ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
        UserService serviceImpl = serviceFactory.getUserServiceImpl();
        try {
            //checkSession
            serviceImpl.signUp(username, email, password, confirmedPassword);
            path = pathCreator.getSignIn();
            attributesInitializer.setSessionAttributesMessage(session, "success_sign_up",
                    "message.success-sign-up");
            session.setMaxInactiveInterval(1);
        } catch (InvalidUsernameException e) {
            path = pathCreator.getSignUp();
            attributesInitializer.setSessionAttributesMessage(session, "invalid_username",
                    "message.invalid-username");
            session.setMaxInactiveInterval(1);
        } catch (InvalidEmailException e) {
            path = pathCreator.getSignUp();
            attributesInitializer.setSessionAttributesMessage(session, "invalid_email",
                    "message.invalid-email");
            session.setMaxInactiveInterval(1);
        } catch (InvalidPasswordException e){
            path = pathCreator.getSignUp();
            attributesInitializer.setSessionAttributesMessage(session, "invalid_password",
                    "message.invalid-password");
            session.setMaxInactiveInterval(1);
        } catch (EquivalencePasswordException e){
            path = pathCreator.getSignUp();
            attributesInitializer.setSessionAttributesMessage(session, "no_equals_passwords",
                    "message.not-equals-passwords");
            session.setMaxInactiveInterval(1);
        } catch (ServiceException e){
            session.setMaxInactiveInterval(1);
            throw new CommandException(e);
        }
        return path;
    }
}
