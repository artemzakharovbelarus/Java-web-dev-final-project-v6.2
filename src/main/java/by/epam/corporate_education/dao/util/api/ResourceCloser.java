package by.epam.corporate_education.dao.util.api;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public interface ResourceCloser {
    public void close(Connection connection);
    public void close(ResultSet resultSet);
    public void close(Statement statement);
    public void close(Connection connection, Statement statement, ResultSet resultSet);
    public void close(Connection connection, Statement statement);
}
