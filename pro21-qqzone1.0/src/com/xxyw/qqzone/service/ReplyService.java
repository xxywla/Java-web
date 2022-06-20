package com.xxyw.qqzone.service;

import com.xxyw.qqzone.pojo.Reply;

import java.util.List;

public interface ReplyService {
    // 根据 TopicId 获取对应的回复列表
    List<Reply> getReplyListByTopicId(Integer topicId);
}
