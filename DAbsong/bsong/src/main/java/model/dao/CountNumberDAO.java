package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import library.ConnectDBLibrary;

public class CountNumberDAO {
	private ConnectDBLibrary connectDBLibrary;
	private Statement st;
	private ResultSet rs;
	private Connection conn;
	private PreparedStatement pst;
	
	public CountNumberDAO() {
		connectDBLibrary = new ConnectDBLibrary();
	}
	
	public void numViews(int id) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "UPDATE `songs` SET `counter`=counter+1 WHERE id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			result=pst.executeUpdate();
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
	}
}
