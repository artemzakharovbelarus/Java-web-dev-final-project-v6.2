package by.epam.corporate_education.controller.command.util.api;

import by.epam.corporate_education.entity.NewsItem;
import by.epam.corporate_education.entity.Query;
import by.epam.corporate_education.entity.Training;
import by.epam.corporate_education.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface AttributesInitializer {
    public void setRequestAttributesTrainings(HttpServletRequest request, List<Training> trainings);
    public void setRequestAttributesNews(HttpServletRequest request, List<NewsItem> news);
    public void setSessionAttributesUser(HttpSession session, User user);
    public void setRequestAttributesUsers(HttpServletRequest request, List<User> users);
    public void setRequestAttributesTraining(HttpServletRequest request, Training training);
    public void setRequestAttributesQueries(HttpServletRequest request, List<Query> queries);
    public void setRequestAttributesQuery(HttpServletRequest request, int idQuery);
    public void setRequestAttributesDislikes(HttpServletRequest request, int dislikesAmount);
    public void setRequestAttributesLikes(HttpServletRequest request, int likesAmount);
    public void setSessionAttributesMessage(HttpSession session, String key, String message);
    public void setRequestAttributesLike(HttpServletRequest request, boolean likeEnabled);
    public void setRequestAttributesDislike(HttpServletRequest request, boolean dislikeEnabled);
    public void setRequestAttributesUser(HttpServletRequest request, User user);
    public void setRequestAttributesFullQuery(HttpServletRequest request, Query query);
}
