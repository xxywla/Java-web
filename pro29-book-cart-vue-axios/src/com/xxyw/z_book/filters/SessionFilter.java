package com.xxyw.z_book.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@WebFilter(
        urlPatterns = {"*.do", "*.html"},
        initParams = {
                @WebInitParam(name = "bai", value = "/pro29/page.do?operate=page&page=user/login," +
                        "/pro29/page.do?operate=page&page=user/regist," +
                        "/pro29/user.do?null," +
                        "/pro29/page.do?operate=page&page=user/regist," +
                        "/pro29/user.do?operate=regist," +
                        "/pro29/user.do?operate=checkUname&uname=")
        }
)
public class SessionFilter implements Filter {
    Set<String> baiSet;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String bai = filterConfig.getInitParameter("bai");
        String[] split = bai.split(",");
        baiSet = new HashSet<>(Arrays.asList(split));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        System.out.println("request.getRequestURI() = " + request.getRequestURI());
        System.out.println("request.getQueryString() = " + request.getQueryString());

        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        String str = uri + "?" + queryString;
        if (check(baiSet, str)) {
            filterChain.doFilter(request, response);
        } else {
            HttpSession session = request.getSession();
            Object currUserObj = session.getAttribute("currUser");
            if (currUserObj == null) {
                response.sendRedirect("page.do?operate=page&page=user/login");
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }

    private boolean check(Set<String> baiSet, String str) {
        for (String bai : baiSet) {
            if (str.contains(bai)) return true;
        }
        return false;
    }
}
