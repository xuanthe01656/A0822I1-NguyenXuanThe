package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library.AuthUtil;
import model.dao.ContactDAO;

import java.io.IOException;

public class AdminDeleteContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminDeleteContactController() {
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
		int id =Integer.parseInt(request.getParameter("id"));
		ContactDAO objCtDAO = new ContactDAO();
		int result = objCtDAO.deleteContact(id);
		if(result>0) {
			response.sendRedirect(request.getContextPath()+"/admin/contact?msg=1");
		}else {
			response.sendRedirect(request.getContextPath()+"/admin/contact?msg=2");
		}
	}

}
