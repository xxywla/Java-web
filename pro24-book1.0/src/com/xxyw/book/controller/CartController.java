package com.xxyw.book.controller;

import com.xxyw.book.pojo.Book;
import com.xxyw.book.pojo.CartItem;
import com.xxyw.book.pojo.User;
import com.xxyw.book.service.CartItemService;

import javax.servlet.http.HttpSession;

public class CartController {

    private CartItemService cartItemService;

    public String addCart(Integer bookId, HttpSession session) {

        User user = (User) session.getAttribute("currUser");
        CartItem cartItem = new CartItem(new Book(bookId), 1, user);
        cartItemService.addOrUpdateCartItem(cartItem, user.getCart());

        return "redirect:cart.do";
    }
}
