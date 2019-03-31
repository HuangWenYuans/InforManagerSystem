package com.hwy.service;

import com.hwy.pojo.User;

import java.util.List;

public interface UserService {
    /***
     * 校验用户登录
     * @param uname 用户名
     * @param pwd 密码
     * @return 返回查询到的用户信息
     */
    User checkUserLoginService(String uname, String pwd);

    /***
     * 修改用户密码
     * @param newPwd 新密码
     * @param uid 用户ID
     * @return 一个int值
     */
    int userChangePwdService(String newPwd, int uid);

    /***
     * 获取所有的用户信息
     * @return
     */
    List<User> userShowService();

    /***
     * 实现用户注册的方法
     * @param u
     * @return
     */
    int userRegServcie(User u);

    /***
     * 检验用户cookie信息
     * @param uid
     * @return
     */
    User cookieUserLoginService(String uid);
}
