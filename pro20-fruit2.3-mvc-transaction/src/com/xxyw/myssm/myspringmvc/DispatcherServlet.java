package com.xxyw.myssm.myspringmvc;

import com.xxyw.myssm.ioc.BeanFactory;
import com.xxyw.myssm.ioc.ClassPathXmlApplicationContext;
import com.xxyw.myssm.util.StringUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {

    private BeanFactory beanFactory;

    @Override
    public void init() throws ServletException {
        super.init();
        //beanFactory = new ClassPathXmlApplicationContext();

        ServletContext application = getServletContext();
        Object beanFactoryObj = application.getAttribute("beanFactory");
        if (beanFactoryObj != null) {
            beanFactory = (BeanFactory) beanFactoryObj;
        } else {
            throw new RuntimeException("IOC容器获取失败！");
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // url http://localhost:8080/pro15/hello.do  ->  hello
        String servletPath = req.getServletPath(); // 输出 /hello.do
        int lastDotIndex = servletPath.lastIndexOf(".do");
        servletPath = servletPath.substring(1, lastDotIndex);

        // 获取 Fruit 对象
        Object controllerBeanObj = beanFactory.getBean(servletPath);

        String operator = req.getParameter("operator");
        if (StringUtil.isEmpty(operator)) {
            operator = "index";
        }
        try {
            // 获取对象的方法
            Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
            // 找到调用该对象的哪一个方法
            for (Method method : methods) {
                if (operator.equals(method.getName())) {
                    // 获取方法参数
                    Parameter[] parameters = method.getParameters();
                    Object[] parameterValues = new Object[parameters.length];
                    for (int i = 0; i < parameters.length; i++) {
                        Parameter parameter = parameters[i];
                        String parameterName = parameter.getName();
                        // 特判参数 request response session
                        if ("req".equals(parameterName)) {
                            parameterValues[i] = req;
                        } else if ("resp".equals(parameterName)) {
                            parameterValues[i] = resp;
                        } else if ("session".equals(parameterName)) {
                            parameterValues[i] = req.getSession();
                        } else {
                            // 从请求中获取参数
                            String parameterValue = req.getParameter(parameterName);
                            String typeName = parameter.getType().getName();

                            Object parameterObj = parameterValue;
                            if (parameterObj != null) {
                                if ("java.lang.Integer".equals(typeName)) {
                                    parameterObj = Integer.parseInt(parameterValue);
                                }
                            }
                            parameterValues[i] = parameterObj;
                        }
                    }
                    // 调用FruitController中的方法
                    method.setAccessible(true);
                    Object returnObj = method.invoke(controllerBeanObj, parameterValues);

                    // 视图处理
                    String methodReturnStr = (String) returnObj;
                    if (methodReturnStr.startsWith("redirect:")) {
                        String redirectStr = methodReturnStr.substring("redirect:".length());
                        resp.sendRedirect(redirectStr);
                    } else {
                        super.processTemplate(methodReturnStr, req, resp);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DispatcherServletException("DispatcherServlet出错了...");
        }
    }
}
