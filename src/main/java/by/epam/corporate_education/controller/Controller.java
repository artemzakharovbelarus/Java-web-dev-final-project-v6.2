package by.epam.corporate_education.controller;

import by.epam.corporate_education.controller.command.Command;
import by.epam.corporate_education.controller.command.CommandException;
import by.epam.corporate_education.controller.command.factory.CommandFactory;
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
        String action = request.getParameter("command");
        try {
            String path = getPath(action, request, response);
            response.sendRedirect(path);
        } catch (CommandException e){
            log.error(e);
            response.sendRedirect("error");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String action = request.getParameter("command");
        try{
            String path = getPath(action, request, response);
            RequestDispatcher dispatcher = request.getRequestDispatcher(path);
            dispatcher.forward(request, response);
        } catch (CommandException e){
            log.error(e);
            response.sendRedirect("error");
        }
    }

    private String getPath(String action, HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandFactory commandFactory = CommandFactory.getINSTANCE();
        Command command = commandFactory.createCommand(action);
        String path = command.execute(request, response);

        return path;
    }
}
