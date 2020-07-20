package by.epam.corporate_education.dao.impl;

import by.epam.corporate_education.dao.SQLRequest;
import by.epam.corporate_education.dao.api.TeamDAO;
import by.epam.corporate_education.dao.exception.DAOException;
import by.epam.corporate_education.dao.pool.DBConnectionPool;
import by.epam.corporate_education.entity.Team;
import by.epam.corporate_education.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// @Log4j2
public class TeamDAOImpl implements TeamDAO {
    private DBConnectionPool connectionPool = DBConnectionPool.getINSTANCE();

    @Override
    public List<Team> getAllTeams() throws DAOException {
        String request = SQLRequest.GET_ALL_TEAMS;
        List<Team> teams = new ArrayList<>();

        ResultSet resultSet = null;
        try(Connection connection = connectionPool.takeConnection();
            Statement statement = connection.createStatement()){

            resultSet = statement.executeQuery(request);
            while (resultSet.next()){
                Team team = getNextTeam(resultSet);
                teams.add(team);
            }
        } catch (SQLException e){
            throw new DAOException(e);
        } finally {
            try{
                if (resultSet != null){
                    resultSet.close();
                }
            } catch (SQLException e){
                //log
            }
        }
        return teams;
    }

    @Override
    public List<Team> getAllTeamsSorted(String parameter, String typeSorting) throws DAOException {
        return null;
    }

    private Team getNextTeam(ResultSet resultSet) throws SQLException{
        int idTeam = resultSet.getInt(1);
        int peopleAmount = resultSet.getInt(2);
        int idTraining = resultSet.getInt(3);
        int idLeader = resultSet.getInt(4);

        List<User> students = new ArrayList<>();
        Team team = new Team(idTeam, peopleAmount, students, idTraining, idLeader);
        return team;
    }
}
