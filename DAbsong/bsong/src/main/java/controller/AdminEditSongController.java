package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import library.AuthUtil;
import model.bean.Cat;
import model.bean.Songs;
import model.bean.SongsJoinCat;
import model.dao.SongDAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@MultipartConfig
public class AdminEditSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdminEditSongController() {
        super();
    }
    public boolean equalsFile(String str) {
    	String[] arrFile = {"JPEG","JPG","GIF","TIFF","PSD","EPS","AI","INDD","RAW"};
    	for (String string : arrFile) {
			if(str.equalsIgnoreCase(string)) {
				return true;
			}
		}
    	return false;
    }
    public boolean equalsNameSong(ArrayList<Songs> listSByid, String nameSong) {
    	for (Songs songs : listSByid) {
			if(songs.getName().equals(nameSong)==true) {
				return false;
			}
		}
    	return true;
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/Login");
			return;
		}
		HttpSession session = request.getSession();
		int id = Integer.parseInt(request.getParameter("id"));
		SongDAO objSDAO = new SongDAO();
		Songs objS = objSDAO.getItemById(id);
		SongsJoinCat objSJ = objSDAO.getItemByIdJoin(id);
		session.setAttribute("id", id);
		request.setAttribute("objS", objS);
		request.setAttribute("objSJ", objSJ);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/song/edit.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/Login");
			return;
		}
		HttpSession session = request.getSession();
		AdminEditSongController obj = new AdminEditSongController();
		int id1 = (Integer)session.getAttribute("id");
		String name1 = request.getParameter("name");
		SongDAO objSDAO = new SongDAO();
		ArrayList<Songs> listSById = objSDAO.getListById(id1);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		final String path = request.getServletContext().getRealPath("/templates/admin/assets/img");
	    File dirFileUpload = new File(path);
	    if(!dirFileUpload.exists()) {
	    	dirFileUpload.mkdir();
	    }	    
	    final Part filePart = request.getPart("picture");
	    final String fileName = getFileName(filePart);
	    if(obj.equalsNameSong(listSById, name1)){
	    if(fileName!="") {    	
	    		int id = id1;
	    		String name = name1;
				int cid = Integer.parseInt(request.getParameter("category"));
				String preview = request.getParameter("preview");
				String detail = request.getParameter("detail");
				String date_create = sdf.format(date);
				String picture ="";
		    	final String[] arrFile = fileName.split("\\.");
		    	String duoiFile = arrFile[arrFile.length-1];
		    	if(obj.equalsFile(duoiFile)==true) {
		    		System.out.println("Đường dẫn: "+path);
		    		//final String reNameFile = renameFile(fileName);
		    		picture = fileName;
				    System.out.println("Tên file: "+fileName);
				    OutputStream out = null;
				    InputStream filecontent = null;

				    try {
				        out = new FileOutputStream(new File(path + File.separator + fileName));
				        filecontent = filePart.getInputStream();
				        int read = 0;
				        final byte[] bytes = new byte[1024];

				        while ((read = filecontent.read(bytes)) != -1) {
				            out.write(bytes, 0, read);
				        }
				       
				    } catch (FileNotFoundException fne) {
				        fne.printStackTrace();
				    } finally {
				        if (out != null) {
				            out.close();
				        }
				        if (filecontent != null) {
				            filecontent.close();
				        }
				    }
				   
		    	}
		    	Cat objC = new Cat();
		    	Songs objS = new Songs(0, name, preview, detail, date_create,objC, picture, 0, cid);
				//SongDAO objSDAO = new SongDAO();
				int result = objSDAO.editSong(objS, id);
				if(result>0) {
					response.sendRedirect(request.getContextPath()+"/admin/song?msg=1");
				}else {
					response.sendRedirect(request.getContextPath()+"/admin/song?msg=2");
				}
	    	}
	    }else {
	    	response.sendRedirect(request.getContextPath()+"/admin/song?msg=5");
	    }	
	}
	

	private String getFileName(final Part part) {
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
//	public static String renameFile(String fileName){
//		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
//		Date date = new Date();		
//		return FileNameUtils.getBaseName(fileName)+"_"+sdf.format(date)+"."+FileNameUtils.getExtension(fileName);
//	}

}
