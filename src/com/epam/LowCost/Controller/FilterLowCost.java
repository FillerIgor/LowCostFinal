package com.epam.LowCost.Controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "FilterLowCost")
public class FilterLowCost implements Filter {

    private ServletContext context;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        context.log("Charset was set.");

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        this.context = config.getServletContext();
        this.context.log("RequestLoggingFilter initialized");

    }

}
