package com.xxyw.qqzone.service;

import com.xxyw.qqzone.dao.UserBasicDAO;
import com.xxyw.qqzone.pojo.Topic;
import com.xxyw.qqzone.pojo.UserBasic;

import java.util.List;

public interface TopicService {
    // 查询特定用户的日志列表
    List<Topic> getTopicList(UserBasic userBasic);
    // 根据 id 获取对应的 Topic
    Topic getTopicById(Integer topicId);
}
