package com.jsp.dao;

import com.jsp.model.NewsModel;
import com.jsp.paging.Pageble;

import java.util.List;

public interface INewsDAO extends GenericDAO<NewsModel>{
    NewsModel findOne(Long id);
    List<NewsModel> findByCategoryId(Long CategoryId);
    Long save(NewsModel newModel);
    void update(NewsModel updateNew);
    void delete(Long id);
    List<NewsModel> findAll(Pageble pageble);
    int count();
}
