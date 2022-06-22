package com.xxyw.qqzone.controller;

import com.xxyw.qqzone.pojo.Topic;
import com.xxyw.qqzone.pojo.UserBasic;
import com.xxyw.qqzone.service.TopicService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class TopicController {

    private TopicService topicService = null;

    public String topicDetail(Integer id, HttpSession session) {

        Topic topic = topicService.getTopicById(id);
        session.setAttribute("topic", topic);

        return "frames/detail";
    }

    public String delTopic(Integer topicId) {
        topicService.delTopicById(topicId);
        return "redirect:topic.do?operate=getTopicList";
    }

    public String getTopicList(HttpSession session) {

        UserBasic userBasic = (UserBasic) session.getAttribute("userBasic");

        List<Topic> topicList = topicService.getTopicList(userBasic);
        userBasic.setTopicList(topicList);
        session.setAttribute("friend", userBasic);

        return "frames/main";
    }
}
