package com.jsp.service;

import com.jsp.model.CategoryModel;
import java.util.List;

public interface ICategoryService {
    List<CategoryModel> findAll();
    CategoryModel findOne(Long id);
    CategoryModel findByCategoryCode(String code);
}
