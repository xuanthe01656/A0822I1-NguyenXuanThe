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
import model.bean.User;
import model.dao.CatDAO;

import java.io.IOException;

@MultipartConfig
public class AdminAddCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdminAddCatController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/Login");
			return;
		}
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("User");
		if(!"admin".equals(user.getUsername())) {
			response.sendRedirect(request.getContextPath()+"/admin/song?msg=8");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/admin/categories/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/Login");
			return;
		}
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("User");
		if(!"admin".equals(user.getUsername())) {
			response.sendRedirect(request.getContextPath()+"/admin/song?msg=8");
			return;
		}
		String catelogies = request.getParameter("categories");
		Cat objC = new Cat(0, catelogies);
		CatDAO objCDAO = new CatDAO();
		int result = objCDAO.addItems(objC);
		if(result>0) {
			response.sendRedirect(request.getContextPath()+"/admin/song?msg=1");
		}else {
			response.sendRedirect(request.getContextPath()+"/admin/song?msg=2");
		}	
	}

}
