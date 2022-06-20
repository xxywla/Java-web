package com.xxyw.qqzone.dao.impl;

import com.xxyw.myssm.basedao.BaseDAO;
import com.xxyw.qqzone.dao.ReplyDAO;
import com.xxyw.qqzone.pojo.Reply;
import com.xxyw.qqzone.pojo.Topic;

import java.util.List;

public class ReplyDAOImpl extends BaseDAO<Reply> implements ReplyDAO {
    @Override
    public List<Reply> getReplyList(Topic topic) {
        return executeQuery("select * from t_reply where topic = ? ", topic.getId());
    }

    @Override
    public void addReply(Reply reply) {

    }

    @Override
    public void delReply(Integer id) {

    }
}
