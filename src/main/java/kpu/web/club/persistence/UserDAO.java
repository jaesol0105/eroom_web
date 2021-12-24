package kpu.web.club.persistence;

import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.*;

import kpu.web.club.domain.UserVO;

public class UserDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;

	void connect() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/mysql");
			// 커넥션 얻기
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void disconnect() {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 유저 회원 가입
	public boolean add(UserVO vo) {
		connect();
		String sql = "insert into eroom_user values (?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPasswd());
			pstmt.setString(3, vo.getUsername());
			pstmt.setString(4, vo.getMobile());
			pstmt.setString(5, vo.getEmail());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}
	
	// 유저 1명 정보 조회
	public UserVO read(String id) {
		connect();
		String sql = "select * from eroom_user where id = ?";
		UserVO vo = new UserVO();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			vo.setId(rs.getString("id"));
			vo.setPasswd(rs.getString("passwd"));
			vo.setUsername(rs.getString("username"));
			vo.setMobile(rs.getString("mobile"));
			vo.setEmail(rs.getString("email"));
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return vo;
	}
	
	// 유저 정보 수정
	public boolean update(UserVO vo) {
		connect();
		String sql = "update eroom_user set passwd = ?, username = ?, mobile = ?, email = ? where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPasswd());
			pstmt.setString(2, vo.getUsername());
			pstmt.setString(3, vo.getMobile());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}
	
	// 유저 회원 탈퇴
	public boolean delete(UserVO vo) {
		connect();
		String sql = "delete from eroom_user where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}
}
