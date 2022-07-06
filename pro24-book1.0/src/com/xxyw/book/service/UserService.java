package com.xxyw.book.service;

import com.xxyw.book.pojo.User;

public interface UserService {
    User login(String uname, String pwd);

    void regist(User user);
}
