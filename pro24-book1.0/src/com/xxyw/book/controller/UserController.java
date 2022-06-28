package com.xxyw.book.controller;

import com.xxyw.book.pojo.User;
import com.xxyw.book.service.UserService;

public class UserController {

    UserService userService = null;

    public String login(String uname, String pwd) {
        User user = userService.login(uname, pwd);
        System.out.println("user = " + user);
        return "index";
    }
}
