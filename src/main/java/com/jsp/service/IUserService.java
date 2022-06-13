package com.jsp.service;

import com.jsp.model.UserModel;

public interface IUserService {
    UserModel findByUsernameAndPasswordAndStatus(String username, String password, Integer status);
}
