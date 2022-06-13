package com.jsp.mapper;

import com.jsp.model.RoleModel;
import com.jsp.model.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<UserModel>{

    @Override
    public UserModel mapRow(ResultSet resultSet) {
        try {
            UserModel userModel = new UserModel();
            userModel.setId(resultSet.getLong("id"));
            userModel.setUsername(resultSet.getString("username"));
            userModel.setPassword(resultSet.getString("password"));
            userModel.setFullname(resultSet.getString("fullname"));
            userModel.setStatus(resultSet.getInt("status"));
            try {
                RoleModel roleModel = new RoleModel();
                roleModel.setName(resultSet.getString("name"));
                roleModel.setCode(resultSet.getString("code"));
                userModel.setRole(roleModel);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return userModel;
        } catch(SQLException e) {
            return null;
        }
    }
}
