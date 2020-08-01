package by.epam.corporate_education.controller.filter;

import by.epam.corporate_education.controller.util.ControllerUtilFactory;
import by.epam.corporate_education.controller.util.ParameterName;
import by.epam.corporate_education.controller.util.api.PathCreator;
import lombok.extern.log4j.Log4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Log4j
public class PageSecurityFilter implements Filter {

    private ControllerUtilFactory utilFactory;
    private PathCreator pathCreator;

    private Map<String, String> temp = new HashMap<>();

    public void init(FilterConfig config) throws ServletException {
        utilFactory = ControllerUtilFactory.getINSTANCE();
        pathCreator = utilFactory.getPathCreator();
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String path = request.getRequestURI();
        HttpSession session = request.getSession();
        boolean isOnline = session.getAttribute(ParameterName.ONLINE) != null;

        if (!isOnline && !(path.endsWith(pathCreator.getSignUp()) || path.endsWith(pathCreator.getSignIn())
                || path.endsWith(pathCreator.getForgotPassword()))){
            response.sendRedirect(pathCreator.getSignIn());
        } else if (isOnline && (path.endsWith(pathCreator.getSignIn())
                || path.endsWith(pathCreator.getSignUp()) || path.endsWith(pathCreator.getForgotPassword()))){
            response.sendRedirect(pathCreator.getForwardMainPage(request.getContextPath()));
        } else if (isOnline && endsWith(path)){
            checkEmptyPage(path, request, response);
        }

        chain.doFilter(req, resp);
    }

    private void checkEmptyPage(String path, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (path.endsWith(pathCreator.getUsersPage())) {
            response.sendRedirect(pathCreator.getViewAllUsers(request.getContextPath()));
        } else if (path.endsWith(pathCreator.getTrainingQueries()) || path.endsWith(pathCreator.getTrainerTrainings())) {
            response.sendRedirect(pathCreator.getForwardTrainerTrainings(request.getContextPath()));
        } else if (path.endsWith(pathCreator.getQueries())) {
            response.sendRedirect(pathCreator.getForwardQueries(request.getContextPath()));
        } else if (path.endsWith(pathCreator.getTrainingPage())
                || path.endsWith(pathCreator.getTrainingsPage())) {
            response.sendRedirect(pathCreator.getForwardTrainingsPage(request.getContextPath()));
        } else if (path.endsWith(pathCreator.getMainPage()) || path.endsWith(pathCreator.getEditTraining())
                || path.endsWith(pathCreator.getEnrollTraining())){
            response.sendRedirect(pathCreator.getForwardMainPage(request.getContextPath()));
        } else if (path.endsWith(pathCreator.getUserPage())){
            response.sendRedirect(pathCreator.getForwardViewProfile(request.getContextPath()));
        } else if (path.endsWith(pathCreator.getEditProfile())){
            response.sendRedirect(pathCreator.getForwardEditProfile(request.getContextPath()));
        }
    }

    private boolean endsWith(String path) {
        boolean result = path.endsWith(pathCreator.getUserPage()) || path.endsWith(pathCreator.getUsersPage())
                || path.endsWith(pathCreator.getTrainingPage()) || path.endsWith(pathCreator.getTrainingsPage())
                || path.endsWith(pathCreator.getTrainingQueries()) || path.endsWith(pathCreator.getQueries())
                || path.endsWith(pathCreator.getTrainerTrainings()) || path.endsWith(pathCreator.getMainPage())
                || path.endsWith(pathCreator.getEditTraining()) || path.endsWith(pathCreator.getEnrollTraining())
                || path.endsWith(pathCreator.getEditProfile());
        return result;
    }

    public void destroy() {
        utilFactory = null;
        pathCreator = null;
    }
}
