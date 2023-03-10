package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import library.AuthUtil;
import model.bean.Cat;
import model.bean.Songs;
import model.dao.CatDAO;
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

import org.apache.tomcat.jakartaee.commons.compress.utils.FileNameUtils;

@MultipartConfig
public class AdminAddSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SongDAO songDAO;
	private CatDAO catDAO;
       
    public AdminAddSongController() {
        super();
        songDAO = new SongDAO();
        catDAO = new CatDAO();
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/Login");
			return;
		}
		ArrayList<Cat> cat = catDAO.getItems();
		RequestDispatcher rd = request.getRequestDispatcher("/admin/song/add.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/Login");
			return;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		
		final String path = request.getServletContext().getRealPath("/templates/admin/assets/img");
	    File dirFileUpload = new File(path);
	    if(!dirFileUpload.exists()) {
	    	dirFileUpload.mkdir();
	    }	    
	    final Part filePart = request.getPart("picture");
	    final String fileName = getFileName(filePart);
	    
	    if(fileName!="") {
	    	String name = request.getParameter("name");
			int cid = Integer.parseInt(request.getParameter("category"));
			//System.out.println(cid);
			String preview = request.getParameter("preview");
			String detail = request.getParameter("detail");
			String date_create = sdf.format(date);
			String picture ="";
	    	final String[] arrFile = fileName.split("\\.");
	    	String duoiFile = arrFile[arrFile.length-1];
	    	AdminAddSongController obj = new AdminAddSongController();
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
			SongDAO objSDAO = new SongDAO();
			int result = objSDAO.addSong(objS);
			if(result>0) {
				response.sendRedirect(request.getContextPath()+"/admin/add-song?msg=1");
			}else {
				ArrayList<Cat> cat = catDAO.getItems();
				RequestDispatcher rd = request.getRequestDispatcher("/admin/add-song?msg=2");
				rd.forward(request, response);
				return;
				//response.sendRedirect(request.getContextPath()+"/add-song?msg=2");
			}
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
	public static String renameFile(String fileName){
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
		Date date = new Date();		
		return FileNameUtils.getBaseName(fileName)+"_"+sdf.format(date)+"."+FileNameUtils.getExtension(fileName);
	}

}
