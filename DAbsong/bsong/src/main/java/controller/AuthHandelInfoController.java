package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.bean.User;
import model.dao.UserDAO;

import java.io.IOException;
import java.util.ArrayList;

public class AuthHandelInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
       

    public AuthHandelInfoController() {
        super();
        userDAO = new UserDAO();
       
    }
    public boolean equalsUser(ArrayList<User> list, String username, String email) {
		for (User user : list) {
			if(user.getUsername().contains(username)) {
				return true;
			}
		}
	return false;
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/auth/inforeset.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User objUs = new User();
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		User user = userDAO.exisUserRs(username, email);
		if(user.getUsername()!=null||user.getEmail()!=null) {
			if(user.getUsername().equals(username)&&user.getEmail().equals(email)) {
				session.setAttribute("objU", objUs);
				response.sendRedirect(request.getContextPath()+"/verification");
			}else {
				if(user.getUsername().equals(username)==false&&user.getEmail().equals(email)) {
					response.sendRedirect(request.getContextPath()+"/handelinfo?msg=2");
				}else {
					if(user.getUsername().equals(username)&&user.getEmail().equals(email)==false) {
						response.sendRedirect(request.getContextPath()+"/handelinfo?msg=1");
					}else {
						response.sendRedirect(request.getContextPath()+"/handelinfo?msg=2");
					}	
				}
			}
		}else {
			response.sendRedirect(request.getContextPath()+"/handelinfo?msg=2");
		}
		
	}

}
