package com.jsp.dao;

import com.jsp.model.CategoryModel;

import java.util.List;

public interface ICategoryDAO extends GenericDAO<CategoryModel>{
    List<CategoryModel> findAll();
    CategoryModel findOne(Long id);
    CategoryModel findByCategoryId(String code);
}
