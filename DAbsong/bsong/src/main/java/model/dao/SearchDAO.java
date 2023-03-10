package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectDBLibrary;
import model.bean.Cat;
import model.bean.Contact;
import model.bean.Songs;
import model.bean.SongsJoinCat;
import model.bean.User;

public class SearchDAO {
	private ConnectDBLibrary connectDBLibrary;
	private Statement st;
	private ResultSet rs;
	private Connection conn;
	private PreparedStatement pst;
	
	public SearchDAO() {
		connectDBLibrary=new ConnectDBLibrary();
	}
	
	public ArrayList<Songs> getItem(String key) {
		 ArrayList<Songs> listS = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM songs INNER JOIN categories ON songs.cat_id=categories.id WHERE songs.name LIKE "+"'%"+key+"%'";
		try {
			st=conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Cat categories = new Cat(rs.getInt("categories.id"), rs.getString("categories.name"));
				Songs objS = new Songs(rs.getInt("songs.id"), rs.getString("songs.name"), rs.getString("songs.preview_text"), rs.getString("songs.detail_text"), rs.getString("songs.date_create"),categories, rs.getString("songs.picture"), rs.getInt("songs.counter"), rs.getInt("songs.cat_id"));
				listS.add(objS);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return listS;
	}
	public ArrayList<Cat> SearchItem(String search) {
		 ArrayList<Cat> listC = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM categories WHERE name LIKE "+"'%"+search+"%'";
		try {
			st=conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Cat objS = new Cat(rs.getInt("id"), rs.getString("name"));
				listC.add(objS);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return listC;
	}
	public ArrayList<SongsJoinCat> getItemByKey(String key){
		ArrayList<SongsJoinCat> listS = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT songs.id, songs.name, categories.name, counter, picture, categories.id FROM songs INNER JOIN categories ON songs.cat_id=categories.id WHERE songs.name LIKE"+"'%"+key+"%'";
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				Cat categories = new Cat(rs.getInt("categories.id"), rs.getString("categories.name"));
				SongsJoinCat objSC = new SongsJoinCat(rs.getInt("songs.id"), rs.getString("songs.name"), rs.getString("categories.name"),rs.getInt("counter"), rs.getString("picture"), rs.getInt("categories.id"), categories);
				listS.add(objSC);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				conn.close();			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return listS;
	}

	public ArrayList<User> searchUser(String key) {
		ArrayList<User> listU = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql="SELECT * FROM users WHERE username LIKE "+"'%"+key+"%'";
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				User objU = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("Email"), rs.getInt("Phone"));
				listU.add(objU);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return listU;
	}
	public ArrayList<Contact> searchContact(String key) {
		ArrayList<Contact> listCt = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql="SELECT * FROM contacts WHERE name LIKE "+"'%"+key+"%'";
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				Contact objCt = new Contact(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("website"), rs.getString("message"));
				listCt.add(objCt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return listCt;
	}
}
