package by.epam.corporate_education.controller.command.impl;

import by.epam.corporate_education.controller.command.Command;
import by.epam.corporate_education.controller.command.CommandException;
import by.epam.corporate_education.controller.util.ControllerUtilFactory;
import by.epam.corporate_education.controller.util.ParameterName;
import by.epam.corporate_education.controller.util.api.ControllerValueChecker;
import by.epam.corporate_education.controller.util.api.HttpRequestResponseKeeper;
import by.epam.corporate_education.controller.util.api.PathCreator;
import by.epam.corporate_education.service.ServiceFactory;
import by.epam.corporate_education.service.api.AdminService;
import by.epam.corporate_education.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

public class EditTrainingCommand implements Command {

    private ControllerUtilFactory utilFactory = ControllerUtilFactory.getINSTANCE();
    private ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private AdminService adminService;

    public EditTrainingCommand(){
        adminService = serviceFactory.getAdminServiceImpl();
    }

    //annotation
    public EditTrainingCommand(AdminService adminService, ControllerUtilFactory utilFactory){
        this.adminService = adminService;
        this.utilFactory = utilFactory;
    }

    @Override
    public String execute() throws CommandException {
        ControllerValueChecker controllerValueChecker = utilFactory.getControllerValueChecker();
        PathCreator pathCreator = utilFactory.getPathCreator();
        HttpRequestResponseKeeper keeper = utilFactory.getHttpRequestResponseKeeper();

        HttpServletRequest request = keeper.getRequest();
        HttpServletResponse response = keeper.getResponse();

        String path = pathCreator.getError();
        String idTraining = request.getParameter(ParameterName.ID_TRAINING);

        String title = request.getParameter(ParameterName.TITLE);
        String requirements = request.getParameter(ParameterName.REQUIREMENTS);
        String information = request.getParameter(ParameterName.INFORMATION);
        String city = request.getParameter(ParameterName.CITY);
        int hoursAmount = Integer.parseInt(request.getParameter(ParameterName.HOURS_AMOUNT));
        int minMembers = Integer.parseInt(request.getParameter(ParameterName.MIN_MEMBERS));
        int maxMembers = Integer.parseInt(request.getParameter(ParameterName.MAX_MEMBERS));
        LocalDate startDate = LocalDate.parse(request.getParameter(ParameterName.START_DATE));
        LocalDate endDate = LocalDate.parse(request.getParameter(ParameterName.END_DATE));
        int idTrainer = Integer.parseInt(request.getParameter(ParameterName.ID_TRAINER));
        String trainingPhoto = request.getParameter(ParameterName.TRAINING_PHOTO);

        try{
            if (controllerValueChecker.isNumber(idTraining)){
                int idTrainingInt = Integer.parseInt(idTraining);
                adminService.updateTrainingValues(idTrainingInt, title, requirements, information, city, hoursAmount,
                        minMembers, maxMembers, startDate, endDate, trainingPhoto, idTrainer);
                path = pathCreator.getViewTraining(request.getContextPath(), idTrainingInt);
            }
        } catch (ServiceException e){
            throw new CommandException(e);
        }
        return path;
    }
}
