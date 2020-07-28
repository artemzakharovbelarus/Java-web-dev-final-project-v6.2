package by.epam.corporate_education.controller.util;

import by.epam.corporate_education.controller.util.api.*;
import by.epam.corporate_education.controller.util.impl.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ControllerUtilFactory {
    @Getter
    private final static ControllerUtilFactory INSTANCE = new ControllerUtilFactory();

    @Getter
    private AttributesInitializer attributesInitializer = new AttributesInitializerImpl();
    @Getter
    private PaginationCurrentPage paginationCurrentPage = new PaginationCurrentPageImpl();
    @Getter
    private ControllerValueChecker controllerValueChecker = new ControllerValueCheckerImpl();
    @Getter
    private PathCreator pathCreator = new PathCreatorImpl();
    @Getter @Setter
    private HttpRequestResponseKeeper httpRequestResponseKeeper = new HttpRequestResponseKeeperImpl();
}
