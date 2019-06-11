package com.ssafy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.vo.Member;

public class MemberDao {
	private Connection conn;

	public MemberDao() {
		conn = FoodDaoImpl.getConn();
	}

	public void add(Member m) {
		String sql = "INSERT INTO member VALUES(?,?,?,?,?,?)";
		String sql2 = "INSERT INTO memalg VALUES(?,?)";
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		try { 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getPw());
			pstmt.setString(3, m.getName());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getPhone());
			pstmt.setString(6, m.getEmail());
			pstmt.executeUpdate(); // 삽입
			// 알러지 삽입 쿼리
			pstmt2 = conn.prepareStatement(sql2);
			String[] alg = m.getAllergys();
			System.out.println(Arrays.toString(alg));
			for (int i = 0; i < alg.length; i++) {
				pstmt2.setString(1, alg[i]);
				pstmt2.setString(2, m.getId());
				pstmt2.executeUpdate(); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // 사용한 자원을 반환, finally 실행 후 리턴
			close(pstmt);
			close(pstmt2);
		}
	}

	public boolean delete(String id) {
		String sql = "DELETE FROM member WHERE id=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			int result = pstmt.executeUpdate(); // 삭제
			if(result > 0) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { 
			close(pstmt);
		}
		return false;
	}

	public boolean check(String id, String pass) {
		String sql = "SELECT pw FROM member WHERE id=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery(); // 삭제
			if(rs.next()) {
				if(pass.equals(rs.getString(1))) return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { 
			close(pstmt);
		}
		return false;
	}

	public Member search(String id) {
		Member m = new Member();
		String sql = "SELECT * FROM member LEFT JOIN memalg ON member.id = memalg.id WHERE member.id=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.last();
			int size = rs.getRow();
			String[] allergys = new String[size];
			rs.beforeFirst();
			while (rs.next()) {
				if(rs.getRow() == 1) {
					m.setId(rs.getString(1));
					m.setPw(rs.getString(2));
					m.setName(rs.getString(3));
					m.setAddress(rs.getString(4));
					m.setPhone(rs.getString(5));
					m.setEmail(rs.getString(6));
				}
				//알러지 조회
				if(rs.getString(7) != null) {
					allergys[rs.getRow()-1] = rs.getString(7);
				} else {
					allergys[rs.getRow()-1] = "알러지 정보 없음";
				}
			}
			m.setAllergys(allergys);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // 사용한 자원을 반환, finally 실행 후 리턴
			close(rs);
			close(pstmt);
		}
		return m;
	}

	public Member update(String id, String pw, String address, String phone, String email,
			String[] al) {
		Member m = new Member();
		String sql = "UPDATE member SET pw=?, address=?, phone=?, email=? WHERE id=?";
		String sql2 = "DELETE FROM memalg WHERE id=?";
		String sql3 = "INSERT INTO memalg VALUES(?,?)";
		
		// 알러지 업데이트
		System.out.println(address);
		System.out.println(phone);
		System.out.println(email);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		try {
			pstmt = conn.prepareStatement(sql);			
			pstmt.setString(1, pw);
			pstmt.setString(2, address);
			pstmt.setString(3, phone);
			pstmt.setString(4, email);
			pstmt.setString(5, id);	
			pstmt.executeUpdate();		
			pstmt.setString(1, pw);
			pstmt.setString(2, address);
			pstmt.setString(3, phone);
			pstmt.setString(4, email);
			pstmt.setString(5, id);	
			pstmt.executeUpdate();
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, id);
			pstmt2.executeUpdate();
			pstmt3 = conn.prepareStatement(sql3);
			for (int i = 0; al != null && i < al.length; i++) {
				pstmt3.setString(1, al[i]);
				pstmt3.setString(2, id);
				pstmt3.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // 사용한 자원을 반환, finally 실행 후 리턴
			close(rs);
			close(pstmt3);
			close(pstmt2);
			close(pstmt);
		}
		return search(id);
	}

	// close
	private void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void close(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public String[] check(int code, String id) {
		String sql = "SELECT foodalg.allergy FROM foodalg INNER JOIN memalg "
				+ "ON foodalg.allergy = memalg.allergy "
				+ "WHERE code=? AND id=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String[] allergys = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, code);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			rs.last();
			if(rs.getRow() > 0) {
				allergys = new String[rs.getRow()];
				rs.beforeFirst();
				while(rs.next()) {
					allergys[rs.getRow()-1] = rs.getString(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // 사용한 자원을 반환, finally 실행 후 리턴
			close(rs);
			close(pstmt);
		}
		return allergys;
	}
	
	public void intake(int code, String id) {
		String sql = "INSERT INTO intake VALUES(?, ?, 1) ON DUPLICATE KEY UPDATE count = count+1";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, code);
			pstmt.setString(2, id);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // 사용한 자원을 반환, finally 실행 후 리턴
			close(pstmt);
		}
	}

	public String passFind(String  id, String name) {
		String sql = "SELECT pw FROM member where id = ? and name = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // 사용한 자원을 반환, finally 실행 후 리턴
			close(pstmt);
		}
		
		return null;
	}
}
