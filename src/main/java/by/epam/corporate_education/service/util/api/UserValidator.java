package by.epam.corporate_education.service.util.api;

import by.epam.corporate_education.entity.User;
import by.epam.corporate_education.service.exception.EquivalencePasswordException;
import by.epam.corporate_education.service.exception.InvalidEmailException;
import by.epam.corporate_education.service.exception.InvalidPasswordException;
import by.epam.corporate_education.service.exception.InvalidUsernameException;

public interface UserValidator {
    public void validateSignUp(String username, String email, String password, String confirmedPassword)
            throws InvalidUsernameException, InvalidEmailException,
            InvalidPasswordException, EquivalencePasswordException;
}
