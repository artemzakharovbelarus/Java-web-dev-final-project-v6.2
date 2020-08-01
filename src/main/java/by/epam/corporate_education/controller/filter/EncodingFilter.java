package by.epam.corporate_education.controller.filter;

import by.epam.corporate_education.controller.util.ParameterName;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private String encoding;

    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter(ParameterName.ENCODING_FILTER);

        if (encoding == null){
            encoding = ParameterName.ENCODING;
        }
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding(encoding);
        resp.setCharacterEncoding(encoding);
        chain.doFilter(req, resp);
    }

    public void destroy() {
        encoding = null;
    }
}
