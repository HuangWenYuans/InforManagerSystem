/**
 * Copyright (C), 2018
 * FileName: User
 * Author:   huangwenyuan
 * Date:     2018/11/12 17:35
 * Description:
 */

package com.hwy.pojo;

import java.util.HashMap;

/**
 * 功能描述:
 *
 * @author huangwenyuan
 * @create 2018/11/12
 * @since 1.0.0
 */
public class User {
    private int uid;
    private String uname;
    private String pwd;
    private String sex;
    private int age;
    private String birth;

    public User(int uid, String uname, String pwd, String sex, int age, String birth) {
        this.uid = uid;
        this.uname = uname;
        this.pwd = pwd;
        this.sex = sex;
        this.age = age;
        this.birth = birth;
    }

    public User() {
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", pwd='" + pwd + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", birth='" + birth + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

    