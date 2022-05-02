package com.tjoeun.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tjoeun.mapper.UserMapper;
import com.tjoeun.vo.User;

@Repository
public class UserMybatisDAO {
	@Autowired
	private UserMapper userMapper;

// 아이디 찾기, 중복확인

	public User selectById(String uid) {
		return userMapper.getUserById(uid);
	}

// 목록 불러오기
	public List<User> getUserList() {
		return userMapper.getUserList();
	}

//	페이지 나눠주기
	public PageInfo<User> getListPage(int pageNum) {
		PageHelper.startPage(pageNum, 3); // 2 페이지, 한 화면에 3개씩
		PageInfo<User> pageInfo = new PageInfo<>(userMapper.getUserList());
		//List<User> list = pageInfo.getList();
		return pageInfo;
	}

	public List<Map<String, String>> getUserMap() {
		return userMapper.getUserMap();
	}

//	addUser (사용자 추가)
	public boolean insert(User u) {
		if (userMapper.insertUser(u) > 0) {
			return true;
		} else {
			return false;
		}

	}
//	맵맵

	public int addAndGetKey(User u) {
		return userMapper.addAndGetKey(u);
	}

//   업데이트 (수정저장)
	public boolean update(User u) {
		if (userMapper.updateUser(u) > 0) {
			return true;
		} else {
			return false;
		}
	}

//	맵맵
	public boolean updateByMap(Map<String, String> map) {
		return userMapper.updateByMap(map) > 0;
	}

//   삭제
	public boolean delete(String useruid) {
		/*
		 * if(userMapper.deleteUser(useruid) > 0) { return true; }else { return false; }
		 */
		return userMapper.deleteUser(useruid) > 0;

	}

//	중복확인
	public boolean login(User user) {
		return userMapper.login(user) != null;

	}

}