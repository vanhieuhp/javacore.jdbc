package com.jsp.service.impl;

import com.jsp.dao.IUserDAO;
import com.jsp.model.UserModel;
import com.jsp.service.IUserService;

import javax.inject.Inject;

public class UserService implements IUserService {

    @Inject
    IUserDAO userDAO;

    @Override
    public UserModel findByUsernameAndPasswordAndStatus(String username, String password, Integer status) {
        return userDAO.findByUsernameAndPasswordAndRole(username, password, status);
    }
}
