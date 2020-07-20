package by.epam.corporate_education.controller.command.impl;

import by.epam.corporate_education.controller.command.Command;
import by.epam.corporate_education.controller.command.CommandException;
import by.epam.corporate_education.controller.command.util.CommandUtilFactory;
import by.epam.corporate_education.controller.command.util.api.AttributesInitializer;
import by.epam.corporate_education.controller.command.util.api.NumberChecker;
import by.epam.corporate_education.controller.command.util.api.PathCreator;
import by.epam.corporate_education.entity.Query;
import by.epam.corporate_education.service.ServiceFactory;
import by.epam.corporate_education.service.api.UserService;
import by.epam.corporate_education.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ViewAllQueriesCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandUtilFactory utilFactory = CommandUtilFactory.getINSTANCE();
        NumberChecker numberChecker = utilFactory.getNumberChecker();
        AttributesInitializer attributesInitializer = utilFactory.getAttributesInitializer();
        PathCreator pathCreator = utilFactory.getPathCreator();

        String path = pathCreator.getError();
        String idUser = request.getParameter("idUser");


        ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
        UserService userService = serviceFactory.getUserServiceImpl();
        List<Query> queries = new ArrayList<>();
        try {
            if (numberChecker.isNumber(idUser)){
                int idUserInt = Integer.parseInt(idUser);
                queries = userService.getAllQueries(idUserInt);
                attributesInitializer.setRequestAttributesQueries(request, queries);
                path = pathCreator.getQueries();
            }
        } catch (ServiceException e){
            throw new CommandException(e);
        }
        return path;
    }
}
