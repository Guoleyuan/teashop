package com.guet.service.Impl;

import com.guet.controller.Login;
import com.guet.dao.Impl.UserDaoImpl;
import com.guet.dao.UserDao;
import com.guet.entity.User;
import com.guet.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao=new UserDaoImpl();


    /**
     * 判断用户名和密码是否正确
     * @param usernameText
     * @param passwordText
     * @return  true:用户名和密码正确
     */
    @Override
    public boolean isAccountTrue(String usernameText, String passwordText) {
        User user = userDao.getUserMessage();
        if (user.getUserName().equals(usernameText) && user.getUserPassword().equals(passwordText)){
            return true;
        }else {
            return false;
        }
    }
}
