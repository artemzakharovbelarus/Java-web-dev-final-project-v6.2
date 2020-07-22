package by.epam.corporate_education.controller.command.util.api;

public interface PathCreator {
    public String getError();
    public String getViewAllUsers(String contextPath);
    public String getChangeLocale(String contextPath);
    public String getViewTraining(String contextPath, int idTraining);
    public String getEnrolTraining(String contextPath, int idTraining);
    public String getForwardEditTraining();
    public String getForwardEnrollTraining();
    public String getMainPage();
    public String getForwardForgotPassword();
    public String getForwardSignUp();
    public String getForwardTrainingsPage(String contextPath);
    public String getForwardMainPage(String contextPath);
    public String getSignIn();
    public String getSignUp();
    public String getForwardQueries(String contextPath, int idUser);
    public String getQueries();
    public String getTrainerTrainings();
    public String getTrainingPage();
    public String getUserPage();
    public String getTrainingQueries(String contextPath, int idTraining);
}
