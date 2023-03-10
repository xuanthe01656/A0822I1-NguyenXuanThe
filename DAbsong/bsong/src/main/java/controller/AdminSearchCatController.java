package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library.AuthUtil;
import model.bean.Cat;
import model.dao.SearchDAO;

import java.io.IOException;
import java.util.ArrayList;

@MultipartConfig
public class AdminSearchCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdminSearchCatController() {
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
		String search = request.getParameter("key");
		SearchDAO objSDAO = new SearchDAO();
		ArrayList<Cat> listC = objSDAO.SearchItem(search);
		request.setAttribute("listC", listC);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/categories/resultSearch.jsp");
		rd.forward(request, response);
	}

}
