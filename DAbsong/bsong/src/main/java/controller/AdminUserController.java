package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library.AuthUtil;
import model.bean.User;
import model.dao.UserDAO;

import java.io.IOException;
import java.util.ArrayList;

public class AdminUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminUserController() {
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
		int index = 0;
		if(request.getParameter("index")==null) {
			index=1;
		}else {
			index= Integer.parseInt(request.getParameter("index"));
		}
		UserDAO objUDAO = new UserDAO();
		ArrayList<User> listU = objUDAO.getItem();
		request.setAttribute("listU", listU);
		request.setAttribute("index", index);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/user/index.jsp");
		rd.forward(request, response);
	}

}
