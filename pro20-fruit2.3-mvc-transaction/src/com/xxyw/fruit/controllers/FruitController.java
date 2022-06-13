package com.xxyw.fruit.controllers;

import com.xxyw.fruit.pojo.Fruit;
import com.xxyw.fruit.service.FruitService;
import com.xxyw.myssm.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class FruitController {

    private FruitService fruitService = null;

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

        List<Fruit> fruitList = fruitService.getFruitList(keyword, pageNum);
        session.setAttribute("fruitList", fruitList);

        int pageCount = fruitService.getPageCount(keyword);
        session.setAttribute("pageCount", pageCount);

        return "index";
    }

    private String addForm() {
        return "add";
    }

    private String addAction(String fname, Integer price, Integer fcount, String remark) {

        fruitService.addFruit(new Fruit(0, fname, price, fcount, remark));
        return "redirect:fruit.do";
    }


    private String del(Integer fid) {
        if (fid != null) {
            fruitService.delFruit(fid);
            return "redirect:fruit.do";
        }
        return "error";
    }

    private String edit(Integer fid, HttpServletRequest req) {
        if (fid != null) {
            // 从数据库中获取指定 fid 的水果
            Fruit fruit = fruitService.getFruitByFid(fid);
            // 保存到request作用域
            req.setAttribute("fruit", fruit);
            // 跳转到编辑页面
            return "edit";
        }
        return "error";
    }

    private String update(Integer fid, String fname, Integer price, Integer fcount, String remark) {

        fruitService.updateFruit(new Fruit(fid, fname, price, fcount, remark));

        return "redirect:fruit.do";
    }
}
