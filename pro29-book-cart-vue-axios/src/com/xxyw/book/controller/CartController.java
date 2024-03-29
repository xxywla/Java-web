package com.xxyw.book.controller;

import com.google.gson.Gson;
import com.xxyw.book.pojo.Book;
import com.xxyw.book.pojo.Cart;
import com.xxyw.book.pojo.CartItem;
import com.xxyw.book.pojo.User;
import com.xxyw.book.service.CartItemService;

import javax.servlet.http.HttpSession;

public class CartController {

    private CartItemService cartItemService;

    public String index(HttpSession session) {

        // 获取当前用户信息
        User user = (User) session.getAttribute("currUser");
        // 获取当前用户的购物车
        Cart cart = cartItemService.getCart(user);
        user.setCart(cart);
        session.setAttribute("currUser", user);
        // 到购物车页面
        return "cart/cart";
    }

    public String addCart(Integer bookId, HttpSession session) {

        User user = (User) session.getAttribute("currUser");
        CartItem cartItem = new CartItem(new Book(bookId), 1, user);
        cartItemService.addOrUpdateCartItem(cartItem, user.getCart());

        return "redirect:cart.do";
    }

    public String editBuyCount(Integer cartItemId, Integer buyCount) {

        CartItem cartItem = new CartItem();
        cartItem.setId(cartItemId);
        cartItem.setBuyCount(buyCount);
        cartItemService.updateCartItem(cartItem);

        return "";
    }

    public String getCart(HttpSession session) {
        User user = (User) session.getAttribute("currUser");
        Cart cart = cartItemService.getCart(user);

        cart.getTotalMoney();
        cart.getTotalBookCount();
        cart.getTotalCount();

        Gson gson = new Gson();
        String cartJsonStr = gson.toJson(cart);
        return "json:" + cartJsonStr;
    }
}
