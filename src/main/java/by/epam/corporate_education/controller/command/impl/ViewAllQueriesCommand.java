package by.epam.corporate_education.controller.command.impl;

import by.epam.corporate_education.controller.command.Command;
import by.epam.corporate_education.controller.command.CommandException;
import by.epam.corporate_education.controller.util.ControllerUtilFactory;
import by.epam.corporate_education.controller.util.ParameterName;
import by.epam.corporate_education.controller.util.api.AttributesInitializer;
import by.epam.corporate_education.controller.util.api.HttpRequestResponseKeeper;
import by.epam.corporate_education.controller.util.api.ControllerValueChecker;
import by.epam.corporate_education.controller.util.api.PathCreator;
import by.epam.corporate_education.entity.Query;
import by.epam.corporate_education.service.ServiceFactory;
import by.epam.corporate_education.service.api.UserService;
import by.epam.corporate_education.service.exception.ServiceException;
import by.epam.corporate_education.util.annotation.ConstructorForTest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ViewAllQueriesCommand implements Command {

    private ControllerUtilFactory utilFactory = ControllerUtilFactory.getINSTANCE();
    private ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private UserService userService;

    public ViewAllQueriesCommand(){
        userService = serviceFactory.getUserServiceImpl();
    }

    @ConstructorForTest
    public ViewAllQueriesCommand(UserService userService, ControllerUtilFactory utilFactory){
        this.userService = userService;
        this.utilFactory = utilFactory;
    }

    @Override
    public String execute() throws CommandException {
        ControllerValueChecker valueChecker = utilFactory.getControllerValueChecker();
        AttributesInitializer attributesInitializer = utilFactory.getAttributesInitializer();
        PathCreator pathCreator = utilFactory.getPathCreator();
        HttpRequestResponseKeeper keeper = utilFactory.getHttpRequestResponseKeeper();

        HttpServletRequest request = keeper.getRequest();
        HttpServletResponse response = keeper.getResponse();


        String path = pathCreator.getError();

        HttpSession session = request.getSession();
        Integer idUser = (Integer) session.getAttribute(ParameterName.ID_USER);
        int idStatus = (Integer) session.getAttribute(ParameterName.STATUS);

        List<Query> queries = new ArrayList<>();
        try {
            if (valueChecker.isStudent(idStatus)) {
                queries = userService.viewAllQueries(idUser);
                attributesInitializer.setRequestAttributesQueries(request, queries);
                path = pathCreator.getQueries();
            } else {
                path = pathCreator.getError();
            }
        } catch (ServiceException e){
            throw new CommandException(e);
        }
        return path;
    }
}
