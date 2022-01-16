package user;

import java.sql.*;
import util.DatabaseUtil;

public class userDAO { //데이터베이스에 접근해서 데이터베이스 관련 함수들이랑 쿼리
	
	public int login(String userID, String userPassword) { //아이디와 비밀번호를 받아서 로그인을 시도해주는 함수
		String sql = "select userPassword from user where userID =?"; //user 테이블의 userID가 ?인 userPassword를 고른다.
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1; //로그인 성공
				}
				else
					return 0; //비번 틀림
			}
			return -1; //아이디 없음
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null) pstmt.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) conn.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return -2; //데이터베이스 오류
	}
	
	public int join(UserDTO user) { //사용자의 정보를 입력받아서 회원가입 진행하는 함수.
		String sql = "insert into user values(?,?,?,?,false)"; 
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserEmail());
			pstmt.setString(4, user.getUserEmailHash());
			
			return pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null) pstmt.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) conn.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return -1; //데이터베이스 오류
	}
	
	public boolean getUserEmailChecked(String userID) { //사용자의 아이디를 받아서 이메일 확인 되었는지 확인
		String sql = "select userEmailChecked from user where userID= ?"; 
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			rs =  pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getBoolean(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null) pstmt.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) conn.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false; //데이터베이스 오류
	}
	
	public boolean setUserEmailChecked(String userID) { //사용자의 아이디를 받아서 이메일인증해주는 함수. 인증되었으면 false에서 true로
		String sql = "update user set userEmailChecked=true where userID= ?"; 
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			pstmt.executeUpdate();
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null) pstmt.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) conn.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false; //데이터베이스 오류
	}
	
	public String getUserEmail(String userID) { //사용자의 아이디를 받아서 그 사용자의 이메일주소를 반환ㄴ
		String sql = "select userEmail from user where userID= ?"; 
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			rs =  pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getString(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null) pstmt.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) conn.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null; //데이터베이스 오류
	}
	
}
