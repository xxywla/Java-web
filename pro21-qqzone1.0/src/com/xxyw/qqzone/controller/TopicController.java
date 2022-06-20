package com.xxyw.qqzone.controller;

import com.xxyw.qqzone.pojo.Topic;
import com.xxyw.qqzone.service.TopicService;

import javax.servlet.http.HttpSession;

public class TopicController {

    private TopicService topicService = null;

    public String topicDetail(Integer id, HttpSession session) {

        Topic topic = topicService.getTopicById(id);
        session.setAttribute("topic", topic);

        return "frames/detail";
    }
}
