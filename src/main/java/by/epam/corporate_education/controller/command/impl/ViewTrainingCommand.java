package by.epam.corporate_education.controller.command.impl;

import by.epam.corporate_education.controller.command.Command;
import by.epam.corporate_education.controller.command.CommandException;
import by.epam.corporate_education.controller.util.ControllerUtilFactory;
import by.epam.corporate_education.controller.util.ParameterName;
import by.epam.corporate_education.controller.util.api.AttributesInitializer;
import by.epam.corporate_education.controller.util.api.ControllerValueChecker;
import by.epam.corporate_education.controller.util.api.HttpRequestResponseKeeper;
import by.epam.corporate_education.controller.util.api.PathCreator;
import by.epam.corporate_education.entity.Query;
import by.epam.corporate_education.entity.Training;
import by.epam.corporate_education.service.ServiceFactory;
import by.epam.corporate_education.service.api.AdminService;
import by.epam.corporate_education.service.api.UserService;
import by.epam.corporate_education.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ViewTrainingCommand implements Command {

    private ControllerUtilFactory utilFactory = ControllerUtilFactory.getINSTANCE();
    private ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private AdminService adminService;
    private UserService userService;

    public ViewTrainingCommand(){
        adminService = serviceFactory.getAdminServiceImpl();
        userService = serviceFactory.getUserServiceImpl();
    }

    //annotation
    public ViewTrainingCommand(AdminService adminService, UserService userService, ControllerUtilFactory utilFactory){
        this.adminService = adminService;
        this.userService = userService;
        this.utilFactory = utilFactory;
    }

    @Override
    public String execute() throws CommandException {
        ControllerValueChecker controllerValueChecker = utilFactory.getControllerValueChecker();
        AttributesInitializer attributesInitializer = utilFactory.getAttributesInitializer();
        PathCreator pathCreator = utilFactory.getPathCreator();
        HttpRequestResponseKeeper keeper = utilFactory.getHttpRequestResponseKeeper();

        HttpServletRequest request = keeper.getRequest();
        HttpServletResponse response = keeper.getResponse();

        String path = pathCreator.getError();

        String idTraining = request.getParameter(ParameterName.ID_TRAINING);
        HttpSession session = request.getSession();
        Integer idUser = (Integer) session.getAttribute(ParameterName.ID_USER);

        Training training = new Training();
        Query query = new Query();
        boolean likeEnabled = false;
        boolean dislikeEnabled = false;
        int likesAmount = 0;
        int dislikesAmount = 0;
        try{
            if (controllerValueChecker.isNumber(idTraining)) {
                int idTrainingInt = Integer.parseInt(idTraining);

                training = userService.getTraining(idTrainingInt);
                attributesInitializer.setRequestAttributesTraining(request, training);

                query = userService.getQueryByIdTrainingIdUser(idTrainingInt, idUser);
                attributesInitializer.setRequestAttributesQuery(request, query.getIdQuery());

                likeEnabled = userService.isLikeEnabled(idTrainingInt, idUser);
                attributesInitializer.setRequestAttributesLike(request, likeEnabled);

                dislikeEnabled = userService.isDislikeEnabled(idTrainingInt, idUser);
                attributesInitializer.setRequestAttributesDislike(request, dislikeEnabled);

                likesAmount = adminService.getLikesAmount(idTrainingInt);
                attributesInitializer.setRequestAttributesLikes(request, likesAmount);

                dislikesAmount = adminService.getDislikesAmount(idTrainingInt);
                attributesInitializer.setRequestAttributesDislikes(request, dislikesAmount);

                path = pathCreator.getTrainingPage();
            }
        } catch (ServiceException e){
            throw new CommandException(e);
        }
        return path;
    }
}
