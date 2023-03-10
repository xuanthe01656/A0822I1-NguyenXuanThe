package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library.AuthUtil;
import model.dao.SongDAO;

import java.io.IOException;

public class AdminDeleteSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdminDeleteSongController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/Login");
			return;
		}
		int id = Integer.parseInt(request.getParameter("id"));
		SongDAO objSDAO = new SongDAO();
		int result = objSDAO.delItemById(id);
		if(result>0) {
			response.sendRedirect(request.getContextPath()+"/admin/song?msg=3");
		}else {
			response.sendRedirect(request.getContextPath()+"/admin/song?msg=4");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
