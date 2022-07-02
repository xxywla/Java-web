package com.xxyw.book.controller;

import com.xxyw.book.pojo.Cart;
import com.xxyw.book.pojo.User;
import com.xxyw.book.service.CartItemService;
import com.xxyw.book.service.UserService;

import javax.servlet.http.HttpSession;

public class UserController {

    UserService userService = null;
    CartItemService cartItemService;

    public String login(String uname, String pwd, HttpSession session) {
        User user = userService.login(uname, pwd);
        System.out.println("user = " + user);


        if (user != null) {

            Cart cart = cartItemService.getCart(user);
            user.setCart(cart);

            session.setAttribute("currUser", user);
            return "redirect:book.do";
        }
        return "user/login";
    }
}
