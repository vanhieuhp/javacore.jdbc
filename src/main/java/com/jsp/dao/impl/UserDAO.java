package com.jsp.dao.impl;

import com.jsp.dao.IUserDAO;
import com.jsp.mapper.UserMapper;
import com.jsp.model.UserModel;

import java.util.List;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO {
    @Override
    public UserModel findByUsernameAndPasswordAndRole(String username, String password, Integer status) {
        StringBuilder sql = new StringBuilder("SELECT * FROM user AS u ");
        sql.append(" INNER JOIN role AS r ON r.id = u.roleid");
        sql.append(" WHERE username = ? and password = ? and status = ?");
        List<UserModel> users =  query(sql.toString(), new UserMapper(), username, password, status);
        return users.isEmpty() ? null : users.get(0);
    }
}
