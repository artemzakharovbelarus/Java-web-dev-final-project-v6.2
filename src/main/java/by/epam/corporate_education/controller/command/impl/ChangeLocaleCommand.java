package by.epam.corporate_education.controller.command.impl;

import by.epam.corporate_education.controller.command.Command;
import by.epam.corporate_education.controller.command.CommandException;
import by.epam.corporate_education.controller.command.util.CommandUtilFactory;
import by.epam.corporate_education.controller.command.util.api.PathCreator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeLocaleCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandUtilFactory commandUtilFactory = CommandUtilFactory.getINSTANCE();
        PathCreator pathCreator = commandUtilFactory.getPathCreator();


        String locale = request.getParameter("locale");
        HttpSession session = request.getSession(true);
        session.setAttribute("locale", locale);
        String path = pathCreator.getChangeLocale(request.getContextPath());
        return path;
    }
}
