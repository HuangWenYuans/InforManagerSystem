/**
 * Copyright (C), 2018
 * FileName: MyFilter
 * Author:   huangwenyuan
 * Date:     2018/12/1 21:10
 * Description:
 */

package com.hwy.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 功能描述:
 *
 * @author huangwenyuan
 * @create 2018/12/1
 * @since 1.0.0
 */
@WebFilter("/*")
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //设置请求编码格式
        request.setCharacterEncoding("utf-8");
        //设置响应编码格式
        response.setContentType("text/html;charset=utf-8");
        //    放行
        chain.doFilter(request, response);


    }

    @Override
    public void destroy() {

    }
}

    