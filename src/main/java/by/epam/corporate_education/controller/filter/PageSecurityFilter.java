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
import java.util.Map;

@Log4j
public class PageSecurityFilter implements Filter {

    private ControllerUtilFactory utilFactory;
    private PathCreator pathCreator;

    public void init(FilterConfig config) throws ServletException {
        utilFactory = ControllerUtilFactory.getINSTANCE();
        pathCreator = utilFactory.getPathCreator();
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String path = request.getRequestURI();
        HttpSession session = request.getSession();
        String contextPath = request.getContextPath();

        if (session.getAttribute(ParameterName.ONLINE) == null
                && !(path.endsWith(pathCreator.getSignUp()) || path.endsWith(pathCreator.getSignIn())
                || path.endsWith(pathCreator.getForgotPassword()))){
            response.sendRedirect(pathCreator.getSignIn());
        } else if (session.getAttribute(ParameterName.ONLINE) != null
                && (path.endsWith(pathCreator.getSignIn())
                || path.endsWith(pathCreator.getSignUp()) || path.endsWith(pathCreator.getForgotPassword()))){
            response.sendRedirect(pathCreator.getForwardMainPage(request.getContextPath()));
        }

        chain.doFilter(req, resp);
    }

    public void destroy() {
        utilFactory = null;
        pathCreator = null;
    }
}
