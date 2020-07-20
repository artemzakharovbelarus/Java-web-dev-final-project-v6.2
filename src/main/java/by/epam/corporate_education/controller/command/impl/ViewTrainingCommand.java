package by.epam.corporate_education.controller.command.impl;

import by.epam.corporate_education.controller.command.Command;
import by.epam.corporate_education.controller.command.CommandException;
import by.epam.corporate_education.controller.command.util.CommandUtilFactory;
import by.epam.corporate_education.controller.command.util.api.AttributesInitializer;
import by.epam.corporate_education.controller.command.util.api.NumberChecker;
import by.epam.corporate_education.controller.command.util.api.PathCreator;
import by.epam.corporate_education.entity.Dislike;
import by.epam.corporate_education.entity.Like;
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

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandUtilFactory utilFactory = CommandUtilFactory.getINSTANCE();
        NumberChecker numberChecker = utilFactory.getNumberChecker();
        AttributesInitializer attributesInitializer = utilFactory.getAttributesInitializer();
        PathCreator pathCreator = utilFactory.getPathCreator();

        String path = pathCreator.getError();

        String idTraining = request.getParameter("idTraining");
        HttpSession session = request.getSession();
        Integer idUser = (Integer) session.getAttribute("idUser");


        ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
        AdminService adminService = serviceFactory.getAdminServiceImpl();
        UserService userService = serviceFactory.getUserServiceImpl();

        Training training = new Training();
        Query query = new Query();
        boolean likeEnabled = false;
        boolean dislikeEnabled = false;
        int likesAmount = 0;
        int dislikesAmount = 0;
        try{
            if (numberChecker.isNumber(idTraining)) {
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
