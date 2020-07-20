package by.epam.corporate_education.dao.util.impl;

import by.epam.corporate_education.dao.util.api.RowsCounter;

import java.sql.*;

public class RowsCounterImpl implements RowsCounter {

    @Override
    public int getRowsAmount(ResultSet resultSet) throws SQLException{
        int rowsAmount = 0;
        while (resultSet.next()){
            rowsAmount = resultSet.getInt(1);
        }
        return rowsAmount;
    }
}
