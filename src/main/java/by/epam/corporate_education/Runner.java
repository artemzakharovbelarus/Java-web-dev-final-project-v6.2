package by.epam.corporate_education;

import by.epam.corporate_education.entity.UserStatus;

public class Runner {
    public static void main(String[] args) {
        UserStatus status = UserStatus.values()[0];
        System.out.println(status);
    }
}
