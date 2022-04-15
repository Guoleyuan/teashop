package com.guet.entity;

import com.guet.dao.UserDao;
import lombok.Data;

/**
 * 用户实体类，存储用户的帐号和密码，用来登陆系统
 */
@Data
public class User {

    //用户名
    private String userName;
    //密码
    private String userPassword;

}
