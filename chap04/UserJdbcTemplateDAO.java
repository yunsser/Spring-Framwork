package com.tjoeun.dao;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tjoeun.vo.User;

@Repository
public class UserJdbcTemplateDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public boolean login(User puser) {

		String sql = "SELECT * FROM users WHERE uid=? and pwd=?";
		
		User u = jdbcTemplate.queryForObject(sql,
				new Object[] { puser.getUid(), puser.getPwd() },
				new int[] { Types.VARCHAR, Types.VARCHAR },
				(rs, i) -> {
					User user = new User();
					user.setUid(rs.getString("UID"));
					user.setPwd(rs.getString("PWD"));
					user.setName(rs.getString("NAME"));
					user.setPhone(rs.getString("PHONE"));
					return user;
				});
		if(u!=null) {
			return true;
		}else {
			return false;
		}
		
	}

	public List<User> getList() {

		String sql = "SELECT * FROM users";
		
		return jdbcTemplate.query(sql, 
				(rs, i) -> {
					User u = new User();
					u.setUid(rs.getString("UID"));
					u.setPwd(rs.getString("PWD"));
					u.setName(rs.getString("NAME"));
					u.setPhone(rs.getString("PHONE"));
					return u;
				});
	}

	public boolean addUser(User user) {

		String sql = "INSERT INTO users VALUES (?,?,?,?)";
		
		int rows = jdbcTemplate.update(sql,
				new Object[] { user.getUid(), user.getPwd(), user.getName(), user.getPhone() },
				new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR });
		return rows > 0;
	}

	public User detail(String uid) {
		
		String sql = "SELECT * FROM users WHERE uid=?";
		
		return jdbcTemplate.queryForObject(sql, 
			new Object[] { uid },
			new int[] { Types.VARCHAR },
			
			(rs, i) -> {
			User u = new User();
			u.setUid(rs.getString("UID"));
			u.setPwd(rs.getString("PWD"));
			u.setName(rs.getString("NAME"));
			u.setPhone(rs.getString("PHONE"));
			return u;
		});
	}

	public boolean update(User user) {
		
		String sql = "UPDATE users SET phone=? WHERE uid=?";
		
		int rows = jdbcTemplate.update(sql,
				new Object[] { user.getPhone(), user.getUid() },
				new int[] { Types.VARCHAR, Types.VARCHAR });
		return rows > 0;
	}

	public boolean delete(String useruid) {
		
		String sql = "DELETE FROM users WHERE uid=?";
		
		int rows = jdbcTemplate.update(sql,
				new Object[] { useruid },
				new int[] { Types.VARCHAR });
		return rows > 0;
	}
	
	public boolean idcheck(String uid) {
		
		String sql = "SELECT * FROM users WHERE uid=?";
		
		try{
			jdbcTemplate.queryForObject(sql,
			new Object[]{uid},
			new int[]{Types.VARCHAR},
			(rs, i)-> new User());
			return true;
			}catch(EmptyResultDataAccessException erErr) {
				return false;
			}
	}
	

}
