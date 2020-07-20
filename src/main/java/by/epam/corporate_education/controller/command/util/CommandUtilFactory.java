package by.epam.corporate_education.controller.command.util;

import by.epam.corporate_education.controller.command.util.api.AttributesInitializer;
import by.epam.corporate_education.controller.command.util.api.NumberChecker;
import by.epam.corporate_education.controller.command.util.api.PaginationCurrentPage;
import by.epam.corporate_education.controller.command.util.api.PathCreator;
import by.epam.corporate_education.controller.command.util.impl.AttributesInitializerImpl;
import by.epam.corporate_education.controller.command.util.impl.NumberCheckerImpl;
import by.epam.corporate_education.controller.command.util.impl.PaginationCurrentPageImpl;
import by.epam.corporate_education.controller.command.util.impl.PathCreatorImpl;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommandUtilFactory {
    @Getter
    private final static CommandUtilFactory INSTANCE = new CommandUtilFactory();

    @Getter
    private AttributesInitializer attributesInitializer = new AttributesInitializerImpl();
    @Getter
    private PaginationCurrentPage paginationCurrentPage = new PaginationCurrentPageImpl();
    @Getter
    private NumberChecker numberChecker = new NumberCheckerImpl();
    @Getter
    private PathCreator pathCreator = new PathCreatorImpl();
}
