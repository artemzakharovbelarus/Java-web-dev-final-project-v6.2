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
import by.epam.corporate_education.service.api.UserService;
import by.epam.corporate_education.service.exception.ServiceException;
import by.epam.corporate_education.util.annotation.ConstructorForTest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ForwardEditProfileCommand implements Command {

    private ControllerUtilFactory utilFactory = ControllerUtilFactory.getINSTANCE();
    private ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private UserService userService = serviceFactory.getUserServiceImpl();

    public ForwardEditProfileCommand(){
        userService = serviceFactory.getUserServiceImpl();
    }

    @ConstructorForTest
    public ForwardEditProfileCommand(UserService userService, ControllerUtilFactory utilFactory){
        this.userService = userService;
        this.utilFactory = utilFactory;
    }

    @Override
    public String execute() throws CommandException {
        ControllerValueChecker valueChecker = utilFactory.getControllerValueChecker();
        PathCreator pathCreator = utilFactory.getPathCreator();
        HttpRequestResponseKeeper keeper = utilFactory.getHttpRequestResponseKeeper();
        AttributesInitializer attributesInitializer = utilFactory.getAttributesInitializer();

        HttpServletRequest request = keeper.getRequest();
        HttpServletResponse response = keeper.getResponse();

        String path = pathCreator.getError();

        HttpSession session = request.getSession();
        Integer idUser = (Integer) session.getAttribute(ParameterName.ID_USER);
        int idStatus = (Integer) session.getAttribute(ParameterName.STATUS);

        User user = new User();
        try {
            if (valueChecker.isAnyUser(idStatus)){
                user = userService.viewProfile(idUser);
                attributesInitializer.setRequestAttributesUser(request, user);
                path = pathCreator.getEditProfile();
            } else {
                path = pathCreator.getError();
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return path;
    }
}
