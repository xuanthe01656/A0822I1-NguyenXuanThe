package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.User;
import model.dao.UserDAO;

import java.io.IOException;
import java.util.ArrayList;

public class AuthRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AuthRegisterController() {
        super();
    }
    public boolean equalsUsername(ArrayList<User> listUs,String username) {
    	for (User user : listUs) {
			if(user.getUsername().equals(username)) {
				return true;
			}
		}
    	return false;
    }
    public boolean equalEmail(ArrayList<User> listUs,String email) {
    	for (User user : listUs) {
			if(user.getEmail().equals(email)) {
				return true;
			}
		}
    	return false;
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/auth/register1.jsp");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		UserDAO userDAO = new UserDAO();
		ArrayList<User> listUs = userDAO.getItem();
		AuthRegisterController obj = new AuthRegisterController();
		if(obj.equalsUsername(listUs, request.getParameter("username"))==false&&obj.equalEmail(listUs, request.getParameter("email"))==false) {
			String username= request.getParameter("username");
			String password = request.getParameter("password");
			String fullname = request.getParameter("fullname");
			String email = request.getParameter("email");
			int phone = Integer.parseInt(request.getParameter("phone"));
			User objU = new User(0, username, password, fullname,email,phone);
			UserDAO objUDAO = new UserDAO();
			int result =  objUDAO.addUser(objU);
			if(result>0) {
				response.sendRedirect(request.getContextPath()+"/register?msg=1");
				
			}else {
				response.sendRedirect(request.getContextPath()+"/register?msg=2");
			}
		}else {
			response.sendRedirect(request.getContextPath()+"/register?msg=3");
		}
		
	}

}
