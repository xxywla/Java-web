package com.xxyw.qqzone.service.impl;

import com.xxyw.qqzone.dao.HostReplyDAO;
import com.xxyw.qqzone.pojo.HostReply;
import com.xxyw.qqzone.service.HostReplyService;

public class HostReplyServiceImpl implements HostReplyService {

    HostReplyDAO hostReplyDAO = null;

    @Override
    public HostReply getHostReplyByReplyId(Integer replyId) {
        return hostReplyDAO.getHostReplyByReplyId(replyId);
    }
}
