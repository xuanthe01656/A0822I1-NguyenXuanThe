package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library.AuthUtil;
import library.DefineLb;
import model.bean.SongsJoinCat;
import model.dao.SongDAO;

import java.io.IOException;
import java.util.ArrayList;

public class AdminSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdminSongController() {
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
		int index = 0;
		if(request.getParameter("index")==null) {
			index=1;
		}else {
			index= Integer.parseInt(request.getParameter("index"));
		}
		int dfNum = DefineLb.NUMBER_PER_PAGE;
		SongDAO objS = new SongDAO();
		ArrayList<SongsJoinCat> listSong = objS.getPaging2(index);
		request.setAttribute("listSong", listSong);
		request.setAttribute("index", index);
		request.setAttribute("Dfnum", dfNum);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/song/index.jsp");
		rd.forward(request, response);	
	}

}
