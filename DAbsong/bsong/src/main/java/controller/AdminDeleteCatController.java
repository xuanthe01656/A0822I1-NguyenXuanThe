package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library.AuthUtil;
import model.dao.CatDAO;
import model.dao.SongDAO;

import java.io.IOException;

public class AdminDeleteCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminDeleteCatController() {
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
		CatDAO objCDAO = new CatDAO();
		SongDAO objSDAO = new SongDAO();
		objSDAO.delItemByCat(id); 
		int result = objCDAO.delItems(id);
		if(result>0) {
			response.sendRedirect(request.getContextPath()+"/admin/cat?msg=5");
		}else {
			response.sendRedirect(request.getContextPath()+"/admin/cat?msg=6");
		}
	}

}
