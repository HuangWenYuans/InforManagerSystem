/**
 * Copyright (C), 2018
 * FileName: UserDaoImpl
 * Author:   huangwenyuan
 * Date:     2018/11/12 17:52
 * Description:
 */

package com.hwy.dao.impl;

import com.hwy.dao.UserDao;
import com.hwy.pojo.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述:
 *
 * @author huangwenyuan
 * @create 2018/11/12
 * @since 1.0.0
 */
public class UserDaoImpl implements UserDao {

    /***
     * 根据用户名和密码查询用户信息
     * @param uname 用户名
     * @param pwd 密码
     * @return 返回查询到的用户信息
     */
    @Override
    public User checkUserLoginDao(String uname, String pwd) {
        //声明jdbc对象
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //声明变量
        User u = null;

        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/managersys", "root", "hwy79868058");
            //创建sql命令
            String sql = "select * from t_user where uname=? and pwd=?";
            //创建sql命令对象
            ps = conn.prepareStatement(sql);
            //给占位符赋值
            ps.setString(1, uname);
            ps.setString(2, pwd);
            //执行sql
            rs = ps.executeQuery();
            //遍历结果
            while (rs.next()) {
                //    给变量赋值
                u = new User();
                u.setUid(rs.getInt("uid"));
                u.setUname(rs.getString("uname"));
                u.setPwd(rs.getString("pwd"));
                u.setSex(rs.getString("sex"));
                u.setAge(rs.getInt("age"));
                u.setBirth(rs.getString("birth"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //返回结果
        return u;
    }

    /***
     * 根据用户ID修改用户密码
     * @param newPwd
     * @param uid
     * @return
     */
    @Override
    public int userChangePwdDao(String newPwd, int uid) {
        //声明jdbc对象
        Connection conn = null;
        PreparedStatement ps = null;
        //创建变量
        int index = -1;
        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/managersys", "root", "hwy79868058");
            //创建sql命令
            String sql = "update t_user set pwd=? where uid=?";
            //创建sql命令对象
            ps = conn.prepareStatement(sql);
            //给占位符赋值
            ps.setString(1, newPwd);
            ps.setInt(2, uid);
            //执行sql语句
            index = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        //返回结果
        return index;
    }

    /***
     * 获取所有的用户信息
     * @return
     */
    @Override
    public List<User> userShowDao() {
        //声明jdbc对象
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //声明变量
        List<User> lu = null;
        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/managersys", "root", "hwy79868058");
            //创建sql命令
            String sql = "select * from t_user";
            //创建sql命令对象
            ps = conn.prepareStatement(sql);
            //执行sql
            rs = ps.executeQuery();
            //    给变量集合赋值
            lu = new ArrayList<User>();
            //遍历结果
            while (rs.next()) {
                User u = new User();
                u.setUid(rs.getInt("uid"));
                u.setUname(rs.getString("uname"));
                u.setPwd(rs.getString("pwd"));
                u.setSex(rs.getString("sex"));
                u.setAge(rs.getInt("age"));
                u.setBirth(rs.getString("birth"));
                //    将对象存储到集合中
                lu.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //返回结果
        return lu;
    }

    /***
     * 实现用户注册的方法
     * @param u
     * @return
     */
    @Override
    public int userRegDao(User u) {
        //    声明jdbc对象
        Connection conn = null;
        PreparedStatement ps = null;
        //    声明变量
        int index = -1;
        //    加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //    获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/managersys", "root", "hwy79868058");
            //    创建sql命令
            String sql = "insert into t_user values(default,?,?,?,?,?)";
            //    创建sql命令对象
            ps = conn.prepareStatement(sql);
            //   给占位符赋值
            ps.setString(1, u.getUname());
            ps.setString(2, u.getPwd());
            ps.setString(3, u.getSex());
            ps.setInt(4, u.getAge());
            ps.setString(5, u.getBirth());
            //    执行sql语句
            index = ps.executeUpdate();
            //    关闭资源
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //    返回结果
        return index;
    }

    /***
     * 根据Uid获取用户信息
     * @param uid
     * @return
     */
    @Override
    public User cookieUserLoginDao(String uid) {
        //声明jdbc对象
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //声明变量
        User u = null;
        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/managersys", "root", "hwy79868058");
            //创建sql命令
            String sql = "select * from t_user where uid = ?";
            //创建sql命令对象
            ps = conn.prepareStatement(sql);
            //给占位符赋值
            ps.setString(1, uid);
            //执行sql
            rs = ps.executeQuery();
            //遍历结果
            while (rs.next()) {
                //给变量赋值
                u = new User();
                u.setUid(rs.getInt("uid"));
                u.setUname(rs.getString("uname"));
                u.setPwd(rs.getString("pwd"));
                u.setSex(rs.getString("sex"));
                u.setAge(rs.getInt("age"));
                u.setBirth(rs.getString("birth"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源

        }
        //返回结果
        return u;
    }
}
    