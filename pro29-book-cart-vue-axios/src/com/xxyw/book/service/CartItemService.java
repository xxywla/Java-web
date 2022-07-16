package com.xxyw.book.service;

import com.xxyw.book.pojo.Cart;
import com.xxyw.book.pojo.CartItem;
import com.xxyw.book.pojo.User;

import java.util.List;

public interface CartItemService {
    void addCartItem(CartItem cartItem);

    void updateCartItem(CartItem cartItem);

    void addOrUpdateCartItem(CartItem cartItem, Cart cart);

    List<CartItem> getCartItemList(User user);

    Cart getCart(User user);
}
