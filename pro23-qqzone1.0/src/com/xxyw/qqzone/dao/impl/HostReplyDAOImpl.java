package com.xxyw.qqzone.dao.impl;

import com.xxyw.myssm.basedao.BaseDAO;
import com.xxyw.qqzone.dao.HostReplyDAO;
import com.xxyw.qqzone.pojo.HostReply;

public class HostReplyDAOImpl extends BaseDAO<HostReply> implements HostReplyDAO {
    @Override
    public HostReply getHostReplyByReplyId(Integer replyId) {
        return load("select * from t_host_reply where reply = ? ", replyId);
    }

    @Override
    public void delHostReplyById(Integer hostReplyId) {
        executeUpdate("delete from t_host_reply where id = ? ", hostReplyId);
    }
}
