package com.xxyw.qqzone.service;

import com.xxyw.qqzone.pojo.UserBasic;

import java.util.List;

public interface UserBasicService {
    UserBasic login(String loginId, String pwd);

    List<UserBasic> getFriendList(UserBasic userBasic);

    // 根据 id获取指定用户信息
    UserBasic getUserBasicById(Integer id);
}
