package com.jsp.controller.web;

import com.jsp.model.UserModel;
import com.jsp.service.ICategoryService;
import com.jsp.service.IUserService;
import com.jsp.utils.FormUtil;
import com.jsp.utils.SessionUtil;

import java.io.IOException;
import java.util.ResourceBundle;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/trang-chu", "/login", "/logout"})
public class HomeController extends HttpServlet{

	@Inject
	ICategoryService categoryService;

	@Inject
	IUserService userService;

	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action != null && action.equals("login")) {
			String message = req.getParameter("message");
			String alert = req.getParameter("alert");
			if (message != null && alert != null) {
				req.setAttribute("message", resourceBundle.getString(message));
				req.setAttribute("alert", alert);
			}
			RequestDispatcher rd = req.getRequestDispatcher("/views/login.jsp");
			rd.forward(req, resp);
		} else if (action != null && action.equals("logout")) {
			SessionUtil.getInstance().removeValue(req, "USERMODEL");
			resp.sendRedirect(req.getContextPath() + "/trang-chu");
		} else {
			req.setAttribute("categories", categoryService.findAll());
			RequestDispatcher rd = req.getRequestDispatcher("/views/web/home.jsp");
			rd.forward(req, resp);

		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action != null && action.equals("login")) {
			UserModel model = FormUtil.toModel(UserModel.class, req);
		    model = userService.findByUsernameAndPasswordAndStatus(model.getUsername(), model.getPassword(), 1);
			if (model != null) {
				SessionUtil.getInstance().putValue(req, "USERMODEL", model);
				if (model.getRole().getCode().equals("ADMIN")) {
					resp.sendRedirect(req.getContextPath() + "/admin-home");
				} else if (model.getRole().getCode().equals("USER")){
					resp.sendRedirect(req.getContextPath() + "/trang-chu");
				}
			} else {
				resp.sendRedirect(req.getContextPath() + "/login?action=login&message=user_password_invalid&alert=danger");
			}
		}
	}
}
