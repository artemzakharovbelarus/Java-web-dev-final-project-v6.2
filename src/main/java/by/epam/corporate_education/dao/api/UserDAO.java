package by.epam.corporate_education.dao.api;

import by.epam.corporate_education.dao.exception.DAOException;
import by.epam.corporate_education.entity.Team;
import by.epam.corporate_education.entity.Training;
import by.epam.corporate_education.entity.User;

import java.util.List;

public interface UserDAO{

    public User getUser(int idUser) throws DAOException;
    public User getUser(String username, String password) throws DAOException;
    public int addNewUser(User user, String password) throws DAOException;

    public List<User> getAllUsers() throws DAOException;
    public List<User> getAllUsersSorted(String parameter, String typeSorting) throws DAOException;
    public List<User> getUsersOnline() throws DAOException;

    public void changeBannedStatus(int idUser, boolean status) throws DAOException;

    public void changeUserStatus(int idUser, int idStatus) throws DAOException;
    public void changeLeaderStatus(int idUser) throws DAOException;
    public User getAllUserInformation(int idUser) throws DAOException;
    public void changeOnlineStatus(int idUser, boolean status) throws DAOException;
}
