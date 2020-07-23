package by.epam.corporate_education.service.impl;

import by.epam.corporate_education.dao.DAOFactory;
import by.epam.corporate_education.dao.api.DislikeDAO;
import by.epam.corporate_education.dao.api.LikeDAO;
import by.epam.corporate_education.dao.api.TrainingDAO;
import by.epam.corporate_education.dao.api.UserDAO;
import by.epam.corporate_education.dao.exception.DAOException;
import by.epam.corporate_education.entity.Like;
import by.epam.corporate_education.entity.Training;
import by.epam.corporate_education.entity.User;
import by.epam.corporate_education.service.api.AdminService;
import by.epam.corporate_education.service.exception.ServiceException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdminServiceImpl implements AdminService {

    private DAOFactory daoFactory = DAOFactory.getINSTANCE();

    private LikeDAO likeDAO = daoFactory.getLikeDAOImpl();
    private DislikeDAO dislikeDAO = daoFactory.getDislikeDAOImpl();
    private UserDAO userDAO = daoFactory.getUserDAOImpl();
    private TrainingDAO trainingDAO = daoFactory.getTrainingDAOImpl();

    public AdminServiceImpl(){}

    //annotation
    public AdminServiceImpl(LikeDAO likeDAO, DislikeDAO dislikeDAO, UserDAO userDAO, TrainingDAO trainingDAO){
        this.likeDAO = likeDAO;
        this.dislikeDAO = dislikeDAO;
        this.userDAO = userDAO;
        this.trainingDAO = trainingDAO;
    }

    @Override
    public int getLikesAmount(int idTraining) throws ServiceException {

        int likesAmount = 0;
        try {
            likesAmount = likeDAO.getTrainingLikesAmount(idTraining);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return likesAmount;
    }

    @Override
    public int getDislikesAmount(int idTraining) throws ServiceException {

        int dislikesAmount = 0;
        try {
            dislikesAmount = dislikeDAO.getTrainingDislikesAmount(idTraining);
        } catch (DAOException e){
            throw new ServiceException();
        }
        return dislikesAmount;
    }

    @Override
    public void changeBannedStatus(int idUser, boolean status) throws ServiceException {
        try{
            userDAO.changeBannedStatus(idUser, !status);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateTrainingValues(int idTraining, String title, String requirements, String information, String city,
                                    int hoursAmount, int minMembers, int maxMembers, LocalDate startDate, LocalDate endDate,
                                    String trainingPhoto, int idTrainer) throws ServiceException{

        /*
         * validation
         */
        Training training = new Training(idTraining, title, requirements, information, city, hoursAmount, minMembers,
                maxMembers, startDate, endDate, trainingPhoto, idTrainer);

        try{
            trainingDAO.updateTraining(training);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int changeTrainingDeletedStatus(int idTraining) throws ServiceException {
        int result = 0;
        try{
            result = trainingDAO.changeDeletedStatus(idTraining);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        List<User> users = new ArrayList<>();

        try{
            users = userDAO.getAllUsers();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return users;
    }

    @Override
    public User getUserInformation(int idUser) throws ServiceException {
        User user = new User();
        try {
            user = userDAO.getAllUserInformation(idUser);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user;
    }
}
