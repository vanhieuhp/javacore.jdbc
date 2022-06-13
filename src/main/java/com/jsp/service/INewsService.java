package com.jsp.service;

import com.jsp.model.NewsModel;
import com.jsp.paging.Pageble;

import java.util.List;

public interface INewsService {
    NewsModel findOne(Long id);
    List<NewsModel> findByCategoryId(Long categoryId);
    NewsModel save(NewsModel newsModel);
    NewsModel update(NewsModel updateNew);
    void delete(Long[] ids);
    List<NewsModel> findAll(Pageble pageble);
    int getTotalItem();
}