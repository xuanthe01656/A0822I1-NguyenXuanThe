package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.Contact;
import model.dao.ContactDAO;

import java.io.IOException;

public class PublicContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PublicContactController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/public/contact.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String website = request.getParameter("website");
		String message = request.getParameter("message");
		Contact objCt = new Contact(0, name, email, website, message);
		ContactDAO objCtDAO = new ContactDAO();
		int result = objCtDAO.addItem(objCt);
		if(result>0) {
			response.sendRedirect(request.getContextPath()+"/public/contact?msg=1");
		}else {
			response.sendRedirect(request.getContextPath()+"/public/contact?msg=2");
		}
	}


}
