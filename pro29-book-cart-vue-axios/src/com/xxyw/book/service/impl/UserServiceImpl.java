package com.xxyw.book.service.impl;

import com.xxyw.book.dao.UserDAO;
import com.xxyw.book.pojo.User;
import com.xxyw.book.service.UserService;

public class UserServiceImpl implements UserService {

    UserDAO userDAO = null;

    @Override
    public User login(String uname, String pwd) {
        return userDAO.getUser(uname, pwd);
    }

    @Override
    public void regist(User user) {
        userDAO.addUser(user);
    }

    @Override
    public User getUserByUname(String uname) {
        return userDAO.getUserByUname(uname);
    }
}
