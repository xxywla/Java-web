package com.xxyw.fruit.servlets;

import com.xxyw.fruit.dao.FruitDAO;
import com.xxyw.fruit.dao.impl.FruitDAOImpl;
import com.xxyw.fruit.pojo.Fruit;
import com.xxyw.myssm.myspringmvc.ViewBaseServlet;
import com.xxyw.myssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit.do")
public class EditServlet extends ViewBaseServlet {
    FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fidStr = req.getParameter("fid");
        if (StringUtil.isNotEmpty(fidStr)) {
            Integer fid = Integer.parseInt(fidStr);
            // 从数据库中获取指定 fid 的水果
            Fruit fruit = fruitDAO.getFruitByFid(fid);
            // 保存到request作用域
            req.setAttribute("fruit", fruit);
            // 跳转到编辑页面
            super.processTemplate("edit", req, resp);
        }
    }
}
