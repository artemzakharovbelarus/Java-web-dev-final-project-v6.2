package by.epam.corporate_education.controller.command.impl;

import by.epam.corporate_education.controller.command.Command;
import by.epam.corporate_education.controller.command.CommandException;
import by.epam.corporate_education.controller.util.ControllerUtilFactory;
import by.epam.corporate_education.controller.util.api.PathCreator;

public class RedirectToSignUpCommand implements Command {

    private ControllerUtilFactory utilFactory = ControllerUtilFactory.getINSTANCE();

    public RedirectToSignUpCommand(){}

    //annotation
    public RedirectToSignUpCommand(ControllerUtilFactory utilFactory){
        this.utilFactory = utilFactory;
    }

    @Override
    public String execute() throws CommandException {
        PathCreator pathCreator = utilFactory.getPathCreator();

        String path = pathCreator.getForwardSignUp();
        return path;
    }
}
