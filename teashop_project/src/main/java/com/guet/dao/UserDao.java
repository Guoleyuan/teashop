package com.guet.dao;

import com.guet.entity.User;

public interface UserDao {

    /**
     * 获取数据库的用户名和密码
     * @return
     */
    public User getUserMessage();
}
