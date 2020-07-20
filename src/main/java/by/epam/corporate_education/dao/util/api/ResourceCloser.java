package by.epam.corporate_education.dao.util.api;

import java.sql.ResultSet;

public interface ResourceCloser {
    public void close(ResultSet resultSet);
}
