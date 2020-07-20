package by.epam.corporate_education.service.util.impl;

import by.epam.corporate_education.service.util.api.PasswordEncoder;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncoderImpl implements PasswordEncoder {

    @Override
    public String encode(String password) {

        String encoded = BCrypt.hashpw(password, BCrypt.gensalt(10));
        return encoded;
    }
}
