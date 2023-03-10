package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library.AuthUtil;
import model.bean.Contact;
import model.dao.SearchDAO;

import java.io.IOException;
import java.util.ArrayList;

public class AdminSearchContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminSearchContactController() {
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
		String key = request.getParameter("key");
		SearchDAO objSrDAO = new SearchDAO();
		ArrayList<Contact> listCt = objSrDAO.searchContact(key);
		request.setAttribute("listCt", listCt);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/contact/resultSearch.jsp");
		rd.forward(request, response);
	}

}
