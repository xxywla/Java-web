package com.xxyw.fruit.servlets;

import com.xxyw.fruit.dao.FruitDAO;
import com.xxyw.fruit.dao.impl.FruitDAOImpl;
import com.xxyw.fruit.pojo.Fruit;
import com.xxyw.myssm.myspringmvc.ViewBaseServlet;
import com.xxyw.myssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {
    FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String keyword = "";
        Integer pageNum = 1;
        HttpSession session = req.getSession();

        String oper = req.getParameter("oper");
        if (StringUtil.isNotEmpty(oper) && "search".equals(oper)) {
            // 通过查询发送过来的请求
            keyword = req.getParameter("keyword");
            session.setAttribute("keyword", keyword);

            pageNum = 1;

        } else {
            // 通过上一页下一页发送过来的请求
            keyword = (String) session.getAttribute("keyword");
            if (StringUtil.isEmpty(keyword)) {
                keyword = "";
            }
            String pageNumStr = req.getParameter("pageNum");
            if (StringUtil.isNotEmpty(pageNumStr)) {
                pageNum = Integer.parseInt(pageNumStr);
            }
        }


        session.setAttribute("pageNum", pageNum);

        //Integer fruitCount = fruitDAO.getFruitCount();
        Integer fruitCount = fruitDAO.getFruitCountByKey(keyword);
        int pageCount = (fruitCount + 5 - 1) / 5;
        session.setAttribute("pageCount", pageCount);

        //List<Fruit> fruitList = fruitDAO.getFruitList();
        //List<Fruit> fruitList = fruitDAO.getFruitListByPage(pageNum);
        List<Fruit> fruitList = fruitDAO.getFruitListByPageKey(keyword, pageNum);
        session.setAttribute("fruitList", fruitList);

        super.processTemplate("index", req, resp);
    }
}
