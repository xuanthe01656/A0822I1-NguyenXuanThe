package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library.AuthUtil;
import model.bean.SongsJoinCat;
import model.dao.SearchDAO;

import java.io.IOException;
import java.util.ArrayList;

public class AdminSearchSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminSearchSongController() {
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
		String key = request.getParameter("key");
		SearchDAO objSearch = new SearchDAO();
		ArrayList<SongsJoinCat> listS = objSearch.getItemByKey(key);
		request.setAttribute("listS", listS);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/song/resultSearch.jsp");
		rd.forward(request, response);
	}

}
