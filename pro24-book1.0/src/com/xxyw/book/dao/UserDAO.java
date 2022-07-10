package com.xxyw.book.dao;

import com.xxyw.book.pojo.User;

public interface UserDAO {
    User getUser(String uname, String pwd);

    void addUser(User user);

    User getUserByUname(String uname);
}
