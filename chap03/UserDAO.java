package com.tjoeun.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.tjoeun.vo.User;

@Repository
public class UserDAO {

	private static final String fname = "C:/test/user.txt";

	public boolean login(User user) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fname));
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] token = line.split("\\|");
				if (token[0].equals(user.getUid()) && token[1].equals(user.getPwd())) {
					br.close();
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;

	}

	public List<User> getList() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fname));
			List<User> list = new ArrayList<>();
			User user = null;
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] token = line.split("\\|");
				user = new User();
				user.setUid(token[0]);
				user.setPwd(token[1]);
				user.setName(token[2]);
				user.setPhone(token[3]);
				list.add(user);
			}
			br.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean add(User user) {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(fname, true));// 트루를 넣는 이유는 이어쓰겠다는 의미, 이어쓰기모드
			pw.println(user.getLine());
			pw.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public User detail(String uid) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fname));
			User user = null;
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] token = line.split("\\|");
				if (token[0].equals(uid)) {
					user = new User();
					user.setUid(token[0]);
					user.setPwd(token[1]);
					user.setName(token[2]);
					user.setPhone(token[3]);
					br.close();
					return user;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean update(User user) 
	{
		List<User> list = getList();
		int idx = list.indexOf(user); // 동일성 검사, 객체비교를 위해 equals(), hashcode() 오버라이
		if (idx>=0) {
			list.get(idx).setPhone(user.getPhone());
			// 리스트의 내용을 기존 파일에 덮어쓴다
			return overwrite(list);
		}
		return false;

	}

	
	private boolean overwrite(List<User> list) 
	{
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(fname));
			for(int i=0; i<list.size(); i++) {
				pw.println(list.get(i).getLine());
			}
			pw.close();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public boolean delete(User user) 
	{
		List <User> list = getList();
		
		boolean deleted = list.remove(user);
		
		if(deleted) return overwrite(list);
		else return false;
	}
	
	
	
	
	
	
	
	
}
