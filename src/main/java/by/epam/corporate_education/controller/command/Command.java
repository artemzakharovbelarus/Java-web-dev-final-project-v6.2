package by.epam.corporate_education.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    public String execute() throws CommandException;
}
