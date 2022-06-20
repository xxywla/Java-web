package com.xxyw.qqzone.service.impl;

import com.xxyw.qqzone.dao.TopicDAO;
import com.xxyw.qqzone.pojo.Reply;
import com.xxyw.qqzone.pojo.Topic;
import com.xxyw.qqzone.pojo.UserBasic;
import com.xxyw.qqzone.service.ReplyService;
import com.xxyw.qqzone.service.TopicService;

import java.util.List;

public class TopicServiceImpl implements TopicService {
    private TopicDAO topicDAO = null;
    private ReplyService replyService = null;

    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return topicDAO.getTopicList(userBasic);
    }

    @Override
    public Topic getTopicById(Integer topicId) {
        Topic topic = topicDAO.getTopic(topicId);

        List<Reply> replyList = replyService.getReplyListByTopicId(topicId);

        topic.setReplyList(replyList);

        return topic;
    }
}
