package by.epam.corporate_education.controller;

import by.epam.corporate_education.controller.command.Command;
import by.epam.corporate_education.controller.command.CommandException;
import by.epam.corporate_education.controller.command.factory.CommandFactory;
import by.epam.corporate_education.controller.util.ControllerUtilFactory;
import by.epam.corporate_education.controller.util.ParameterName;
import by.epam.corporate_education.controller.util.api.PathCreator;
import lombok.extern.log4j.Log4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 8068924271078825528L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        ControllerUtilFactory utilFactory = ControllerUtilFactory.getINSTANCE();
        PathCreator pathCreator = utilFactory.getPathCreator();

        String action = request.getParameter(ParameterName.COMMAND);
            try {
                String path = getPath(action, request, response);
                response.sendRedirect(path);
            } catch (CommandException e) {
                log.error(e);
                response.sendRedirect(pathCreator.getError());
            }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        ControllerUtilFactory utilFactory = ControllerUtilFactory.getINSTANCE();
        PathCreator pathCreator = utilFactory.getPathCreator();

        String action = request.getParameter(ParameterName.COMMAND);
        try {
                String path = getPath(action, request, response);
                RequestDispatcher dispatcher = request.getRequestDispatcher(path);
                dispatcher.forward(request, response);
            } catch (CommandException e) {
                log.error(e);
                response.sendRedirect(pathCreator.getError());
            }
    }

    private String getPath(String action, HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandFactory commandFactory = CommandFactory.getINSTANCE();

        Command command = commandFactory.createCommand(action, request, response);
        String path = command.execute();

        return path;
    }
}
