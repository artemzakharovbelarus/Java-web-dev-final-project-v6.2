package by.epam.corporate_education.service.util;

import by.epam.corporate_education.service.util.api.UserValidator;
import by.epam.corporate_education.service.util.impl.UserValidatorImpl;
import by.epam.corporate_education.util.annotation.SetterForTest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidatorManager {
    @Getter
    private static final ValidatorManager INSTANCE = new ValidatorManager();

    @Getter
    private UserValidator userValidator = new UserValidatorImpl();

    @SetterForTest
    public void setUserValidator(UserValidator userValidator) {
        this.userValidator = userValidator;
    }
}
