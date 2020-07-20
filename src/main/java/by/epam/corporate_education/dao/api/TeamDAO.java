package by.epam.corporate_education.dao.api;

import by.epam.corporate_education.dao.exception.DAOException;
import by.epam.corporate_education.entity.Team;

import java.util.List;

public interface TeamDAO {
    public List<Team> getAllTeams() throws DAOException;
    public List<Team> getAllTeamsSorted(String parameter, String typeSorting) throws DAOException;
}
