package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import library.AuthUtil;
import model.bean.Cat;
import model.dao.CatDAO;

import java.io.IOException;
import java.util.ArrayList;

@MultipartConfig
public class AdminEditCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminEditCatController() {
        super();
    }
    public boolean equalsNameCat(ArrayList<Cat> listCat, String nameCat) {
    	for (Cat cat : listCat) {
			if(cat.getName().equals(nameCat)==true) {
				return false;
			}
		}
    	return true;
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/Login");
			return;
		}
		HttpSession session = request.getSession();
		int id = Integer.parseInt(request.getParameter("id"));
		session.setAttribute("id", id);
		CatDAO objCDAO = new CatDAO();
		Cat objC = objCDAO.getItemsById(id);
		request.setAttribute("objC", objC);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/categories/edit.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/Login");
			return;
		}
		HttpSession session = request.getSession();
		AdminEditCatController obj = new AdminEditCatController();
		int id = (Integer)session.getAttribute("id");
		String name_categeries = request.getParameter("categories");
		CatDAO objCDAO = new CatDAO();
		ArrayList<Cat> listCatById = objCDAO.getListById(id);
		if(obj.equalsNameCat(listCatById, name_categeries)) {
			int result = objCDAO.editItems(id, name_categeries);
			if(result>0) {
				response.sendRedirect(request.getContextPath()+"/admin/song?msg=3");
			}else {
				response.sendRedirect(request.getContextPath()+"/admin/song?msg=4");
			}
		}else {
			response.sendRedirect(request.getContextPath()+"/admin/song?msg=7");
		}
	}

}
