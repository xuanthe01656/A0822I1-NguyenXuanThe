package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library.AuthUtil;
import model.bean.Cat;
import model.dao.CatDAO;

import java.io.IOException;
import java.util.ArrayList;

public class AdminCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CatDAO catDAO;
    public AdminCatController() {
        super();
        catDAO = new CatDAO();
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
		int index=0;
		if(request.getParameter("index")==null) {
			index=1;
		}else {
			index=Integer.parseInt(request.getParameter("index"));
		}
		CatDAO objCDAO = new CatDAO();
		ArrayList<Cat> listCat = objCDAO.getItems();
		request.setAttribute("listCat", listCat);
		request.setAttribute("index", index);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/categories/index.jsp");
		rd.forward(request, response);
	}

}
