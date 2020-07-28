package by.epam.corporate_education.service.api;

import by.epam.corporate_education.dao.api.*;
import by.epam.corporate_education.entity.NewsItem;
import by.epam.corporate_education.entity.Query;
import by.epam.corporate_education.entity.Training;
import by.epam.corporate_education.entity.User;
import by.epam.corporate_education.service.exception.ServiceException;

import java.time.LocalDate;
import java.util.List;

public interface UserService {
    public User signIn(String username, String password) throws ServiceException;
    public void signOut(int idUser, boolean status) throws ServiceException;
    public void signUp(String username, String email, String password, String confirmedPassword) throws ServiceException;
    public List<Training> viewAllTrainings() throws ServiceException;
    public List<NewsItem> viewAllNews() throws ServiceException;
    public Training getTraining(int idTraining) throws ServiceException;
    public void writeQuery(int idTraining, int idUser) throws ServiceException;
    public List<Query> viewAllQueries(int idUser) throws ServiceException;
    public void undoQuery(int idQuery) throws ServiceException;
    public Query getQueryByIdTrainingIdUser(int idTraining, int idUser) throws ServiceException;
    public void addTrainingLike(int idTraining, int idUser) throws ServiceException;
    public void addTrainingDislike(int idTraining, int idUser) throws ServiceException;
    public boolean isLikeEnabled(int idTraining, int idUser) throws ServiceException;
    public boolean isDislikeEnabled(int idTraining, int idUser) throws ServiceException;
    public void putOffLike(int idTraining, int idUser) throws ServiceException;
    public void putOffDislike(int idTraining, int idUser) throws ServiceException;
}
