package com.jsp.service.impl;

import com.jsp.dao.INewsDAO;
import com.jsp.dao.impl.NewDAO;
import com.jsp.model.CategoryModel;
import com.jsp.model.NewsModel;
import com.jsp.paging.Pageble;
import com.jsp.service.ICategoryService;
import com.jsp.service.INewsService;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

public class NewService implements INewsService {

    @Inject
    private INewsDAO newDAO;

    @Inject
    ICategoryService categoryService;

    @Override
    public NewsModel findOne(Long id) {
        NewsModel newsModel = newDAO.findOne(id);
        CategoryModel categoryModel = categoryService.findOne(newsModel.getCategoryId());
        newsModel.setCategoryCode(categoryModel.getCode());
        return newsModel;
    }

    @Override
    public List<NewsModel> findByCategoryId(Long categoryId) {
        return newDAO.findByCategoryId(categoryId);
    }

    @Override
    public NewsModel save(NewsModel newModel) {
        CategoryModel categoryModel = categoryService.findByCategoryCode(newModel.getCategoryCode());
        newModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        newModel.setCategoryId(categoryModel.getId());
        Long newId = newDAO.save(newModel);
        return newDAO.findOne(newId);
    }

    @Override
    public NewsModel update(NewsModel updateNew) {
        NewsModel oldNew = newDAO.findOne(updateNew.getId());
        CategoryModel categoryModel = categoryService.findByCategoryCode(updateNew.getCategoryCode());

        updateNew.setCreatedBy(oldNew.getCreatedBy());
        updateNew.setCreatedDate(oldNew.getCreatedDate());
        updateNew.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        updateNew.setCategoryId(categoryModel.getId());

        newDAO.update(updateNew);
        return newDAO.findOne(updateNew.getId());
    }

    @Override
    public void delete(Long[] ids) {
        for (Long value : ids) {
            newDAO.delete(value);
        }
    }

    @Override
    public List<NewsModel> findAll(Pageble pageble) {
        return newDAO.findAll(pageble);

    }

    @Override
    public int getTotalItem() {
        return newDAO.count();
    }


}
