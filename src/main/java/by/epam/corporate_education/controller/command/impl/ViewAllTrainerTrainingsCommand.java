package by.epam.corporate_education.controller.command.impl;

import by.epam.corporate_education.controller.command.Command;
import by.epam.corporate_education.controller.command.CommandException;
import by.epam.corporate_education.controller.command.util.CommandUtilFactory;
import by.epam.corporate_education.controller.command.util.api.AttributesInitializer;
import by.epam.corporate_education.controller.command.util.api.PathCreator;
import by.epam.corporate_education.entity.Training;
import by.epam.corporate_education.service.ServiceFactory;
import by.epam.corporate_education.service.api.TrainerService;
import by.epam.corporate_education.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ViewAllTrainerTrainingsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandUtilFactory utilFactory = CommandUtilFactory.getINSTANCE();
        AttributesInitializer attributesInitializer = utilFactory.getAttributesInitializer();
        PathCreator pathCreator = utilFactory.getPathCreator();

        String path = pathCreator.getError();

        HttpSession session = request.getSession();
        Integer idTrainer = (Integer) session.getAttribute("idUser");

        ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
        TrainerService trainerService = serviceFactory.getTrainerServiceImpl();

        List<Training> trainings = new ArrayList<>();
        try{

            trainings = trainerService.getAllTrainerTrainings(idTrainer);
            attributesInitializer.setRequestAttributesTrainings(request, trainings);
            path = pathCreator.getTrainerTrainings();
        } catch (ServiceException e){
            throw new CommandException(e);
        }
        return path;
    }
}
