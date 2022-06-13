package com.jsp.controller.admin;

import com.jsp.constant.SystemConstant;
import com.jsp.model.NewsModel;
import com.jsp.paging.PageRequest;
import com.jsp.paging.Pageble;
import com.jsp.service.ICategoryService;
import com.jsp.service.INewsService;
import com.jsp.sort.Sorter;
import com.jsp.utils.FormUtil;
import com.jsp.utils.MessageNotification;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/admin-new"})
public class NewController extends HttpServlet{

    @Inject
    private INewsService newsService;

    @Inject
    private ICategoryService categoryService;

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsModel model = FormUtil.toModel(NewsModel.class, req);
        String view = "";
        if (model.getType().equals(SystemConstant.LIST)) {
            Pageble pageble = new PageRequest(model.getCurrentPage(), model.getMaxPageItem(),
                    new Sorter(model.getSortName(), model.getSortBy()));
            model.setListResult(newsService.findAll(pageble));
            model.setTotalItem(newsService.getTotalItem());
            model.setTotalPage((int) Math.ceil( (double) model.getTotalItem() / model.getMaxPageItem() ));
            view = "/views/admin/new/list.jsp";
        } else if (model.getType().equals(SystemConstant.EDIT)) {
            if (model.getId() != null) {
                model =  newsService.findOne(model.getId());
            }
            req.setAttribute(SystemConstant.CATEGORIES, categoryService.findAll());
            view = "/views/admin/new/edit.jsp";
        }
        MessageNotification.showMessage(req);
        req.setAttribute(SystemConstant.MODEL, model);
        RequestDispatcher rd = req.getRequestDispatcher(view);
        rd.forward(req, resp);

    }
}
