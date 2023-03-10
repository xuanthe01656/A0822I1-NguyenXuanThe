package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library.AuthUtil;
import model.dao.UserDAO;

import java.io.IOException;

public class AdminDeleteUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminDeleteUserController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/Login");
			return;
		}
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/Login");
			return;
		}
		int id = Integer.parseInt(request.getParameter("id"));
		UserDAO objUDAO = new UserDAO();
		int result=objUDAO.deleteUser(id);
		if(result>0) {
			response.sendRedirect(request.getContextPath()+"/admin/user?msg=3");
		}else {
			response.sendRedirect(request.getContextPath()+"/admin/user?msg=4");
		}
	}
}
