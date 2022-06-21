package com.xxyw.qqzone.service;

import com.xxyw.qqzone.pojo.Reply;

import java.util.List;

public interface ReplyService {
    // 根据 TopicId 获取对应的回复列表
    List<Reply> getReplyListByTopicId(Integer topicId);

    // 增加对某个 topic 的回复
    void addReply(Reply reply);
}
