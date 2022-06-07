package com.xxyw.fruit.service.impl;

import com.xxyw.fruit.dao.FruitDAO;
import com.xxyw.fruit.pojo.Fruit;
import com.xxyw.fruit.service.FruitService;

import java.util.List;

public class FruitServiceImpl implements FruitService {

    private FruitDAO fruitDAO = null;

    @Override
    public List<Fruit> getFruitList(String keyword, Integer pageNo) {
        return fruitDAO.getFruitListByPageKey(keyword, pageNo);
    }

    @Override
    public void addFruit(Fruit fruit) {
        fruitDAO.addFruit(fruit);
    }

    @Override
    public Fruit getFruitByFid(Integer fid) {
        return fruitDAO.getFruitByFid(fid);
    }

    @Override
    public void delFruit(Integer fid) {
        fruitDAO.delFruitById(fid);
    }

    @Override
    public Integer getPageCount(String keyword) {
        Integer count = fruitDAO.getFruitCountByKey(keyword);
        int pageCount = (count + 5 - 1) / 5;
        return pageCount;
    }

    @Override
    public void updateFruit(Fruit fruit) {
        fruitDAO.updateFruit(fruit);
    }
}
