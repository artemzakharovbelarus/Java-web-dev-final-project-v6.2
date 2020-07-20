package by.epam.corporate_education.service.api;

import by.epam.corporate_education.entity.Training;
import by.epam.corporate_education.entity.User;
import by.epam.corporate_education.service.exception.ServiceException;

import java.time.LocalDate;
import java.util.List;

public interface AdminService {
    public List<User> getAllUsers() throws ServiceException;
    public User getUserInformation(int idUser) throws ServiceException;
    public int changeTrainingDeletedStatus(int idTraining) throws ServiceException;
    public void updateTrainingValues(int idTraining, String title, String requirements, String information, String city,
                                         int hoursAmount, int minMembers, int maxMembers, LocalDate startDate,
                                         LocalDate endDate, String trainingPhoto, int idTrainer) throws ServiceException;
    public void changeBannedStatus(int idUser, boolean status) throws ServiceException;
    public int getLikesAmount(int idTraining) throws ServiceException;
    public int getDislikesAmount(int idTraining) throws ServiceException;
}
