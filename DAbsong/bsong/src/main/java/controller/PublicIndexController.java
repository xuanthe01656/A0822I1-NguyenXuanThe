package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.Songs;
import model.dao.SongDAO;

import java.io.IOException;
import java.util.ArrayList;

public class PublicIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PublicIndexController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println(request.getServletContext().getRealPath(""));
		int index = 0;
		if(request.getParameter("index")==null) {
			index=1;
		}else {
			index= Integer.parseInt(request.getParameter("index"));
		}
		SongDAO objS = new SongDAO();
		ArrayList<Songs> listSong = objS.getPaging(index);
		request.setAttribute("listSong", listSong);
		request.setAttribute("index", index);
		RequestDispatcher rd = request.getRequestDispatcher("/public/index.jsp");
		rd.forward(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}	

}
