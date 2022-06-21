package com.xxyw.qqzone.service;

import com.xxyw.qqzone.pojo.HostReply;

public interface HostReplyService {
    HostReply getHostReplyByReplyId(Integer replyId);
    // 根据 id 删除对应的主人回复
    void delHostReplyById(Integer hostReplyId);
}
