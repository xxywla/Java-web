package com.xxyw.fruit.dao.impl;

import com.xxyw.fruit.dao.FruitDAO;
import com.xxyw.myssm.basedao.BaseDAO;
import com.xxyw.fruit.pojo.Fruit;

import java.util.List;

public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {
    @Override
    public List<Fruit> getFruitList() {
        return super.executeQuery("select * from t_fruit");
    }

    @Override
    public List<Fruit> getFruitListByPage(Integer pageNum) {
        return super.executeQuery("select * from t_fruit limit ? , 5 ", (pageNum - 1) * 5);
    }

    @Override
    public List<Fruit> getFruitListByPageKey(String keyword, Integer pageNum) {
        return super.executeQuery("select * from t_fruit where fname like ? or remark like ? limit ? , 5 ", "%" + keyword + "%", "%" + keyword + "%", (pageNum - 1) * 5);
    }

    @Override
    public Integer getFruitCount() {
        return ((Long) super.executeComplexQuery("select count(*) from t_fruit")[0]).intValue();
    }

    @Override
    public Integer getFruitCountByKey(String keyword) {
        return ((Long) super.executeComplexQuery("select count(*) from t_fruit where fname like ? or remark like ? ", "%" + keyword + "%", "%" + keyword + "%")[0]).intValue();
    }

    @Override
    public Fruit getFruitByFid(Integer fid) {
        String sql = "select * from t_fruit where fid = ?";
        return super.load(sql, fid);
    }

    @Override
    public boolean addFruit(Fruit fruit) {
        String sql = "insert into t_fruit values(0,?,?,?,?)";
        int count = super.executeUpdate(sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark());
        //insert语句返回的是自增列的值，而不是影响行数
        //System.out.println(count);
        return count > 0;
    }

    @Override
    public boolean updateFruit(Fruit fruit) {
        String sql = "update t_fruit set fname = ? , price = ? , fcount = ? , remark = ? where fid = ? ";
        return super.executeUpdate(sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark(), fruit.getFid()) > 0;
    }

    @Override
    public void delFruitById(Integer fid) {
        String sql = "delete from t_fruit where fid = ? ";
        super.executeUpdate(sql, fid);
    }

    @Override
    public Fruit getFruitByFname(String fname) {
        return super.load("select * from t_fruit where fname like ? ", fname);
    }

    @Override
    public boolean delFruit(String fname) {
        String sql = "delete from t_fruit where fname like ? ";
        return super.executeUpdate(sql, fname) > 0;
    }
}