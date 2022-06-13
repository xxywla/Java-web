package com.xxyw.servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/demo07.do")
public class Filter07 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("demo07_1");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("demo07_2");
    }

    @Override
    public void destroy() {

    }
}
