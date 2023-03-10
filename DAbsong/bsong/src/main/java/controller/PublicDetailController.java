package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.Cat;
import model.bean.Songs;
import model.dao.CatDAO;
import model.dao.CountNumberDAO;
import model.dao.DetailDAO;

import java.io.IOException;
import java.util.ArrayList;

public class PublicDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PublicDetailController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		int id=Integer.parseInt(request.getParameter("id"));
		int cid=Integer.parseInt(request.getParameter("cid"));
		
		DetailDAO objDDAO = new DetailDAO();
		CatDAO objCDAO = new CatDAO();
		Cat objC = objCDAO.getItemsById(id);
		CountNumberDAO objCND = new CountNumberDAO();
		objCND.numViews(id);
		Songs objS = objDDAO.getItemById(id);
		ArrayList<Songs> listSong = objDDAO.getItemByIdCId(id, cid);
		request.setAttribute("objS", objS);
		request.setAttribute("objC", objC);
		request.setAttribute("listSong", listSong);
		RequestDispatcher rd = request.getRequestDispatcher("/public/detail.jsp");
		rd.forward(request, response);
	}

}
