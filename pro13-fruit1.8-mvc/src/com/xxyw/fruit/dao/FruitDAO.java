package com.xxyw.fruit.dao;

import com.xxyw.fruit.pojo.Fruit;

import java.util.List;

public interface FruitDAO {
    //查询库存列表
    List<Fruit> getFruitList();

    // 查询指定页数
    List<Fruit> getFruitListByPage(Integer pageNum);

    // 查询指定页 关键字 的列表
    List<Fruit> getFruitListByPageKey(String keyword, Integer pageNum);

    // 获取记录条数
    Integer getFruitCount();

    // 获取指定关键字的总记录条数
    Integer getFruitCountByKey(String keyword);

    // 根据fid获取特定水果的库存信息
    Fruit getFruitByFid(Integer fid);

    //新增库存
    boolean addFruit(Fruit fruit);

    //修改库存
    boolean updateFruit(Fruit fruit);

    // 根据fid删除库存纪律
    void delFruitById(Integer fid);

    //根据名称查询特定库存
    Fruit getFruitByFname(String fname);

    //删除特定库存记录
    boolean delFruit(String fname);
}
