package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.Songs;
import model.dao.SearchDAO;

import java.io.IOException;
import java.util.ArrayList;

public class PublicSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public PublicSearchController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/public/ketqua.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String search = request.getParameter("editbox_search");
		SearchDAO objSearch = new SearchDAO();
		ArrayList<Songs> listS = objSearch.getItem(search);
		request.setAttribute("listS", listS);
		RequestDispatcher rd = request.getRequestDispatcher("/public/ketqua.jsp");
		rd.forward(request, response);			
	}

}
