package com.xxyw.qqzone.service.impl;

import com.xxyw.qqzone.dao.ReplyDAO;
import com.xxyw.qqzone.pojo.HostReply;
import com.xxyw.qqzone.pojo.Reply;
import com.xxyw.qqzone.pojo.Topic;
import com.xxyw.qqzone.service.HostReplyService;
import com.xxyw.qqzone.service.ReplyService;

import java.util.List;

public class ReplyServiceImpl implements ReplyService {
    ReplyDAO replyDAO = null;
    HostReplyService hostReplyService = null;

    @Override
    public List<Reply> getReplyListByTopicId(Integer topicId) {
        List<Reply> replyList = replyDAO.getReplyList(new Topic(topicId));
        for (int i = 0; i < replyList.size(); i++) {
            Reply reply = replyList.get(i);

            HostReply hostReply = hostReplyService.getHostReplyByReplyId(reply.getId());

            reply.setHostReply(hostReply);
        }
        return replyList;
    }
}
