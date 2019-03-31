package com.hwy.dao;

import com.hwy.pojo.User;

import java.util.List;


public interface UserDao {
    /***
     * 根据用户名和密码查询用户信息
     * @param uname 用户名
     * @param pwd 密码
     * @return 返回查询到的用户信息
     */
    User checkUserLoginDao(String uname, String pwd);

    /***
     * 根据用户ID修改用户密码
     * @param newPwd
     * @param uid
     * @return
     */
    int userChangePwdDao(String newPwd, int uid);

    /***
     * 获取所有的用户信息
     * @return
     */
    List<User> userShowDao();


    /***
     * 实现用户注册的方法
     * @param u
     * @return
     */
    int userRegDao(User u);

    /***
     * 根据Uid获取用户信息
     * @param uid
     * @return
     */
    User cookieUserLoginDao(String uid);
}
