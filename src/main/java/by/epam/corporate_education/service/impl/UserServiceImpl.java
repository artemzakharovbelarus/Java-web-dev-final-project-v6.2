package by.epam.corporate_education.service.impl;

import by.epam.corporate_education.dao.DAOFactory;
import by.epam.corporate_education.dao.api.*;
import by.epam.corporate_education.dao.exception.*;
import by.epam.corporate_education.entity.*;
import by.epam.corporate_education.service.api.UserService;
import by.epam.corporate_education.service.exception.*;
import by.epam.corporate_education.service.util.ServiceUtilFactory;
import by.epam.corporate_education.service.util.ValidatorManager;
import by.epam.corporate_education.service.util.api.PasswordEncoder;
import by.epam.corporate_education.service.util.api.UserValidator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private DAOFactory daoFactory = DAOFactory.getINSTANCE();
    private ServiceUtilFactory utilFactory = ServiceUtilFactory.getINSTANCE();
    private ValidatorManager validatorManager = utilFactory.getValidatorManager();
    private PasswordEncoder passwordEncoder = utilFactory.getEncoder();

    @Override
    public void putOffLike(int idTraining, int idUser) throws ServiceException {
        LikeDAO likeDAO = daoFactory.getLikeDAOImpl();

        Like like = new Like(idUser, idTraining);
        try{
            likeDAO.changeEnabledStatus(like);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void putOffDislike(int idTraining, int idUser) throws ServiceException {
        DislikeDAO dislikeDAO = daoFactory.getDislikeDAOImpl();

        Dislike dislike = new Dislike(idUser, idTraining);
        try {
            dislikeDAO.changeEnabledStatus(dislike);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean isLikeEnabled(int idTraining, int idUser) throws ServiceException {
        LikeDAO likeDAO = daoFactory.getLikeDAOImpl();

        Like like = new Like(idUser, idTraining);
        boolean isEnabled = false;
        try {
            isEnabled = likeDAO.getLikeEnabledStatus(like);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
        return isEnabled;
    }

    @Override
    public boolean isDislikeEnabled(int idTraining, int idUser) throws ServiceException {
        DislikeDAO dislikeDAO = daoFactory.getDislikeDAOImpl();

        Dislike dislike = new Dislike(idUser, idTraining);
        boolean isEnabled = false;
        try{
            isEnabled = dislikeDAO.getDislikeEnabledStatus(dislike);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
        return isEnabled;
    }

    @Override
    public void addTrainingDislike(int idTraining, int idUser) throws ServiceException {
        DislikeDAO dislikeDAO = daoFactory.getDislikeDAOImpl();

        Dislike dislike = new Dislike(idUser, idTraining);
        try {
            dislikeDAO.addTrainingDislike(dislike);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void addTrainingLike(int idTraining, int idUser) throws ServiceException {
        LikeDAO likeDAO = daoFactory.getLikeDAOImpl();

        Like like = new Like(idUser, idTraining);
        try {
            likeDAO.addTrainingLike(like);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Query getQueryByIdTrainingIdUser(int idTraining, int idUser) throws ServiceException {
        QueryDAO queryDAO = daoFactory.getQueryDAOImpl();

        Query query = new Query();
        try{
            query = queryDAO.getQueryByIdTrainingIdUser(idTraining, idUser);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return query;
    }

    @Override
    public void undoQuery(int idQuery) throws ServiceException {
        QueryDAO queryDAO = daoFactory.getQueryDAOImpl();

        try{
            queryDAO.changeCanceledStatus(idQuery);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Query> getAllQueries(int idUser) throws ServiceException {
        QueryDAO queryDAO = daoFactory.getQueryDAOImpl();
        List<Query> queries = new ArrayList<>();

        try {
            queries = queryDAO.getAllQueries(idUser);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
        return queries;
    }

    @Override
    public void writeQuery(int idTraining, int idUser) throws ServiceException {
        QueryDAO queryDAO = daoFactory.getQueryDAOImpl();

        Query query = new Query(idTraining, idUser);
        try{
            queryDAO.addQuery(query);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void signOut(int idUser, boolean status) throws ServiceException {
        UserDAO userDAO = daoFactory.getUserDAOImpl();
        try{
            userDAO.changeOnlineStatus(idUser, status);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public Training getTraining(int idTraining) throws ServiceException {
        TrainingDAO trainingDAO = daoFactory.getTrainingDAOImpl();
        Training training = new Training();
        try{
            training = trainingDAO.getTraining(idTraining);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return training;
    }

    @Override
    public List<NewsItem> getAllNews() throws ServiceException {
        NewsDAO newsDAO = daoFactory.getNewsDAOImpl();
        List<NewsItem> news = new ArrayList<>();
        try {
            news = newsDAO.getAllNews();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return news;
    }

    @Override
    public User signIn(String username, String password) throws ServiceException {
        UserDAO userDAO = daoFactory.getUserDAOImpl();
        User user = new User();

        try {

            user = userDAO.getUser(username, password);
            if (user.isBannedStatus()) {
                throw new UserBannedException("User is banned!");
            } else if (!user.isBannedStatus()){
                userDAO.changeOnlineStatus(user.getIdUser(), !user.isOnlineStatus());
            }
        } catch (NoUsernamePasswordException e){
            throw new InvalidSignInValuesException(e);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public int signUp(String username, String email, String password, String confirmedPassword) throws ServiceException {
        UserDAO userDAO = daoFactory.getUserDAOImpl();

        UserValidator userValidator = validatorManager.getUserValidator();
        userValidator.validateSignUp(username, email, password, confirmedPassword);
        User user = new User(username, email);

        String encoded = passwordEncoder.encode(password);

        int result = 0;
        try {

            result = userDAO.addNewUser(user, encoded);
        } catch (UsedUsernameException e) {
            throw new InvalidUsernameException(e);
        } catch (UsedEmailException e){
            throw new InvalidEmailException(e);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public List<Training> viewAllTrainings() throws ServiceException {
        TrainingDAO trainingDAO = daoFactory.getTrainingDAOImpl();
        List<Training> trainings = new ArrayList<>();

        try{
            trainings = trainingDAO.getAllTrainings();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return trainings;
    }
}
