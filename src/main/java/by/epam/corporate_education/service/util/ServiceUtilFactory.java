package by.epam.corporate_education.service.util;

import by.epam.corporate_education.service.util.api.PagesCounter;
import by.epam.corporate_education.service.util.api.PasswordEncoder;
import by.epam.corporate_education.service.util.impl.PagesCounterImpl;
import by.epam.corporate_education.service.util.impl.PasswordEncoderImpl;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ServiceUtilFactory {
    @Getter
    private final static ServiceUtilFactory INSTANCE = new ServiceUtilFactory();

    @Getter
    private PasswordEncoder encoder = new PasswordEncoderImpl();
    @Getter @Setter
    private ValidatorManager validatorManager = ValidatorManager.getINSTANCE();
    @Getter
    private PagesCounter pagesCounter = new PagesCounterImpl();

}
