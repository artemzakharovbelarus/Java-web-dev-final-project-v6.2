package by.epam.corporate_education.dao;

import by.epam.corporate_education.dao.api.*;
import by.epam.corporate_education.dao.impl.*;
import by.epam.corporate_education.dao.util.api.ValueChecker;
import by.epam.corporate_education.dao.util.impl.ValueCheckerImpl;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DAOFactory {
    @Getter
    private static final DAOFactory INSTANCE = new DAOFactory();

    @Getter
    private UserDAO userDAOImpl = new UserDAOImpl();
    @Getter
    private TrainingDAO trainingDAOImpl = new TrainingDAOImpl();
    @Getter
    private TeamDAO teamDAOImpl = new TeamDAOImpl();
    @Getter
    private NewsDAO newsDAOImpl = new NewsDAOImpl();
    @Getter
    private QueryDAO queryDAOImpl = new QueryDAOImpl();
    @Getter
    private LikeDAO likeDAOImpl = new LikeDAOImpl();
    @Getter
    private DislikeDAO dislikeDAOImpl = new DislikeDAOImpl();

}
