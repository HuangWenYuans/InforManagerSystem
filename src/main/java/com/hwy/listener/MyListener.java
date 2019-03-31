/**
 * Copyright (C), 2018
 * FileName: MyListener
 * Author:   huangwenyuan
 * Date:     2018/11/20 23:24
 * Description:
 */

package com.hwy.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 功能描述:
 *
 * @author huangwenyuan
 * @create 2018/11/20
 * @since 1.0.0
 */

@WebListener
public class MyListener implements HttpSessionListener, ServletContextListener {
    /***
     * session在创建时人数自增
     * @param se
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        //获取ServletContext对象
        ServletContext sc = se.getSession().getServletContext();
        //    获取在线统计人数的变量
        int count = (int) sc.getAttribute("count");
        //   存储人数变量到servletContext中
        sc.setAttribute("count", ++count);
    }

    /***
     * session在销毁时人数自减
     * @param se
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        //获取ServletContext对象
        ServletContext sc = se.getSession().getServletContext();
        //    获取在线统计人数的变量
        int count = (int) sc.getAttribute("count");
        //   存储
        sc.setAttribute("count", --count);
    }


    /***
     * application对象初始化
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //    获取application对象
        ServletContext sc = sce.getServletContext();
        //    在application对象中存储变量用来统计在线人数
        sc.setAttribute("count", 0);


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}

    