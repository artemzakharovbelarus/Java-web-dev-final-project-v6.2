package by.epam.corporate_education.controller.util.api;

public interface ControllerValueChecker {
    public boolean isNumber(String number);
    public boolean isAdmin(int idStatus);
    public boolean isAnyUser(int idStatus);
    public boolean isTeacher(int idStatus);
    public boolean isStudent(int idStatus);
}
