package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.User;
import model.dao.UserDAO;

import java.io.IOException;
import java.util.ArrayList;

public class UserHandelController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserHandelController() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		UserDAO userDAO = new UserDAO();
		ArrayList<User> listUs = userDAO.getItem();
		AuthRegisterController obj = new AuthRegisterController();
		String ausername = request.getParameter("ausername");
		String aemail = request.getParameter("aemail");
		if(ausername!=null) {
			if(ausername.length()>=5&&ausername.length()<=32) {
				if(obj.equalsUsername(listUs, ausername)) {
					response.getWriter().print("<div><span style=\"background-color: yellow; color: red;\">Username đã tồn tại!</span></div>");
				}else {
					response.getWriter().print("<div><span style=\"background-color: green; color: white;\">Username được phép dùng!</span></div>");
				}
			}
		}
		if(aemail!=null) {
			if(aemail.length()>=11&&aemail.length()<=150) {
				if(obj.equalEmail(listUs, aemail)) {
					response.getWriter().print("<div><span style=\"background-color: yellow; color: red;\">Email đã tồn tại!</span></div>");
				}else {
					response.getWriter().print("<div><span style=\"background-color: green; color: white;\">Email được phép dùng!</span></div>");
				}
			}
		}
		
	}

}
