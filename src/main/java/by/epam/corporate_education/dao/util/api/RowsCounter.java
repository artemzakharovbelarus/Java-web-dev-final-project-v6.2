package by.epam.corporate_education.dao.util.api;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowsCounter {
    public int getRowsAmount(ResultSet resultSet) throws SQLException;
}
