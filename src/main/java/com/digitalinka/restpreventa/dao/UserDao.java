package com.digitalinka.restpreventa.dao;

import com.digitalinka.restpreventa.model.response.StatusResponse;
import com.digitalinka.restpreventa.model.response.UserResponse;

import java.util.List;

public interface UserDao {

    UserResponse loginByUserEmail(String userEmail,String password);
    UserResponse userByDni(List<Object[]> parametrosString);
    StatusResponse  updateUserLogin(List<Object[]> parametrosString);
}

