package by.epam.corporate_education.dao.util.impl;

import by.epam.corporate_education.dao.util.api.ResourceCloser;
import lombok.extern.log4j.Log4j;

import java.sql.ResultSet;
import java.sql.SQLException;

@Log4j
public class ResourceCloserImpl implements ResourceCloser {

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
