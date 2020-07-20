package by.epam.corporate_education.dao.util;

import by.epam.corporate_education.dao.util.api.*;
import by.epam.corporate_education.dao.util.impl.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DAOUtilFactory {
    @Getter
    private static final DAOUtilFactory INSTANCE = new DAOUtilFactory();

    @Getter
    private ValueChecker valueChecker = new ValueCheckerImpl();
    @Getter
    private StatementInitializer statementInitializer = new StatementInitializerImpl();
    @Getter
    private SortingManager sortingManager = SortingManagerImpl.getINSTANCE();
    @Getter
    private RowsCounter rowsCounter = new RowsCounterImpl();
    @Getter
    private ResourceCloser resourceCloser = new ResourceCloserImpl();
    @Getter
    private ResultCreator resultCreator = new ResultCreatorImpl();
}
