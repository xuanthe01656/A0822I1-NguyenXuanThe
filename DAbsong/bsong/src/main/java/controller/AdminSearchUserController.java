package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library.AuthUtil;
import model.bean.User;
import model.dao.SearchDAO;

import java.io.IOException;
import java.util.ArrayList;

public class AdminSearchUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminSearchUserController() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/Login");
			return;
		}
		doPost(request, response);	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/Login");
			return;
		}
		String key = request.getParameter("key");
		SearchDAO objSearchDAO = new SearchDAO();
		ArrayList<User> listU = objSearchDAO.searchUser(key);
		request.setAttribute("listU", listU);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/user/resultSearch.jsp");
		rd.forward(request, response);
	}

}
