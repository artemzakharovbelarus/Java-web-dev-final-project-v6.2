package by.epam.corporate_education.controller.util.impl;

import by.epam.corporate_education.controller.util.ParameterName;
import by.epam.corporate_education.controller.util.api.AttributesInitializer;
import by.epam.corporate_education.entity.NewsItem;
import by.epam.corporate_education.entity.Query;
import by.epam.corporate_education.entity.Training;
import by.epam.corporate_education.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AttributesInitializerImpl implements AttributesInitializer {

    @Override
    public void setRequestAttributesUser(HttpServletRequest request, User user) {
        request.setAttribute(ParameterName.USER, user);
    }

    @Override
    public void setRequestAttributesLike(HttpServletRequest request, boolean likeEnabled) {
        request.setAttribute(ParameterName.LIKE_ENABLED, likeEnabled);
    }

    @Override
    public void setRequestAttributesDislike(HttpServletRequest request, boolean dislikeEnabled) {
        request.setAttribute(ParameterName.DISLIKE_ENABLED, dislikeEnabled);
    }

    @Override
    public void setSessionAttributesMessage(HttpSession session, String key, String message) {
        session.setAttribute(key, message);
    }

    @Override
    public void setRequestAttributesDislikes(HttpServletRequest request, int dislikesAmount) {
        request.setAttribute(ParameterName.DISLIKES_AMOUNT, dislikesAmount);
    }

    @Override
    public void setRequestAttributesLikes(HttpServletRequest request, int likesAmount) {
        request.setAttribute(ParameterName.LIKES_AMOUNT, likesAmount);
    }

    @Override
    public void setRequestAttributesQuery(HttpServletRequest request, int idQuery) {
        request.setAttribute(ParameterName.ACTIVE_QUERY, idQuery);
    }

    @Override
    public void setRequestAttributesQueries(HttpServletRequest request, List<Query> queries) {
        request.setAttribute(ParameterName.QUERIES, queries);
    }

    @Override
    public void setRequestAttributesTraining(HttpServletRequest request, Training training) {
        request.setAttribute(ParameterName.TRAINING, training);
    }

    @Override
    public void setRequestAttributesTrainings(HttpServletRequest request, List<Training> trainings) {
        request.setAttribute(ParameterName.TRAININGS, trainings);
    }

    @Override
    public void setRequestAttributesNews(HttpServletRequest request, List<NewsItem> news) {
        request.setAttribute(ParameterName.NEWS, news);
    }

    @Override
    public void setSessionAttributesUser(HttpSession session, User user) {
        session.setAttribute(ParameterName.ID_USER, user.getIdUser());
        session.setAttribute(ParameterName.USERNAME, user.getUsername());
        session.setAttribute(ParameterName.EMAIL, user.getEmail());
        session.setAttribute(ParameterName.LEADER, user.isLeaderStatus());
        session.setAttribute(ParameterName.ONLINE, user.isOnlineStatus());
        session.setAttribute(ParameterName.BANNED, user.isBannedStatus());
        session.setAttribute(ParameterName.STATUS, user.getStatus().getIdStatus());
        session.setAttribute(ParameterName.USER_PHOTO, user.getUserPhoto());
    }

    @Override
    public void setRequestAttributesUsers(HttpServletRequest request, List<User> users) {
        request.setAttribute(ParameterName.USERS, users);
    }
}
