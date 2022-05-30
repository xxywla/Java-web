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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@WebServlet("/fruit.do")
public class FruitServlet extends ViewBaseServlet {
    FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String operator = req.getParameter("operator");
        if (StringUtil.isEmpty(operator)) {
            operator = "index";
        }

        Method[] methods = this.getClass().getDeclaredMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            if (operator.equals(methodName)) {
                try {
                    method.invoke(this, req, resp);
                    return;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        throw new RuntimeException("非法的operator");
/*
        switch (operator) {
            case "index":
                index(req, resp);
                break;
            case "add":
                add(req, resp);
                break;
            case "del":
                del(req, resp);
                break;
            case "edit":
                edit(req, resp);
                break;
            case "update":
                update(req, resp);
                break;
            default:
                throw new RuntimeException("非法的operator");
        }*/
    }

    private void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String method = req.getMethod();

        if ("GET".equals(method)) {
            super.processTemplate("add", req, resp);
        } else if ("POST".equals(method)) {
            String fname = req.getParameter("fname");
            String priceStr = req.getParameter("price");
            int price = Integer.parseInt(priceStr);
            String fcountStr = req.getParameter("fcount");
            int fcount = Integer.parseInt(fcountStr);
            String remark = req.getParameter("remark");
            fruitDAO.addFruit(new Fruit(0, fname, price, fcount, remark));

            resp.sendRedirect("fruit.do");
        }
    }

    private void del(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int fid = Integer.parseInt(req.getParameter("fid"));
        fruitDAO.delFruitById(fid);

        resp.sendRedirect("fruit.do");
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String fname = req.getParameter("fname");
        String priceStr = req.getParameter("price");
        int price = Integer.parseInt(priceStr);
        String fcountStr = req.getParameter("fcount");
        int fcount = Integer.parseInt(fcountStr);
        String remark = req.getParameter("remark");
        String fidStr = req.getParameter("fid");
        Integer fid = Integer.parseInt(fidStr);
        boolean result = fruitDAO.updateFruit(new Fruit(fid, fname, price, fcount, remark));
        System.out.println(result ? "添加成功" : "添加失败");

        resp.sendRedirect("fruit.do");
    }
}
