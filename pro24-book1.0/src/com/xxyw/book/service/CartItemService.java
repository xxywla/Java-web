package com.xxyw.book.service;

import com.xxyw.book.pojo.Cart;
import com.xxyw.book.pojo.CartItem;
import com.xxyw.book.pojo.User;

public interface CartItemService {
    void addCartItem(CartItem cartItem);

    void updateCartItem(CartItem cartItem);

    void addOrUpdateCartItem(CartItem cartItem, Cart cart);

    Cart getCart(User user);
}
