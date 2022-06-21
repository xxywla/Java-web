package com.xxyw.qqzone.service.impl;

import com.xxyw.qqzone.dao.TopicDAO;
import com.xxyw.qqzone.pojo.Reply;
import com.xxyw.qqzone.pojo.Topic;
import com.xxyw.qqzone.pojo.UserBasic;
import com.xxyw.qqzone.service.ReplyService;
import com.xxyw.qqzone.service.TopicService;
import com.xxyw.qqzone.service.UserBasicService;

import java.util.List;

public class TopicServiceImpl implements TopicService {
    private TopicDAO topicDAO = null;
    private ReplyService replyService = null;
    private UserBasicService userBasicService = null;

    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return topicDAO.getTopicList(userBasic);
    }

    @Override
    public Topic getTopicById(Integer topicId) {
        Topic topic = topicDAO.getTopic(topicId);

        // 查到的只有作者的 id 需要根据 id 获取作者的其他属性
        UserBasic author = topic.getAuthor();
        author = userBasicService.getUserBasicById(author.getId());

        topic.setAuthor(author);

        List<Reply> replyList = replyService.getReplyListByTopicId(topicId);

        topic.setReplyList(replyList);

        return topic;
    }
}
