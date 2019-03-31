/**
 * Copyright (C), 2018
 * FileName: UserServiceImpl
 * Author:   huangwenyuan
 * Date:     2018/11/12 17:46
 * Description:
 */

package com.hwy.service.impl;

import com.hwy.dao.UserDao;
import com.hwy.dao.impl.UserDaoImpl;
import com.hwy.pojo.User;
import com.hwy.service.UserService;
import org.apache.log4j.Logger;

import java.util.List;


/**
 * 功能描述:
 *
 * @author huangwenyuan
 * @create 2018/11/12
 * @since 1.0.0
 */
public class UserServiceImpl implements UserService {
    //声明日志对象
    Logger logger = Logger.getLogger(UserServiceImpl.class);
    //声明Dao层对象
    UserDao ud = new UserDaoImpl();


    /***
     * 校验用户登录
     * @param uname 用户名
     * @param pwd 密码
     * @return 返回查询到的用户信息
     */
    @Override
    public User checkUserLoginService(String uname, String pwd) {
        //打印日志
        logger.debug(uname + "发起了登录请求");
        User u = ud.checkUserLoginDao(uname, pwd);
        //判断
        if (u != null) {
            logger.debug(uname + "登录成功");
        } else {
            logger.debug(uname + "登录失败");
        }
        return ud.checkUserLoginDao(uname, pwd);
    }

    /***
     * 修改用户密码
     * @param newPwd 新密码
     * @param uid 用户ID
     * @return
     */
    @Override
    public int userChangePwdService(String newPwd, int uid) {
        logger.debug(uid + ":发起密码修改请求");
        int index = ud.userChangePwdDao(newPwd, uid);
        if (index > 0) {
            logger.debug(uid + ":密码修改成功");
        } else {
            logger.debug(uid + ":密码修失败");
        }
        return index;
    }

    /***
     * 获取所有的用户信息
     * @return
     */
    @Override
    public List<User> userShowService() {
        List<User> lu = ud.userShowDao();
        logger.debug("显示所有用户信息：" + lu);
        return lu;
    }


    /***
     * 实现用户注册的方法
     * @param u
     * @return
     */
    @Override
    public int userRegServcie(User u) {
        return ud.userRegDao(u);
    }

    /***
     * 检验用户cookie信息
     * @param uid
     * @return
     */
    @Override
    public User cookieUserLoginService(String uid) {
        return  ud.cookieUserLoginDao(uid);
    }
}

    