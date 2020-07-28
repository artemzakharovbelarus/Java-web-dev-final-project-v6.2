package by.epam.corporate_education.controller.util.impl;

import by.epam.corporate_education.controller.util.api.ControllerValueChecker;
import by.epam.corporate_education.entity.UserStatus;
import org.apache.commons.lang3.math.NumberUtils;

public class ControllerValueCheckerImpl implements ControllerValueChecker {

    @Override
    public boolean isStudent(int idStatus) {
        UserStatus userStatus = UserStatus.values()[idStatus-1];
        boolean result = userStatus == UserStatus.STUDENT;
        return result;
    }

    @Override
    public boolean isNumber(String number) {
        boolean result = NumberUtils.isCreatable(number);
        return result;
    }

    @Override
    public boolean isAdmin(int idStatus) {
        UserStatus userStatus = UserStatus.values()[idStatus-1];
        boolean result = userStatus == UserStatus.ADMIN;
        return result;
    }

    @Override
    public boolean isAnyUser(int idStatus) {
        UserStatus userStatus = UserStatus.values()[idStatus-1];
        boolean result = userStatus == UserStatus.ADMIN
                || userStatus == UserStatus.STUDENT
                || userStatus == UserStatus.TEACHER;
        return result;
    }

    @Override
    public boolean isTeacher(int idStatus) {
        UserStatus userStatus = UserStatus.values()[idStatus-1];
        boolean result = userStatus == UserStatus.TEACHER;
        return result;
    }
}
