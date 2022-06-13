package com.jsp.dao.impl;

import com.jsp.dao.INewsDAO;
import com.jsp.mapper.NewMapper;
import com.jsp.model.NewsModel;
import com.jsp.paging.Pageble;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class NewDAO extends AbstractDAO<NewsModel> implements INewsDAO {

    @Override
    public NewsModel findOne(Long id) {
        String sql = "SELECT * FROM news WHERE id = ?";
        List<NewsModel> news = query(sql, new NewMapper(), id);
        return news.isEmpty() ? null : news.get(0);
    }

    @Override
    public List<NewsModel> findByCategoryId(Long categoryId) {
        String sql = "SELECT * FROM news WHERE categoryid = ?";
        return query(sql, new NewMapper(), categoryId);
    }

    @Override
    public Long save(NewsModel newsModel) {
        StringBuilder sql = new StringBuilder("INSERT INTO news (title, thumbnail, shortdescription, content, " +
                "categoryid, createddate, createdby ) ");
        sql.append("VALUES( ?, ?, ?, ?, ?, ?, ?)");
        return insert(sql.toString(), newsModel.getTitle(), newsModel.getThumbnail(), newsModel.getShortDescription(),
                newsModel.getContent(), newsModel.getCategoryId(), newsModel.getCreatedDate(),
                newsModel.getCreatedBy());
    }

    @Override
    public void update(NewsModel updateNew) {
        StringBuilder sql = new StringBuilder("UPDATE news SET title = ?, thumbnail = ?, shortdescription = ?, content = ?,");
        sql.append(" categoryid = ?, createddate = ?, createdby = ?, modifiedby = ?, modifieddate = ? WHERE id = ?");
        update(sql.toString(), updateNew.getTitle(), updateNew.getThumbnail(), updateNew.getShortDescription(),
                updateNew.getContent(), updateNew.getCategoryId(), updateNew.getCreatedDate(),
                updateNew.getCreatedBy(), updateNew.getModifiedBy(), updateNew.getModifiedDate(), updateNew.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM news WHERE id = ?";
        update(sql, id);
    }

    @Override
    public int count() {
        String sql = "SELECT COUNT(*) FROM news";
        return count(sql);
    }

    @Override
    public List<NewsModel> findAll(Pageble pageble) {

        StringBuilder sql = new StringBuilder("SELECT * FROM news ");
        if (pageble.getSorter() != null && StringUtils.isNoneBlank(pageble.getSorter().getSortBy()) && StringUtils.isNoneBlank(pageble.getSorter().getSortName())) {
            sql.append("ORDER BY " + pageble.getSorter().getSortName() + " "+ pageble.getSorter().getSortBy());
        }
        if (pageble.getOffSet() != null && pageble.getMaxPageItem() != null) {
            sql.append(" LIMIT " + pageble.getOffSet() + ", " + pageble.getMaxPageItem() + ";");
        }
        System.out.println(sql.toString());
        return query(sql.toString(), new NewMapper());
    }
}
