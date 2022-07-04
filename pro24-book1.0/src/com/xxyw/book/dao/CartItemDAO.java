package com.xxyw.book.dao;

import com.xxyw.book.pojo.CartItem;
import com.xxyw.book.pojo.User;

import java.util.List;

public interface CartItemDAO {
    void addCartItem(CartItem cartItem);

    void updateCartItem(CartItem cartItem);

    List<CartItem> getCartItemList(User user);

    void delete(CartItem cartItem);
}
