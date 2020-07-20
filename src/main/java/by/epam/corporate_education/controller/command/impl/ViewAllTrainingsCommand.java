package by.epam.corporate_education.controller.command.impl;

import by.epam.corporate_education.controller.command.Command;
import by.epam.corporate_education.controller.command.CommandException;
import by.epam.corporate_education.controller.command.util.CommandUtilFactory;
import by.epam.corporate_education.controller.command.util.api.AttributesInitializer;
import by.epam.corporate_education.controller.command.util.api.PaginationCurrentPage;
import by.epam.corporate_education.entity.Query;
import by.epam.corporate_education.entity.Training;
import by.epam.corporate_education.service.ServiceFactory;
import by.epam.corporate_education.service.api.UserService;
import by.epam.corporate_education.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ViewAllTrainingsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String path = "error";
        List<Training> trainings = new ArrayList<>();

        CommandUtilFactory utilFactory = CommandUtilFactory.getINSTANCE();
        AttributesInitializer attributesInitializer = utilFactory.getAttributesInitializer();
        PaginationCurrentPage paginationCurrentPage = utilFactory.getPaginationCurrentPage();
        ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
        UserService userService = serviceFactory.getUserServiceImpl();

        try{
            trainings = userService.viewAllTrainings();
            attributesInitializer.setRequestAttributesTrainings(request, trainings);

            path = "trainings-page";
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return path;
    }
}
