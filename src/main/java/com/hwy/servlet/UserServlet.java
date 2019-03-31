/**
 * Copyright (C), 2018
 * FileName: UserServlet
 * Author:   huangwenyuan
 * Date:     2018/11/12 17:04
 * Description:
 */

package com.hwy.servlet;

import com.hwy.pojo.User;
import com.hwy.service.UserService;
import com.hwy.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * 功能描述:
 *
 * @author huangwenyuan
 * @create 2018/11/12
 * @since 1.0.0
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
    //声明日志对象
    Logger logger = Logger.getLogger(UserServlet.class);
    //获取service层对象
    UserService us = new UserServiceImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //    设置请求编码格式
        request.setCharacterEncoding("utf-8");
        //    设置响应编码格式
        response.setContentType("text/html;charset=utf-8");
        //获取操作符
        String oper = request.getParameter("oper");
        if ("login".equals(oper)) {
            //调用登录处理方法
            checkUserLogin(request, response);
        } else if ("out".equals(oper)) {
            //    调用退出功能
            userOut(request, response);
        } else if ("pwd".equals(oper)) {
            //    调用密码修改功能
            userChangePwd(request, response);
        } else if ("show".equals(oper)) {
            //    调用显示所有用户功能
            userShow(request, response);
        } else if ("register".equals(oper)) {
            //    调用注册功能
            userRegister(request, response);
        } else {
            logger.debug("没有找到对应操作符：" + oper);
            //System.out.println("没有找到对应操作符：" + oper);

        }
    }


    /***
     * 用户登录方法
     * @param request 请求
     * @param response 响应
     * @throws IOException
     */
    private void checkUserLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //    获取请求信息
        String uname = request.getParameter("uname");
        String pwd = request.getParameter("pwd");
        //    处理请求数据

        //校验
        User u = us.checkUserLoginService(uname, pwd);
        if (u != null) {
            //获取到了user对象说明登录成功

            //创建cookie信息，实现三天免登陆
            Cookie c = new Cookie("uid", u.getUid() + "");
            //设置cookie的有效期为3天
            c.setMaxAge(3 * 24 * 3600);
            //设置cookie携带的路径
            c.setPath("index.jsp");
            //在请求中添加cookie返回
            response.addCookie(c);

            //获取session对象
            HttpSession hs = request.getSession();
            //将用户数据存储到session中
            hs.setAttribute("user", u);

            //重定向
            response.sendRedirect("main/main.jsp");
        } else {
            //添加标识符到request中
            request.setAttribute("flag", 0);
            // 从数据库中没查到该用户说明登录失败
            //    请求转发
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

    }

    /***
     *     用户退出方法
     * @param request 请求
     * @param response 响应
     * @throws IOException
     */
    private void userOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //    获取session对象
        HttpSession hs = request.getSession();
        //    强制销毁session
        hs.invalidate();
        //    重定向到登录页面
        response.sendRedirect("login.jsp");

    }


    /***
     * 用户修改密码的方法
     * @param request 请求
     * @param response 响应
     */
    private void userChangePwd(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //    获取数据
        String newPwd = request.getParameter("newPwd");
        //从session中获取用户信息
        User u = (User) request.getSession().getAttribute("user");
        int uid = u.getUid();

        //    处理请求
        //     调用service处理
        int index = us.userChangePwdService(newPwd, uid);
        if (index > 0) {
            //获取session对象
            HttpSession hs = request.getSession();
            //设置标志用于标识密码修改后的跳转
            hs.setAttribute("flag", 1);
            //    重定向到登录页面
            response.sendRedirect("login.jsp");
        }

    }


    /***
     * 显示所有用户信息
     * @param request 请求
     * @param response 响应
     */
    private void userShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //    处理请求
        //    调用service
        List<User> lu = us.userShowService();
        //判断
        if (lu != null) {
            // 将查询到的用户存储到request对象中
            request.setAttribute("lu", lu);
            //    请求转发
            request.getRequestDispatcher("user/showUser.jsp").forward(request, response);
        }
    }

    /***
     * 用户注册方法
     * @param request 请求
     * @param response 响应
     */
    private void userRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取请求信息
        String uname = request.getParameter("uname");
        String pwd = request.getParameter("pwd");
        String sex = request.getParameter("sex");
        int age;
        if (!"".equals(request.getParameter("age"))) {
            age = Integer.parseInt(request.getParameter("age"));
        } else {
            age = 0;
        }
        String birth = request.getParameter("birth");
        String[] bs = null;
        if (!"".equals(birth)) {
            bs = birth.split("/");
            //将日期格式化为“2013-2-12”的格式
            birth = bs[2] + "-" + bs[0] + "-" + bs[1];
        }
        System.out.println(uname + ":" + pwd + ":" + sex + ":" + age + ":" + birth);
        User u = new User(0, uname, pwd, sex, age, birth);
        //处理请求信息
        //    调用业务层处理
        int index = us.userRegServcie(u);
        //响应处理结果
        if (index > 0) {
            //获取session
            HttpSession hs = request.getSession();
            hs.setAttribute("flag", "2");
            //    请求重定向
            response.sendRedirect("login.jsp");
        }


    }

}

    