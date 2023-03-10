package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.bean.Cat;
import model.bean.Songs;
import model.dao.CatDAO;
import model.dao.SongDAO;

import java.io.IOException;
import java.util.ArrayList;

public class PublicCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PublicCatController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int id=0;
		int index=0;
		if(request.getParameter("index")==null) {
			index=1;
			id=Integer.parseInt(request.getParameter("id"));
		}else {
			index=Integer.parseInt(request.getParameter("index"));
			id = Integer.parseInt(request.getParameter("id"));
		}
		CatDAO objCDAO = new CatDAO();
		Cat objC = objCDAO.getItemsById(id);
		SongDAO objSDAO = new SongDAO();
		ArrayList<Songs> listSong = objSDAO.getItemById(id, index);
		int num = objSDAO.getNumberPageCat(id);
		request.setAttribute("numPage", num);
		request.setAttribute("index", index);
		request.setAttribute("cid", id);
		request.setAttribute("listSong", listSong);
		request.setAttribute("objCat", objC);
		RequestDispatcher rd = request.getRequestDispatcher("/public/cat.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
