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
import java.util.ArrayList;

public class AuthResetPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;

    public AuthResetPasswordController() {
        super();
        userDAO = new UserDAO();
    }
    public boolean equalsUser(ArrayList<User> list, String username) {
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
//		HttpSession session = request.getSession();
//		ArrayList<User> listUser = new ArrayList<>();
//		AuthLoginController obj = new AuthLoginController();
//		User obj1 = new User();
//		String username = request.getParameter("username");
//		String email = request.getParameter("email");
//		ArrayList<User> lUser = userDAO.exisUserRs(username, email);
//		User userLogin = (User)session.getAttribute("objU");
//		if(userLogin!=null) {
//			response.sendRedirect(request.getContextPath()+"/admin/index");
//			return;
//		}
//		if(lUser.size()>0) {
//			for (int i = 0; i < lUser.size(); i++) {
//				if(lUser.get(i).getUsername().equals(username)&&lUser.get(i).getPassword().equals(email)) {
//					obj1 = lUser.get(i);				
//				}else {
//					listUser.add(lUser.get(i));
//				}
//			}	
//		}else {
//			response.sendRedirect(request.getContextPath()+"/resetpassword?msg=2");
//			return;
//		}
//		if(obj1.getUsername()!=null&&obj1.getEmail()!=null) {
//			session.setAttribute("objU", obj1);
//			response.sendRedirect(request.getContextPath()+"/admin/index");
//		}else {
//			if(obj.equalsUser(listUser, username)) {
//				response.sendRedirect(request.getContextPath()+"/resetpassword?msg=1");
//			}else {
//				response.sendRedirect(request.getContextPath()+"/resetpassword?msg=2");
//			}
//		}
	}

}
