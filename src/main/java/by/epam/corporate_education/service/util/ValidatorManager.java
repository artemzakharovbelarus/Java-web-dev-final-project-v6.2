package by.epam.corporate_education.service.util;

import by.epam.corporate_education.service.util.api.UserValidator;
import by.epam.corporate_education.service.util.impl.UserValidatorImpl;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidatorManager {
    @Getter
    private static final ValidatorManager INSTANCE = new ValidatorManager();

    @Getter @Setter
    private UserValidator userValidator = new UserValidatorImpl();
}
