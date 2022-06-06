package com.xxyw.fruit.controllers;

import com.xxyw.fruit.dao.FruitDAO;
import com.xxyw.fruit.dao.impl.FruitDAOImpl;
import com.xxyw.fruit.pojo.Fruit;
import com.xxyw.myssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class FruitController {

    FruitDAO fruitDAO = new FruitDAOImpl();

    private String index(String oper, String keyword, Integer pageNum, HttpServletRequest req) {

        HttpSession session = req.getSession();

        if (pageNum == null) {
            pageNum = 1;
        }

        if (StringUtil.isNotEmpty(oper) && "search".equals(oper)) {
            // 通过查询发送过来的请求
            pageNum = 1;
            if (StringUtil.isEmpty(keyword)) {
                keyword = "";
            }
            session.setAttribute("keyword", keyword);
        } else {
            // 通过上一页下一页发送过来的请求
            Object keywordObj = session.getAttribute("keyword");
            if (keywordObj != null) {
                keyword = (String) keywordObj;
            } else {
                keyword = "";
            }
        }

        session.setAttribute("pageNum", pageNum);

        Integer fruitCount = fruitDAO.getFruitCountByKey(keyword);
        int pageCount = (fruitCount + 5 - 1) / 5;
        session.setAttribute("pageCount", pageCount);

        List<Fruit> fruitList = fruitDAO.getFruitListByPageKey(keyword, pageNum);
        session.setAttribute("fruitList", fruitList);

        return "index";
    }

    private String addForm() {
        return "add";
    }

    private String addAction(String fname, Integer price, Integer fcount, String remark) {

        fruitDAO.addFruit(new Fruit(0, fname, price, fcount, remark));
        return "redirect:fruit.do";
    }


    private String del(Integer fid) {
        if (fid != null) {
            fruitDAO.delFruitById(fid);
            return "redirect:fruit.do";
        }
        return "error";
    }

    private String edit(Integer fid, HttpServletRequest req) {
        if (fid != null) {
            // 从数据库中获取指定 fid 的水果
            Fruit fruit = fruitDAO.getFruitByFid(fid);
            // 保存到request作用域
            req.setAttribute("fruit", fruit);
            // 跳转到编辑页面
            return "edit";
        }
        return "error";
    }

    private String update(Integer fid, String fname, Integer price, Integer fcount, String remark) {

        boolean result = fruitDAO.updateFruit(new Fruit(fid, fname, price, fcount, remark));
        System.out.println(result ? "添加成功" : "添加失败");

        return "redirect:fruit.do";
    }
}
