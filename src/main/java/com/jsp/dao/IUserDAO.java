package com.jsp.dao;

import com.jsp.model.UserModel;

public interface IUserDAO extends GenericDAO<UserModel>{
    UserModel findByUsernameAndPasswordAndRole(String name, String password, Integer status);
}
