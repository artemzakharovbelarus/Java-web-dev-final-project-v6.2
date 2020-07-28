package by.epam.corporate_education.dao.util.impl;

import by.epam.corporate_education.dao.util.api.ResourceCloser;
import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Log4j
public class ResourceCloserImpl implements ResourceCloser {

    @Override
    public void close(Connection connection) {
        try {
            if (connection != null){
                connection.close();
            }
        } catch (SQLException e){
            log.error(e);
        }
    }

    @Override
    public void close(Statement statement) {
        try {
            if (statement != null){
                statement.close();
            }
        } catch (SQLException e){
            log.error(e);
        }
    }

    @Override
    public void close(Connection connection, Statement statement, ResultSet resultSet) {
        close(connection, statement);
        close(resultSet);
    }

    @Override
    public void close(Connection connection, Statement statement) {
        close(connection);
        close(statement);
    }

    @Override
    public void close(ResultSet resultSet) {
        try {
            if (resultSet != null){
                resultSet.close();
            }
        } catch (SQLException e){
            log.error(e);
        }
    }
}
