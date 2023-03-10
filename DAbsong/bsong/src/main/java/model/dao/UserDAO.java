package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectDBLibrary;
import library.DefineLb;
import model.bean.User;

public class UserDAO {
	private ConnectDBLibrary connectDBLibrary;
	private Statement st;
	private ResultSet rs;
	private Connection conn;
	private PreparedStatement pst;
	
	public UserDAO() {
		connectDBLibrary = new ConnectDBLibrary();
	}
	
	public int getNumberPage() {
		int num = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT COUNT(*) FROM users ";
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
	public ArrayList<User> getPaging(int index){
		ArrayList<User> listU = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM users LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, ((index-1)*DefineLb.NUMBER_PER_PAGE));
			pst.setInt(2, DefineLb.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {
				User objU = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("Email"), rs.getInt("Phone"));
				listU.add(objU);
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
		return listU;
	}
	public ArrayList<User> getItem(){
		ArrayList<User> listU = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM users";
		try {
			st= conn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				User objU = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("Email"), rs.getInt("Phone"));
				listU.add(objU);
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
		return listU;
	}
	public User getItemById(int id) {
		User objU = new User();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM users WHERE id=?";
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs=pst.executeQuery();
			while(rs.next()) {
				objU = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("Email"), rs.getInt("Phone"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return objU;
	}
	public User getItemById(String username, String password) {
		User objU = new User();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM users WHERE username=? AND password=?";
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			rs=pst.executeQuery();
			while(rs.next()) {
				objU = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("Email"), rs.getInt("Phone"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return objU;
	}
	public int editUser(int id, User objU) {
		conn=connectDBLibrary.getConnectMySQL();
		String sql = "UPDATE `users` SET `username`=?,`password`=?,`fullname`=? WHERE id=?";
		int result =0;
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, objU.getUsername());
			pst.setString(2, objU.getPassword());
			pst.setString(3, objU.getFullname());
			pst.setInt(4, id);
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
		return result;
	}

	public int deleteUser(int id) {
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "DELETE FROM `users` WHERE id=?";
		int result =0;
		try {
			pst=conn.prepareStatement(sql);
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
		return result;
	}

	public int addUser(User objU) {
		conn= connectDBLibrary.getConnectMySQL();
		String sql = "INSERT INTO `users`(`username`, `password`, `fullname`, `Email`, `Phone`) VALUES (?,?,?,?,?)";
		int result = 0;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objU.getUsername());
			pst.setString(2, objU.getPassword());
			pst.setString(3, objU.getFullname());
			pst.setString(4, objU.getEmail());
			pst.setInt(5, objU.getPhone());
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
	public int getNumUser() {
		int num = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT COUNT(*) FROM users ";
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

	public ArrayList<User> exisUser(String username, String password) {
		User objU = new User();
		ArrayList<User> listUs = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM users WHERE username=? OR password=?";
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			rs=pst.executeQuery();
			while(rs.next()) {
				objU = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("Email"), rs.getInt("Phone"));
				listUs.add(objU);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listUs;
	}

	public User exisUserRs(String username, String email) {
		User objU = new User();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM users WHERE username=? OR Email=?";
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, email);
			rs=pst.executeQuery();
			while(rs.next()) {
				objU = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("Email"), rs.getInt("Phone"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return objU;
	}
}
