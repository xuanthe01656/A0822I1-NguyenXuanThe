package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectDBLibrary;
import library.DefineLb;
import model.bean.Contact;
import model.bean.Songs;

public class ContactDAO {
	private ConnectDBLibrary connectDBLibrary;
	private Statement st;
	private ResultSet rs;
	private Connection conn;
	private PreparedStatement pst;
	
	public ContactDAO() {
		connectDBLibrary = new ConnectDBLibrary();
	}
	
	public int addItem(Contact objCt) {
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "INSERT INTO `contacts`(`id`, `name`, `email`, `website`, `message`) VALUES (?,?,?,?,?)";
		int result = 0;
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, objCt.getId());
			pst.setString(2, objCt.getName());
			pst.setString(3, objCt.getEmail());
			pst.setString(4, objCt.getWebsite());
			pst.setString(5, objCt.getMessage());
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
		String sql = "SELECT COUNT(*) FROM contacts ";
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
	public ArrayList<Contact> getPaging(int index){
		ArrayList<Contact> listCt = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM contacts LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, ((index-1)*DefineLb.NUMBER_PER_PAGE));
			pst.setInt(2, DefineLb.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {
				Contact objCt = new Contact(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("website"), rs.getString("message"));
				listCt.add(objCt);
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
		return listCt;
	}

	public int deleteContact(int id) {
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "DELETE FROM `contacts` WHERE id=?";
		int result=0;
		try {
			pst=conn.prepareStatement(sql);
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
	public int getNumContact() {
		int num = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT COUNT(*) FROM contacts ";
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
}
