package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import library.AuthUtil;
import model.bean.User;
import model.dao.UserDAO;

import java.io.IOException;

public class AdminAddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public AdminAddUserController() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/Login");
			return;
		}
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("objU");
		if(!"admin".equals(user.getUsername())) {
			response.sendRedirect(request.getContextPath()+"/admin/user?msg=5");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/admin/user/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/Login");
			return;
		}
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("objU");
		if(!"admin".equals(user.getUsername())) {
			response.sendRedirect(request.getContextPath()+"/admin/user?msg=5");
			return;
		}
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fullname = request.getParameter("fullname");
		User objU = new User(0, username, password, fullname,"",0);
		UserDAO objUDAO = new UserDAO();
		int result =  objUDAO.addUser(objU);
		if(result>0) {
			response.sendRedirect(request.getContextPath()+"/admin/add-user?msg=1");
		}else {
			response.sendRedirect(request.getContextPath()+"/admin/add-user?msg=2");
		}
		
	}

}
