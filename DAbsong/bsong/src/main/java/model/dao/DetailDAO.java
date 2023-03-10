package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectDBLibrary;
import model.bean.Cat;
import model.bean.Songs;

public class DetailDAO {
	private ConnectDBLibrary connectDBLibrary;
	private Statement st;
	private ResultSet rs;
	private Connection conn;
	private PreparedStatement pst;
	
	public DetailDAO() {
		connectDBLibrary = new ConnectDBLibrary();
	}
	
	public Songs getItemById(int id) {
		Songs objS = new Songs();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM songs INNER JOIN categories ON songs.cat_id=categories.id WHERE songs.id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				Cat categories = new Cat(rs.getInt("categories.id"), rs.getString("categories.name"));
				objS = new Songs(rs.getInt("songs.id"), rs.getString("songs.name"), rs.getString("songs.preview_text"), rs.getString("songs.detail_text"), rs.getString("songs.date_create"),categories, rs.getString("songs.picture"), rs.getInt("songs.counter"), rs.getInt("songs.cat_id"));
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
		return objS;
	}
	public ArrayList<Songs> getItemByIdCId(int id, int cid) {
		ArrayList<Songs> listS = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM songs INNER JOIN categories ON songs.cat_id=categories.id WHERE songs.cat_id=? AND songs.id!=? ORDER BY songs.id DESC LIMIT 2";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cid);
			pst.setInt(2, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				Cat categories = new Cat(rs.getInt("categories.id"), rs.getString("categories.name"));
				Songs objS = new Songs(rs.getInt("songs.id"), rs.getString("songs.name"), rs.getString("songs.preview_text"), rs.getString("songs.detail_text"), rs.getString("songs.date_create"),categories, rs.getString("songs.picture"), rs.getInt("songs.counter"), rs.getInt("songs.cat_id"));
				listS.add(objS);
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
		return listS;
	}
	
}
