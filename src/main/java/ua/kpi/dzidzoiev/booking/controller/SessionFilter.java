package ua.kpi.dzidzoiev.booking.controller;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionFilter implements Filter
{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc)
            throws IOException, ServletException
    {
        // Check that the request is a HTTP request
        if (request instanceof HttpServletRequest)
        {
            if(((HttpServletRequest) request).getRequestURL().toString().contains("j_security_check")) {
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.sendRedirect(((HttpServletRequest) request).getContextPath() + "/index.xhtml");
            }
        }
        fc.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}