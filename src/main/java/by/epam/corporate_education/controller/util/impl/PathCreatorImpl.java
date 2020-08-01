package by.epam.corporate_education.controller.util.impl;

import by.epam.corporate_education.controller.util.api.PathCreator;

public class PathCreatorImpl implements PathCreator {
    private final static String ERROR = "error";
    private final static String VIEW_ALL_USERS = "/controller?command=view-all-users";
    private final static String FORWARD_TO_MAIN = "/controller?command=forward-to-main";
    private final static String ID_TRAINING = "/controller?idTraining=";
    private final static String VIEW_TRAINING = "&command=view-training";
    private final static String VIEW_ALL_TRAININGS = "/controller?command=view-all-trainings";
    private final static String EDIT_TRAINING = "edit-training";
    private final static String ENROLL_TRAINING = "enroll-training";
    private final static String MAIN_PAGE = "main-page";
    private final static String FORWARD_FORGOT_PASSWORD = "/controller?command=forward-forgot-password";
    private final static String FORGOT_PASSWORD = "forgot-password";
    private final static String FORWARD_SIGN_UP = "/controller?command=forward-sign-up";
    private final static String TRAININGS_PAGE = "trainings-page";
    private final static String SIGN_IN = "sign-in";
    private final static String SIGN_UP = "sign-up";
    private final static String ID_USER = "/controller?idUser=";
    private final static String VIEW_ALL_QUERIES = "/controller?command=view-all-queries";
    private final static String QUERIES_PAGE = "queries-page";
    private final static String TRAINING_PAGE = "training-page";
    private final static String USER_PAGE = "user-page";
    private final static String TRAINING_QUERIES = "&command=view-training-queries";
    private final static String USERS_PAGE = "users-page";
    private final static String TRAINING_QUERIES_NOT_COMMAND = "training-queries";
    private final static String TRAINER_TRAININGS = "trainer-trainings-page";
    private final static String FORWARD_TRAINER_TRAININGS = "/controller?command=view-all-trainer-trainings";
    private final static String FORWARD_VIEW_PROFILE = "/controller?command=view-profile";
    private final static String EDIT_PROFILE = "edit-profile";
    private final static String FORWARD_EDIT_PROFILE = "/controller?command=forward-edit-profile";

    @Override
    public String getForwardEditProfile(String contextPath) {
        return contextPath + FORWARD_EDIT_PROFILE;
    }

    @Override
    public String getEditProfile() {
        return EDIT_PROFILE;
    }

    @Override
    public String getForwardViewProfile(String contextPath) {
        return contextPath + FORWARD_VIEW_PROFILE;
    }

    @Override
    public String getForwardTrainerTrainings(String contextPath) {
        return contextPath + FORWARD_TRAINER_TRAININGS;
    }

    @Override
    public String getTrainerTrainings() {
        return TRAINER_TRAININGS;
    }

    @Override
    public String getForgotPassword() {
        return FORGOT_PASSWORD;
    }

    @Override
    public String getTrainingQueries() {
        return TRAINING_QUERIES_NOT_COMMAND;
    }

    @Override
    public String getTrainingsPage() {
        return TRAININGS_PAGE;
    }

    @Override
    public String getUsersPage() {
        return USERS_PAGE;
    }

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
    public String getQueries() {
        return QUERIES_PAGE;
    }

    @Override
    public String getForwardQueries(String contextPath) {
        return contextPath + VIEW_ALL_QUERIES;
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
    public String getEnrollTraining() {
        return ENROLL_TRAINING;
    }

    @Override
    public String getEditTraining() {
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
