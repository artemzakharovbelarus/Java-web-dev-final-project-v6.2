package by.epam.corporate_education.service;

import by.epam.corporate_education.service.api.AdminService;
import by.epam.corporate_education.service.api.TrainerService;
import by.epam.corporate_education.service.api.UserService;
import by.epam.corporate_education.service.impl.AdminServiceImpl;
import by.epam.corporate_education.service.impl.TrainerServiceImpl;
import by.epam.corporate_education.service.impl.UserServiceImpl;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ServiceFactory {
    @Getter
    private static final ServiceFactory INSTANCE = new ServiceFactory();

    @Getter
    private UserService userServiceImpl = new UserServiceImpl();
    @Getter
    private AdminService adminServiceImpl = new AdminServiceImpl();
    @Getter
    private TrainerService trainerServiceImpl = new TrainerServiceImpl();
}
