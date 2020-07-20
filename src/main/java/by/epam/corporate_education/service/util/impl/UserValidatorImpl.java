package by.epam.corporate_education.service.util.impl;

import by.epam.corporate_education.service.exception.EquivalencePasswordException;
import by.epam.corporate_education.service.exception.InvalidEmailException;
import by.epam.corporate_education.service.exception.InvalidPasswordException;
import by.epam.corporate_education.service.exception.InvalidUsernameException;
import by.epam.corporate_education.service.util.api.UserValidator;

public class UserValidatorImpl implements UserValidator {
    private static final String USERNAME_FORMAT_REGEX = "[A-Za-z0-9][A-Za-z0-9_-]{4,13}[A-Za-z0-9]";
    private static final String NAME_FORMAT_REGEX = ".{2,30}";
    private static final String SURNAME_FORMAT_REGEX = ".{2,30}";
    private static final String EMAIL_FORMAT_REGEX = "[a-z][[a-z][0-9][-][_]]{3,15}[@][a-z]{2,10}[.][a-z]{2,4}";
    private static final String PASSWORD_FORMAT_REGEX = "^(?=.*[0-9]).{8,15}$";

    @Override
    public void validateSignUp(String username, String email, String password, String confirmedPassword)
            throws InvalidUsernameException, InvalidEmailException,
            InvalidPasswordException, EquivalencePasswordException{
        if (!validateUsername(username)){
            throw new InvalidUsernameException("Username is invalid!");
        } else if (!validateEmail(email)){
            throw new InvalidEmailException("E-mail is invalid!");
        } else if (!validatePassword(password)){
            throw new InvalidPasswordException("Password is invalid");
        } else if (!checkEquivalence(password, confirmedPassword)){
            throw new EquivalencePasswordException("Passwords are not equals!");
        }
    }

    private boolean validateUsername(String username) {
        return username.matches(USERNAME_FORMAT_REGEX);
    }

    private boolean validateEmail(String email) {
        return email.matches(EMAIL_FORMAT_REGEX);
    }

    private boolean validatePassword(String password) {
        return password.matches(PASSWORD_FORMAT_REGEX);
    }

    private boolean checkEquivalence(String password, String confirmedPassword) {
        return password.equals(confirmedPassword);
    }
}
