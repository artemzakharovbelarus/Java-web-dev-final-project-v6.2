package by.epam.corporate_education.controller.command.impl;

import by.epam.corporate_education.controller.command.Command;
import by.epam.corporate_education.controller.command.CommandException;
import by.epam.corporate_education.controller.util.ControllerUtilFactory;
import by.epam.corporate_education.controller.util.ParameterName;
import by.epam.corporate_education.controller.util.api.AttributesInitializer;
import by.epam.corporate_education.controller.util.api.ControllerValueChecker;
import by.epam.corporate_education.controller.util.api.HttpRequestResponseKeeper;
import by.epam.corporate_education.controller.util.api.PathCreator;
import by.epam.corporate_education.entity.Training;
import by.epam.corporate_education.service.ServiceFactory;
import by.epam.corporate_education.service.api.TrainerService;
import by.epam.corporate_education.service.exception.ServiceException;
import by.epam.corporate_education.util.annotation.ConstructorForTest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ViewAllTrainerTrainingsCommand implements Command {

    private ControllerUtilFactory utilFactory = ControllerUtilFactory.getINSTANCE();
    private ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private TrainerService trainerService;

    public ViewAllTrainerTrainingsCommand(){
        trainerService = serviceFactory.getTrainerServiceImpl();
    }

    @ConstructorForTest
    public ViewAllTrainerTrainingsCommand(TrainerService trainerService, ControllerUtilFactory utilFactory){
        this.trainerService = trainerService;
        this.utilFactory = utilFactory;
    }

    @Override
    public String execute() throws CommandException {
        AttributesInitializer attributesInitializer = utilFactory.getAttributesInitializer();
        PathCreator pathCreator = utilFactory.getPathCreator();
        HttpRequestResponseKeeper keeper = utilFactory.getHttpRequestResponseKeeper();
        ControllerValueChecker valueChecker = utilFactory.getControllerValueChecker();

        HttpServletRequest request = keeper.getRequest();
        HttpServletResponse response = keeper.getResponse();

        String path = pathCreator.getError();

        HttpSession session = request.getSession();
        Integer idTrainer = (Integer) session.getAttribute(ParameterName.ID_USER);
        int idStatus = (Integer) session.getAttribute(ParameterName.STATUS);

        List<Training> trainings = new ArrayList<>();
        try{
            if (valueChecker.isTeacher(idStatus)) {
                trainings = trainerService.getAllTrainerTrainings(idTrainer);
                attributesInitializer.setRequestAttributesTrainings(request, trainings);
                path = pathCreator.getTrainerTrainings();
            } else {
                path = pathCreator.getError();
            }
        } catch (ServiceException e){
            throw new CommandException(e);
        }
        return path;
    }
}
