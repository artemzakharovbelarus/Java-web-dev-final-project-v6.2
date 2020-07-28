package by.epam.corporate_education.controller.util.impl;

import by.epam.corporate_education.controller.util.ControllerUtilFactory;
import by.epam.corporate_education.controller.util.api.ControllerValueChecker;
import org.junit.Test;

import static org.junit.Assert.*;

public class ControllerValueCheckerImplTest {

    ControllerUtilFactory controllerUtilFactory = ControllerUtilFactory.getINSTANCE();
    ControllerValueChecker controllerValueChecker = controllerUtilFactory.getControllerValueChecker();

    @Test
    public void checkNumber_correct(){
        String number = "9";
        boolean result = controllerValueChecker.isNumber(number);
        assertTrue(result);
    }

    @Test
    public void checkNumber_invalid(){
        String notNumber = "1not-number";
        boolean result = controllerValueChecker.isNumber(notNumber);
        assertFalse(result);
    }
}