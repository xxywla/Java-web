package com.xxyw.qqzone.dao;

import com.xxyw.qqzone.pojo.HostReply;

public interface HostReplyDAO {
    HostReply getHostReplyByReplyId(Integer replyId);
    void delHostReplyById(Integer hostReplyId);
}
