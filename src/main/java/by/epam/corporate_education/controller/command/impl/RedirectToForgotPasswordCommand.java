package by.epam.corporate_education.controller.command.impl;

import by.epam.corporate_education.controller.command.Command;
import by.epam.corporate_education.controller.command.CommandException;
import by.epam.corporate_education.controller.command.util.CommandUtilFactory;
import by.epam.corporate_education.controller.command.util.api.PathCreator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectToForgotPasswordCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandUtilFactory utilFactory = CommandUtilFactory.getINSTANCE();
        PathCreator pathCreator = utilFactory.getPathCreator();

        String path = pathCreator.getForwardForgotPassword();
        return path;
    }
}
