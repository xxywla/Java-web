package com.xxyw.qqzone.service;

import com.xxyw.qqzone.dao.UserBasicDAO;
import com.xxyw.qqzone.pojo.UserBasic;

import java.util.List;

public interface UserBasicService {
    UserBasic login(String loginId, String pwd);

    List<UserBasic> getFriendList(UserBasic userBasic);
}
