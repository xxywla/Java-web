package com.xxyw.qqzone.service.impl;

import com.xxyw.qqzone.dao.ReplyDAO;
import com.xxyw.qqzone.pojo.HostReply;
import com.xxyw.qqzone.pojo.Reply;
import com.xxyw.qqzone.pojo.Topic;
import com.xxyw.qqzone.pojo.UserBasic;
import com.xxyw.qqzone.service.HostReplyService;
import com.xxyw.qqzone.service.ReplyService;
import com.xxyw.qqzone.service.UserBasicService;

import java.util.List;

public class ReplyServiceImpl implements ReplyService {
    ReplyDAO replyDAO = null;
    HostReplyService hostReplyService = null;
    UserBasicService userBasicService = null;

    @Override
    public List<Reply> getReplyListByTopicId(Integer topicId) {
        List<Reply> replyList = replyDAO.getReplyList(new Topic(topicId));
        for (int i = 0; i < replyList.size(); i++) {
            Reply reply = replyList.get(i);

            UserBasic author = reply.getAuthor();
            author = userBasicService.getUserBasicById(author.getId());
            reply.setAuthor(author);

            HostReply hostReply = hostReplyService.getHostReplyByReplyId(reply.getId());

            reply.setHostReply(hostReply);
        }
        return replyList;
    }

    @Override
    public void addReply(Reply reply) {
        replyDAO.addReply(reply);
    }

    @Override
    public void delReply(Integer replyId) {
        // 如果有主人回复 需要先删除主人回复 因为有外键
        HostReply hostReply = hostReplyService.getHostReplyByReplyId(replyId);
        if (hostReply != null) {
            hostReplyService.delHostReplyById(hostReply.getId());
        }
        replyDAO.delReply(replyId);
    }
}
