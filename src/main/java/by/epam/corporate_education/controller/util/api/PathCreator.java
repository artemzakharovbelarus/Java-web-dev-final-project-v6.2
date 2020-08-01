package by.epam.corporate_education.controller.util.api;

public interface PathCreator {
    public String getError();
    public String getViewAllUsers(String contextPath);
    public String getChangeLocale(String contextPath);
    public String getViewTraining(String contextPath, int idTraining);
    public String getEnrolTraining(String contextPath, int idTraining);
    public String getEditTraining();
    public String getEnrollTraining();
    public String getMainPage();
    public String getForwardForgotPassword();
    public String getForwardSignUp();
    public String getForwardTrainingsPage(String contextPath);
    public String getForwardMainPage(String contextPath);
    public String getSignIn();
    public String getSignUp();
    public String getForwardQueries(String contextPath);
    public String getQueries();
    public String getTrainingPage();
    public String getUserPage();
    public String getTrainingQueries(String contextPath, int idTraining);
    public String getUsersPage();
    public String getTrainingsPage();
    public String getTrainingQueries();
    public String getForgotPassword();
    public String getTrainerTrainings();
    public String getForwardTrainerTrainings(String contextPath);
    public String getForwardViewProfile(String contextPath);
    public String getEditProfile();
    public String getForwardEditProfile(String contextPath);
}
