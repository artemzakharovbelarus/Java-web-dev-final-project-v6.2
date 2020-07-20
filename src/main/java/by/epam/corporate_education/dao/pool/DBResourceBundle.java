package by.epam.corporate_education.dao.pool;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ResourceBundle;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DBResourceBundle {
    @Getter
    private static final DBResourceBundle INSTANCE = new DBResourceBundle();

    private ResourceBundle dbBundle = ResourceBundle.getBundle("db");

    public String getDBValue(String key) {
        return dbBundle.getString(key);
    }
}
