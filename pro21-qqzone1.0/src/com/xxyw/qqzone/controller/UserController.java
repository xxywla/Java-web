package com.xxyw.qqzone.controller;

import com.xxyw.qqzone.pojo.Topic;
import com.xxyw.qqzone.pojo.UserBasic;
import com.xxyw.qqzone.service.TopicService;
import com.xxyw.qqzone.service.UserBasicService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class UserController {
    private UserBasicService userBasicService;
    private TopicService topicService;

    public String login(String loginId, String pwd, HttpSession session) {
        // 1.登录验证
        UserBasic userBasic = userBasicService.login(loginId, pwd);
        if (userBasic != null) {
            // 1-1 获取相关的好友信息
            List<UserBasic> friendList = userBasicService.getFriendList(userBasic);
            // 1-2 获取相关的日志列表信息
            List<Topic> topicList = topicService.getTopicList(userBasic);

            userBasic.setFriendList(friendList);
            userBasic.setTopicList(topicList);

            // userBasic 保存的是登录者的信息
            session.setAttribute("userBasic", userBasic);
            // friend 保存的是当前进入的是谁的空间
            session.setAttribute("friend", userBasic);

            return "index";
        } else {
            return "login";
        }
    }
}
