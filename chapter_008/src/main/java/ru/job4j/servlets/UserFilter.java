package ru.job4j.servlets;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        if ("/signin/update".contains(request.getServletPath())) {
            chain.doFilter(req, resp);
        } else {
            if ("2".equals(((User) request.getSession().getAttribute("theUser")).getRole())) {
                ((HttpServletResponse) resp).sendRedirect(String.format("%s/", request.getContextPath()));
                return;
            }
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
