package com.tjoeun.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.tjoeun.vo.User;

@Repository
public class UserJdbcDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private Connection getConn() {
		try {
			String url = "jdbc:mysql://localhost:3306/mydb?characterEncoding=UTF-8&serverTimezone=UTC&SSL=false";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, "root", "tjoeun");
			return conn;
		} catch (Exception ex) {
			System.err.println("DB 접속 실패!");
			ex.printStackTrace();
		}
		return null;
	}

	public boolean login(User user) {
		conn = getConn();
		String sql = "SELECT * FROM users WHERE uid=? and pwd=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUid());
			pstmt.setString(2, user.getPwd());
			rs = pstmt.executeQuery();
			if (rs.next())
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return false;
	}

	public List<User> getList() {
		conn = getConn();
		try {
			String sql = "SELECT * FROM users";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			List<User> list = new ArrayList<>();

			while (rs.next()) {
				User us = new User();

				us.setUid(rs.getString("UID"));
				us.setPwd(rs.getString("PWD"));
				us.setName(rs.getString("NAME"));
				us.setPhone(rs.getString("PHONE"));
				list.add(us);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return null;

	}

	public boolean add(User user) {
		conn = getConn();
		String sql = "INSERT INTO users VALUES (?, ?, ?, ?)";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, user.getUid());
			pstmt.setString(2, user.getPwd());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getPhone());

			int rows = pstmt.executeUpdate();
			return rows > 0 ? true : false;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return false;

	}

	public User detail(String uid) {
		conn = getConn();
		try {
			String sql = "SELECT * FROM users WHERE uid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uid);

			rs = pstmt.executeQuery();

			User us = null;

			if (rs.next()) {
				pstmt = conn.prepareStatement(sql);

				us = new User();

				us.setUid(rs.getString("UID"));
				us.setPwd(rs.getString("PWD"));
				us.setName(rs.getString("NAME"));
				us.setPhone(rs.getString("PHONE"));
			}
			closeAll();
			return us;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean update(User user) {
		conn = getConn();
		String sql = "UPDATE users SET phone=? WHERE uid=?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, user.getPhone());
			pstmt.setString(2, user.getUid());

			int rows = pstmt.executeUpdate();
			closeAll();
			return rows > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(User user) {
		this.conn = getConn();
		try {
			String sql = "DELETE FROM users WHERE uid=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, user.getUid());

			int rows = pstmt.executeUpdate();
			return rows > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return false;
	}

	private void closeAll() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}