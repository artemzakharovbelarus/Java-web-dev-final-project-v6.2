package by.epam.corporate_education.controller.command.util.impl;

import by.epam.corporate_education.controller.command.util.api.NumberChecker;
import org.apache.commons.lang3.math.NumberUtils;

public class NumberCheckerImpl implements NumberChecker {

    @Override
    public boolean isNumber(String number) {
        boolean result = NumberUtils.isCreatable(number);
        return result;
    }
}
