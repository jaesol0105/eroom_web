package kpu.web.club.persistence;

import java.sql.*;

import javax.naming.*;
import javax.sql.*;

import kpu.web.club.domain.EroomVO;

public class EroomDAO {
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
	
	// 해당 아이디로 이미 예약된 좌석이 있는지 조회
	public int read(String id) {
		connect();
		String sql = "select * from eroom_seat where id = ?";
		EroomVO vo = new EroomVO();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			vo.setSeat_no(rs.getString("seat_no"));
			rs.close();
		} catch (SQLException e) {
			return 0;
		} finally {
			disconnect();
		}
		// 아이디로 예약한 좌석이 없을경우 0 반환
		if (vo.getSeat_no() == null)
			return 0;
		else
			return Integer.parseInt(vo.getSeat_no());
	}
	
	// 좌석 예약 기능
	public boolean reserve(EroomVO vo) {
		connect();
		String sql = "update eroom_seat set state = ?, id = ? where seat_no = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getState());
			pstmt.setString(2, vo.getId());
			pstmt.setString(3, vo.getSeat_no());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}
	
	// 예약 취소 기능
	public boolean cancel(String id) {
		connect();
		String sql = "update eroom_seat set state = ?, id = ? where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "0");
			pstmt.setString(2, null);
			pstmt.setString(3, id);
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
