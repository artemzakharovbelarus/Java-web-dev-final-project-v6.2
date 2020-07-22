package by.epam.corporate_education.controller.command.util.impl;

import by.epam.corporate_education.controller.command.util.api.PathCreator;

public class PathCreatorImpl implements PathCreator {
    private final static String ERROR = "error";
    private final static String VIEW_ALL_USERS = "/controller?command=view-all-users";
    private final static String FORWARD_TO_MAIN = "/controller?command=forward-to-main";
    private final static String ID_TRAINING = "/controller?idTraining=";
    private final static String VIEW_TRAINING = "&command=view-training";
    private final static String VIEW_ALL_TRAININGS = "/controller?command=view-all-trainings";
    private final static String EDIT_TRAINING = "edit-training";
    private final static String FORWARD_ENROLL_TRAINING = "enroll-training";
    private final static String MAIN_PAGE = "main-page";
    private final static String FORWARD_FORGOT_PASSWORD = "/controller?command=forward-forgot-password";
    private final static String FORWARD_SIGN_UP = "/controller?command=forward-sign-up";
    private final static String FORWARD_ALL_TRAININGS = "trainings-page";
    private final static String SIGN_IN = "sign-in";
    private final static String SIGN_UP = "sign-up";
    private final static String ID_USER = "/controller?idUser=";
    private final static String VIEW_ALL_QUERIES = "&command=view-all-queries";
    private final static String QUERIES_PAGE = "queries-page";
    private final static String TRAINER_TRAININGS_PAGE = "trainer-trainings-page";
    private final static String TRAINING_PAGE = "training-page";
    private final static String USER_PAGE = "user-page";
    private final static String TRAINING_QUERIES = "&command=view-training-queries";

    @Override
    public String getTrainingQueries(String contextPath, int idTraining) {
        return contextPath + ID_TRAINING + idTraining + TRAINING_QUERIES;
    }

    @Override
    public String getUserPage() {
        return USER_PAGE;
    }

    @Override
    public String getTrainingPage() {
        return TRAINING_PAGE;
    }

    @Override
    public String getTrainerTrainings() {
        return TRAINER_TRAININGS_PAGE;
    }

    @Override
    public String getQueries() {
        return QUERIES_PAGE;
    }

    @Override
    public String getForwardQueries(String contextPath, int idUser) {
        return contextPath + ID_USER + idUser + VIEW_ALL_QUERIES;
    }

    @Override
    public String getSignUp() {
        return SIGN_UP;
    }

    @Override
    public String getSignIn() {
        return SIGN_IN;
    }

    @Override
    public String getForwardMainPage(String contextPath) {
        return contextPath + FORWARD_TO_MAIN;
    }

    @Override
    public String getForwardTrainingsPage(String contextPath) {
        return contextPath + VIEW_ALL_TRAININGS;
    }

    @Override
    public String getForwardSignUp() {
        return FORWARD_SIGN_UP;
    }

    @Override
    public String getForwardForgotPassword() {
        return FORWARD_FORGOT_PASSWORD;
    }

    @Override
    public String getMainPage() {
        return MAIN_PAGE;
    }

    @Override
    public String getForwardEnrollTraining() {
        return FORWARD_ENROLL_TRAINING;
    }

    @Override
    public String getForwardEditTraining() {
        return EDIT_TRAINING;
    }

    @Override
    public String getEnrolTraining(String contextPath, int idTraining) {
        return contextPath + VIEW_ALL_TRAININGS;
    }

    @Override
    public String getViewTraining(String contextPath, int idTraining) {
        return contextPath + ID_TRAINING + idTraining + VIEW_TRAINING;
    }

    @Override
    public String getError() {
        return ERROR;
    }

    @Override
    public String getViewAllUsers(String contextPath) {
        return contextPath + VIEW_ALL_USERS;
    }

    @Override
    public String getChangeLocale(String contextPath) {
        return contextPath + FORWARD_TO_MAIN;
    }
}
