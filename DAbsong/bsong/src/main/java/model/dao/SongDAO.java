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
import model.bean.SongsJoinCat;

public class SongDAO {
	private ConnectDBLibrary connectDBLibrary;
	private Statement st;
	private ResultSet rs;
	private Connection conn;
	private PreparedStatement pst;
	
	public SongDAO() {
		connectDBLibrary = new ConnectDBLibrary();
	}
	
	public ArrayList<Songs> getItem(){
		ArrayList<Songs> listSong = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM songs INNER JOIN categories ON songs.cat_id=categories.id ORDER BY songs.id DESC LIMIT 5 ";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Cat categories = new Cat(rs.getInt("categories.id"), rs.getString("categories.name"));
				Songs objSong = new Songs(rs.getInt("songs.id"), rs.getString("songs.name"), rs.getString("songs.preview_text"), rs.getString("songs.detail_text"), rs.getString("songs.date_create"),categories, rs.getString("songs.picture"), rs.getInt("songs.counter"), rs.getInt("songs.cat_id"));
				listSong.add(objSong);
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
		return listSong;
	}
	public int getNumberPage() {
		int num = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT COUNT(*) FROM songs INNER JOIN categories ON songs.cat_id=categories.id";
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
	public int getNumberPageByCid(int cid) {
		int num = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT COUNT(*) FROM songs WHERE cat_id=?";
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
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
	public ArrayList<Songs> getPaging(int index){
		ArrayList<Songs> listSong = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM songs INNER JOIN categories ON songs.cat_id=categories.id ORDER BY songs.id DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, ((index-1)*DefineLb.NUMBER_PER_PAGE));
			pst.setInt(2, DefineLb.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {
				Cat categories = new Cat(rs.getInt("categories.id"), rs.getString("categories.name"));
				Songs objSong = new Songs(rs.getInt("songs.id"), rs.getString("songs.name"), rs.getString("songs.preview_text"), rs.getString("songs.detail_text"), rs.getString("songs.date_create"),categories, rs.getString("songs.picture"), rs.getInt("songs.counter"), rs.getInt("songs.cat_id"));
				listSong.add(objSong);
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
		return listSong;
	}
	public ArrayList<Songs> getItemById(int id, int index){
		ArrayList<Songs> listSong = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM songs INNER JOIN categories ON songs.cat_id=categories.id WHERE songs.cat_id=? ORDER BY songs.id DESC LIMIT ?,?  ";
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setInt(2, ((index-1)*DefineLb.NUMBER_PER_PAGE));
			pst.setInt(3, DefineLb.NUMBER_PER_PAGE);
			rs=pst.executeQuery();
			while(rs.next()) {
				Cat categories = new Cat(rs.getInt("categories.id"), rs.getString("categories.name"));
				Songs objS = new Songs(rs.getInt("songs.id"), rs.getString("songs.name"), rs.getString("songs.preview_text"), rs.getString("songs.detail_text"), rs.getString("songs.date_create"),categories, rs.getString("songs.picture"), rs.getInt("songs.counter"), rs.getInt("songs.cat_id"));
				listSong.add(objS);
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
		return listSong;
	}
	public int getNumberPageCat(int id) {
		int num = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT COUNT(*) FROM songs WHERE cat_id=? ";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
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

	public ArrayList<SongsJoinCat> getPaging2(int index) {
		ArrayList<SongsJoinCat> listSong = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT songs.id, songs.name, categories.name, counter, picture, categories.id FROM songs INNER JOIN categories ON songs.cat_id=categories.id ORDER BY songs.id DESC LIMIT ?,?;";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, ((index-1)*DefineLb.NUMBER_PER_PAGE));
			pst.setInt(2, DefineLb.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			System.out.println();
			while(rs.next()) {
				Cat categories = new Cat(rs.getInt("categories.id"), rs.getString("categories.name"));
				SongsJoinCat objSong = new SongsJoinCat(rs.getInt("songs.id"), rs.getString("songs.name"), rs.getString("categories.name"),rs.getInt("counter"), rs.getString("picture"),rs.getInt("categories.id"),categories);
				listSong.add(objSong);
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
		return listSong;
	}
	public int addSong(Songs objS) {
		
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "INSERT INTO `songs` (`id`, `name`, `preview_text`, `detail_text`, `date_create`, `picture`, `counter`, `cat_id`) VALUES (?,?,?,?,?,?,?,?) ";
		int result=0;
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, objS.getId());
			pst.setString(2, objS.getName());
			pst.setString(3, objS.getPreview_text());
			pst.setString(4, objS.getDetail_text());
			pst.setString(5, objS.getDate_create());
			pst.setString(6, objS.getPicture());
			pst.setInt(7, objS.getCounter());
			pst.setInt(8, objS.getCat_id());
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

	public Songs getItemById(int id1) {
		Songs objS = new Songs();
		conn=connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM songs INNER JOIN categories ON songs.cat_id=categories.id WHERE songs.id = ?";
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, id1);
			rs=pst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("songs.id") ;
				String name = rs.getString("songs.name");
				String preview_text =  rs.getString("songs.preview_text");
				String detail_text = rs.getString("songs.detail_text") ;
				String date_create = rs.getString("songs.date_create");
				Cat category = new Cat(rs.getInt("categories.id"), rs.getString("categories.name")) ;
				String picture = rs.getString("songs.picture");
				int counter = rs.getInt("songs.counter");
				int cat_id = rs.getInt("songs.cat_id");
				objS = new Songs(id, name, preview_text, detail_text, date_create, category, picture, counter, cat_id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return objS;
	}
	public SongsJoinCat getItemByIdJoin(int id) {
		SongsJoinCat objS = new SongsJoinCat();
		conn=connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM songs INNER JOIN categories ON songs.cat_id=categories.id WHERE songs.id = ?";
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs=pst.executeQuery();
			while(rs.next()) {
				Cat categories = new Cat(rs.getInt("categories.id"), rs.getString("categories.name"));
				objS = new SongsJoinCat(rs.getInt("songs.id"), rs.getString("songs.name"), rs.getString("categories.name"),rs.getInt("counter"), rs.getString("picture"),rs.getInt("categories.id"), categories);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return objS;
	}

	public int editSong(Songs objS, int id) {	
		conn=connectDBLibrary.getConnectMySQL();
		String sql  = "UPDATE `songs` SET `name`=?,`preview_text`=?,`detail_text`=?,`picture`=?,`cat_id`=? WHERE id=? ";
		int result = 0;
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, objS.getName());
			pst.setString(2, objS.getPreview_text());
			pst.setString(3, objS.getDetail_text());
			pst.setString(4, objS.getPicture());
			pst.setInt(5, objS.getCat_id());
			pst.setInt(6, id);
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

	public int delItemById(int id) {
		int result=0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "DELETE FROM `songs` WHERE id=?";
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
	public int getNumSong() {
		int num = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT COUNT(*) FROM songs ";
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
	public void delItemByCat(int id) {
		int result=0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "DELETE FROM `songs` WHERE cat_id=?";
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
	}
	public ArrayList<Songs> getListById(int id){
		ArrayList<Songs> listSong = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM songs INNER JOIN categories ON songs.cat_id=categories.id WHERE songs.id!=? ";
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs=pst.executeQuery();
			while(rs.next()) {
				Cat categories = new Cat(rs.getInt("categories.id"), rs.getString("categories.name"));
				Songs objS = new Songs(rs.getInt("songs.id"), rs.getString("songs.name"), rs.getString("songs.preview_text"), rs.getString("songs.detail_text"), rs.getString("songs.date_create"),categories, rs.getString("songs.picture"), rs.getInt("songs.counter"), rs.getInt("songs.cat_id"));
				listSong.add(objS);
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
		return listSong;
	}
	public ArrayList<SongsJoinCat> getLS() {
		ArrayList<SongsJoinCat> listSong = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT songs.id, songs.name, categories.name, counter, picture, categories.id FROM songs INNER JOIN categories ON songs.cat_id=categories.id ";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Cat category = new Cat(rs.getInt("categories.id"), rs.getString("categories.name"));
				SongsJoinCat objSong = new SongsJoinCat(rs.getInt("songs.id"), rs.getString("songs.name"), rs.getString("categories.name"),rs.getInt("counter"), rs.getString("picture"),rs.getInt("categories.id"),category);
				listSong.add(objSong);
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
		return listSong;
	}
}
