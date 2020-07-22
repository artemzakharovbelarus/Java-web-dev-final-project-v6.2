package by.epam.corporate_education.controller.command.util.impl;

import by.epam.corporate_education.controller.command.util.CommandUtilFactory;
import by.epam.corporate_education.controller.command.util.api.NumberChecker;
import org.junit.Test;

import static org.junit.Assert.*;

public class NumberCheckerImplTest {

    CommandUtilFactory commandUtilFactory = CommandUtilFactory.getINSTANCE();
    NumberChecker numberChecker = commandUtilFactory.getNumberChecker();

    @Test
    public void checkNumber_correct(){
        String number = "9";
        boolean result = numberChecker.isNumber(number);
        assertTrue(result);
    }

    @Test
    public void checkNumber_invalid(){
        String notNumber = "1not-number";
        boolean result = numberChecker.isNumber(notNumber);
        assertFalse(result);
    }
}