/**
 * Copyright (C), 2018
 * FileName: CookieServlet
 * Author:   huangwenyuan
 * Date:     2018/11/30 21:11
 * Description:
 */

package com.hwy.servlet;

import com.hwy.pojo.User;
import com.hwy.service.UserService;
import com.hwy.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 功能描述:Cookie信息校验
 * <p>
 * 判断请求中是否携带正确的Cookie信息
 * 如果有则校验Cookie信息是否正确
 * 如果校验正确则直接响应主页面给用户
 * 如果校验不正确则响应登录页面给用户
 * 没有则请求转发给登录页面
 *
 * @author huangwenyuan
 * @create 2018/11/30
 * @since 1.0.0
 */

@WebServlet("/cookie")
public class CookieServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求编码格式
        request.setCharacterEncoding("utf-8");
        //设置响应编码格式
        response.setContentType("text/html;charset=utf-8");
        //    获取请求信息
        //获取Cookie信息
        Cookie[] cks = request.getCookies();

        //    处理请求信息

        if (cks != null) {
            //存在cookie

            //声明一个变量用于存储“uid”cookie的值
            String uid = "";
            //    遍历cookie信息
            for (Cookie c : cks) {
                //取出键为uid的cookie的值，赋值给变量uid
                if ("uid".equals(c.getName())) {
                    uid = c.getValue();
                }
            }

            //用户清除了历史记录中的uid的cookie
            //但是没有将所有cookie清除
            //校验uid是否存在
            if ("".equals(uid)) {
                //cookie中不存在键为uid的信息，则重定向到登录界面
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                //   存在则校验uid对应的用户信息是否正确
                //（目的是避免产生用户在cookie过期时间内注销了账号后再次发起登录请求，还能免登录进入的bug）
                UserService us = new UserServiceImpl();
                User u = us.cookieUserLoginService(uid);
                if (u != null) {
                    //uid对应的用户信息正确
                    //    重定向到主页
                    //将用户数据存储到session中
                    request.getSession().setAttribute("user", u);
                    response.sendRedirect("main/main.jsp");
                } else {
                    //用户对应的登录信息错误
                    //请求转发到登录页面
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            }

        } else {
            //不存在cookie
            //    响应处理结果
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}

    