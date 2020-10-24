package com.digitalinka.restpreventa.service;

import com.digitalinka.restpreventa.dao.UserDao;
import com.digitalinka.restpreventa.model.response.StatusResponse;
import com.digitalinka.restpreventa.model.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl  implements UserService{
    @Autowired
    private UserDao userDao;

    @Override
    public UserResponse loginByUserEmail(String userEmail, String password) {
        return userDao.loginByUserEmail(userEmail,password);
    }

    @Override
    public UserResponse userByDni(List<Object[]> parametrosString) {
        return userDao.userByDni(parametrosString);
    }

    @Override
    public StatusResponse updateUserLogin(List<Object[]> parametrosString) {
        return userDao.updateUserLogin(parametrosString);
    }
}
