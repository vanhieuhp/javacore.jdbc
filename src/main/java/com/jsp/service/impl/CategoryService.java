package com.jsp.service.impl;

import com.jsp.dao.ICategoryDAO;
import com.jsp.model.CategoryModel;
import com.jsp.service.ICategoryService;

import javax.inject.Inject;
import java.util.List;

public class CategoryService implements ICategoryService {

    @Inject
    private ICategoryDAO categoryDAO;

    @Override
    public List<CategoryModel> findAll(){
        return categoryDAO.findAll();
    }

    @Override
    public CategoryModel findOne(Long id) {
        return categoryDAO.findOne(id);
    }

    @Override
    public CategoryModel findByCategoryCode(String code) {
        return categoryDAO.findByCategoryId(code);
    }
}
