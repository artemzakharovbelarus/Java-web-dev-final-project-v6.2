package by.epam.corporate_education.controller.command.util.impl;

import by.epam.corporate_education.controller.command.util.api.AttributesInitializer;
import by.epam.corporate_education.entity.NewsItem;
import by.epam.corporate_education.entity.Query;
import by.epam.corporate_education.entity.Training;
import by.epam.corporate_education.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AttributesInitializerImpl implements AttributesInitializer {

    @Override
    public void setRequestAttributesFullQuery(HttpServletRequest request, Query query) {
        request.setAttribute("query", query);
    }

    @Override
    public void setRequestAttributesUser(HttpServletRequest request, User user) {
        request.setAttribute("user", user);
    }

    @Override
    public void setRequestAttributesLike(HttpServletRequest request, boolean likeEnabled) {
        request.setAttribute("like_enabled", likeEnabled);
    }

    @Override
    public void setRequestAttributesDislike(HttpServletRequest request, boolean dislikeEnabled) {
        request.setAttribute("dislike_enabled", dislikeEnabled);
    }

    @Override
    public void setSessionAttributesMessage(HttpSession session, String key, String message) {
        session.setAttribute(key, message);
    }

    @Override
    public void setRequestAttributesDislikes(HttpServletRequest request, int dislikesAmount) {
        request.setAttribute("dislikes_amount", dislikesAmount);
    }

    @Override
    public void setRequestAttributesLikes(HttpServletRequest request, int likesAmount) {
        request.setAttribute("likes_amount", likesAmount);
    }

    @Override
    public void setRequestAttributesQuery(HttpServletRequest request, int idQuery) {
        request.setAttribute("active_query", idQuery);
    }

    @Override
    public void setRequestAttributesQueries(HttpServletRequest request, List<Query> queries) {
        request.setAttribute("queries", queries);
    }

    @Override
    public void setRequestAttributesTraining(HttpServletRequest request, Training training) {
        request.setAttribute("training", training);
    }

    @Override
    public void setRequestAttributesTrainings(HttpServletRequest request, List<Training> trainings) {
        request.setAttribute("trainings", trainings);
    }

    @Override
    public void setRequestAttributesNews(HttpServletRequest request, List<NewsItem> news) {
        request.setAttribute("news", news);
    }

    @Override
    public void setSessionAttributesUser(HttpSession session, User user) {
        session.setAttribute("idUser", user.getIdUser());
        session.setAttribute("username", user.getUsername());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("leader", user.isLeaderStatus());
        session.setAttribute("online", user.isOnlineStatus());
        session.setAttribute("banned", user.isBannedStatus());
        session.setAttribute("status", user.getStatus().getIdStatus());
        session.setAttribute("userPhoto", user.getUserPhoto());
    }

    @Override
    public void setRequestAttributesUsers(HttpServletRequest request, List<User> users) {
        request.setAttribute("users", users);
    }
}
