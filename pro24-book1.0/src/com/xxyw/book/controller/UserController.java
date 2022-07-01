package com.xxyw.book.controller;

import com.xxyw.book.pojo.User;
import com.xxyw.book.service.UserService;

import javax.servlet.http.HttpSession;

public class UserController {

    UserService userService = null;

    public String login(String uname, String pwd, HttpSession session) {
        User user = userService.login(uname, pwd);
        System.out.println("user = " + user);

        session.setAttribute("currUser", user);

        if (user != null) {
            return "redirect:book.do";
        }
        return "user/login";
    }
}
