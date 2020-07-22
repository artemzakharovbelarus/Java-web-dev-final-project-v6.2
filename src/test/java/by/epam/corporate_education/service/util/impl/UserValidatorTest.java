package by.epam.corporate_education.service.util.impl;

import by.epam.corporate_education.service.exception.EquivalencePasswordException;
import by.epam.corporate_education.service.exception.InvalidEmailException;
import by.epam.corporate_education.service.exception.InvalidPasswordException;
import by.epam.corporate_education.service.exception.InvalidUsernameException;
import by.epam.corporate_education.service.util.ServiceUtilFactory;
import by.epam.corporate_education.service.util.ValidatorManager;
import by.epam.corporate_education.service.util.api.UserValidator;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserValidatorTest {

    ServiceUtilFactory serviceUtilFactory = ServiceUtilFactory.getINSTANCE();
    ValidatorManager validatorManager = serviceUtilFactory.getValidatorManager();
    UserValidator userValidator = validatorManager.getUserValidator();

    String username;
    String password;
    String confirmedPassword;
    String email;

    @Test
    public void validateUsername_short_invalid(){
        //given
        //when
        username = "short";
        boolean result = userValidator.validateUsername(username);
        //then
        assertFalse(result);
    }

    @Test
    public void validateUsername_long_invalid(){
        //given
        //when
        username = "solongusernameforme";
        boolean result = userValidator.validateUsername(username);
        //then
        assertFalse(result);
    }

    @Test
    public void validateUsername_hyphenAtUsername_invalid(){
        //given
        //when
        username = "username-";
        boolean result = userValidator.validateUsername(username);
        //then
        assertFalse(result);
    }

    @Test
    public void validateUsername_correct(){
        //given
        //when
        username = "ArtemZakharovBY";
        boolean result = userValidator.validateUsername(username);
        //then
        assertTrue(result);
    }

    @Test
    public void validateUsername_withNumbers_correct(){
        //given
        //when
        username = "ArtemZakh123";
        boolean result = userValidator.validateUsername(username);
        //then
        assertTrue(result);
    }

    @Test
    public void validatePassword_short_invalid(){
        //given
        //when
        password = "qwe";
        boolean result = userValidator.validatePassword(password);
        //then
        assertFalse(result);
    }

    @Test
    public void validatePassword_long_invalid(){
        //given
        //when
        password = "veryveryverylongpassword";
        boolean result = userValidator.validatePassword(password);
        //then
        assertFalse(result);
    }

    @Test
    public void validatePassword_correct(){
        //given
        //when
        password = "QWE123QW";
        boolean result = userValidator.validatePassword(password);
        //then
        assertTrue(result);
    }

    @Test
    public void validateEmail_withoutDot_invalid(){
        //given
        //when
        email = "temax359x@gmail,com";
        boolean result = userValidator.validateEmail(email);
        //then
        assertFalse(result);
    }

    @Test
    public void validateEmail_withoutNumbers_invalid(){
        //given
        //when
        email = "temazakharov@gmail.com";
        boolean result = userValidator.validateEmail(email);
        //then
        assertTrue(result);
    }

    @Test
    public void validateEmail_withNumbers_correct(){
        //given
        //when
        email = "temax359x@gmail.com";
        boolean result = userValidator.validateEmail(email);
        //then
        assertTrue(result);
    }

    @Test
    public void validatePasswordEquivalence_invalid(){
        //given
        //when
        password = "QWE123Qw";
        confirmedPassword = "QWE123QW";
        boolean result = userValidator.checkEquivalence(password, confirmedPassword);
        //then
        assertFalse(result);
    }

    @Test
    public void validatePasswordEquivalence_correct(){
        //given
        //when
        password = "QWE123QW";
        confirmedPassword = "QWE123QW";
        boolean result = userValidator.checkEquivalence(password, confirmedPassword);
        //then
        assertTrue(result);
    }

    @Test(expected = InvalidUsernameException.class)
    public void validateSignUp_invalidParameterUsername_InvalidUsernameException()
            throws InvalidUsernameException, InvalidEmailException,
                   InvalidPasswordException, EquivalencePasswordException {
        //given
        //when
        username = "ArtemZakharovBY123";
        password = "QWE123QW";
        confirmedPassword = "QWE123QW";
        email = "temax359x@gmail.com";
        userValidator.validateSignUp(username, email, password, confirmedPassword);
        //then
        //expected InvalidUsernameException
    }

    @Test(expected = InvalidEmailException.class)
    public void validateSignUp_invalidParameterEmail_InvalidEmailException()
            throws InvalidUsernameException, InvalidEmailException,
                   InvalidPasswordException, EquivalencePasswordException {
        //given
        //when
        username = "ArtemZakharovBY";
        password = "QWE123QW";
        confirmedPassword = "QWE123QW";
        email = "temax359x@gmail,com";
        userValidator.validateSignUp(username, email, password, confirmedPassword);
        //then
        //expected InvalidEmailException
    }

    @Test(expected = InvalidPasswordException.class)
    public void validateSignUp_invalidParameterPassword_InvalidPasswordException()
            throws InvalidUsernameException, InvalidEmailException,
                   InvalidPasswordException, EquivalencePasswordException {
        //given
        //when
        username = "ArtemZakharovBY";
        password = "QWE";
        confirmedPassword = "QWE";
        email = "temax359x@gmail.com";
        userValidator.validateSignUp(username, email, password, confirmedPassword);
        //then
        //expected InvalidPasswordException
    }

    @Test(expected = EquivalencePasswordException.class)
    public void validateSignUp_notEqualsPassword_EquivalencePasswordException()
            throws InvalidUsernameException, InvalidEmailException,
            InvalidPasswordException, EquivalencePasswordException {
        //given
        //when
        username = "ArtemZakharovBY";
        password = "QWE123QW";
        confirmedPassword = "QWE123";
        email = "temax359x@gmail.com";
        userValidator.validateSignUp(username, email, password, confirmedPassword);
        //then
        //expected EquivalencePasswordException
    }

    @Test
    public void validateSignUp_correct()
            throws InvalidUsernameException, InvalidEmailException,
            InvalidPasswordException, EquivalencePasswordException {
        //given
        //when
        username = "ArtemZakharovBY";
        password = "QWE123QW";
        confirmedPassword = "QWE123QW";
        email = "temax359x@gmail.com";
        boolean result = wasThrownException(username, email, password, confirmedPassword);
        //then
        assertFalse(result);
    }

    private boolean wasThrownException(String username, String email, String password, String confirmedPassword) {
        boolean result = false;
        try {
            userValidator.validateSignUp(username, email, password, confirmedPassword);
        } catch (InvalidUsernameException | EquivalencePasswordException
                | InvalidPasswordException | InvalidEmailException e){
            result = true;
        }
        return result;
    }
}