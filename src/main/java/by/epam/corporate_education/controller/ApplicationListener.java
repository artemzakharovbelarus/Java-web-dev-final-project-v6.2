package by.epam.corporate_education.controller;

import by.epam.corporate_education.dao.exception.DBPoolException;
import by.epam.corporate_education.dao.pool.DBConnectionPool;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

@Log4j
@NoArgsConstructor
public class ApplicationListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        DBConnectionPool connectionPool = DBConnectionPool.getINSTANCE();
        try {
            connectionPool.init();
        } catch (DBPoolException e) {
            log.error(e);
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
        DBConnectionPool connectionPool = DBConnectionPool.getINSTANCE();
        connectionPool.destroy();
    }

    public void sessionCreated(HttpSessionEvent se) {

    }

    public void sessionDestroyed(HttpSessionEvent se) {

    }

    public void attributeAdded(HttpSessionBindingEvent sbe) {

    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {

    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {

    }
}
