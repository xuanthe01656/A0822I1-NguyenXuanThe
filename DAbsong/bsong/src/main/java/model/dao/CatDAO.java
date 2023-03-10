package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectDBLibrary;
import library.DefineLb;
import model.bean.Cat;
import model.bean.Songs;

public class CatDAO {
	private ConnectDBLibrary connectDBLibrary;
	private Statement st;
	private ResultSet rs;
	private Connection conn;
	private PreparedStatement pst;
	
	public CatDAO() {
		 connectDBLibrary = new ConnectDBLibrary();
	}
	
	public ArrayList<Cat> getItems(){
		ArrayList<Cat> listCat = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM categories";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Cat objCat = new Cat(rs.getInt("id"), rs.getString("name"));
				listCat.add(objCat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				st.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}

		return listCat;
	}
	public Cat getItemsById(int id){
		Cat objCat = new Cat();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM categories WHERE id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				objCat = new Cat(rs.getInt("id"), rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}	
		return objCat;
	}
	public int editItems(int id, String categories){
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "UPDATE `categories` SET `name`=? WHERE id= ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, categories);
			pst.setInt(2, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}

		return result;
	}
	public int addItems(Cat objC){
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "INSERT INTO `categories`(`name`) VALUES (?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objC.getName());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}

		return result;
	}
	public int delItems(int id){
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "DELETE FROM `categories` WHERE id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}

		return result;
	}
	public int getNumberPage() {
		int num = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT COUNT(*) FROM categories ";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				int total = rs.getInt(1);
				num = total/DefineLb.NUMBER_PER_PAGE;
				if(total%DefineLb.NUMBER_PER_PAGE!=0) {
					num++;
				}
				return num;
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
		return 0;
	}
	public ArrayList<Cat> getPaging(int index){
		ArrayList<Cat> listCat = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM categories LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, ((index-1)*DefineLb.NUMBER_PER_PAGE));
			pst.setInt(2, DefineLb.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {
				Cat objCat = new Cat(rs.getInt("id"), rs.getString("name"));
				listCat.add(objCat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return listCat;
	}
	public int getNumCat() {
		int num = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT COUNT(*) FROM categories ";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				num = rs.getInt(1);
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
		return num;
	}
	public ArrayList<Cat> getListById(int id){
		ArrayList<Cat> listCat = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM categories WHERE id!=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				Cat objCat = new Cat(rs.getInt("id"), rs.getString("name"));
				listCat.add(objCat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return listCat;
	}
}
