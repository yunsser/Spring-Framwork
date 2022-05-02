package com.tjoeun.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.support.SessionStatus;

import com.tjoeun.dao.UserJdbcTemplateDAO;
import com.tjoeun.vo.User;

@Controller
@RequestMapping("/jdbc")
public class UserJdbcTemplateController {
	@Autowired
	private UserJdbcTemplateDAO dao;

//	로그인

	@GetMapping("/login")
	public String form() {
		return "user/loginForm";
	}
	
	@PostMapping("/login")
	@ResponseBody
	public Map<String, Boolean> login(User user, Model model) {
//		System.out.println("아이디화ㅓㄱ인" + user.getUid());
//		System.out.println("비번확인" + user.getPwd());
		Map<String, Boolean> map = new HashMap<>();
		boolean ok = dao.login(user);
		if (ok) {
			model.addAttribute("uid", user.getUid()); /* 세션에 저장 */
		}
		map.put("ok", ok);
		return map;
	}

	
//	로그아웃

	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:/user/login";
	}

//	사용자추가

	@GetMapping("/add")
	public String addForm() {
		return "user/addForm";
	}

	@PostMapping("/add")
	@ResponseBody
	public Map<String, Boolean> addUser(User user) {
		boolean added = dao.addUser(user);
		Map<String, Boolean> map = new HashMap<>();
		map.put("added", added);
		return map;
	}

//	목록보기

	@GetMapping("/list")
	public String list(Model model) {
		List<User> list = dao.getList();
		model.addAttribute("list", list);
		return "user/userList";
	}
	
//	상세보기

	@GetMapping("/detail")
	// @ResponseBody
	public String detail(@RequestParam("useruid") String useruid, Model model) {
//		System.out.println(useruid+"확인");
//		System.out.println(uid+","+useruid);
		User user = dao.detail(useruid);
		model.addAttribute("user", user);
		return "user/userDetail";

	}

//	수정

	@GetMapping("/edit")
	public String edit(@RequestParam String useruid, Model model) {
		User user = dao.detail(useruid);
		model.addAttribute("user", user);
		return "user/userEdit";
	}
	

//	수정저장

	@PostMapping("/update")
	@ResponseBody
	public Map<String, Boolean> update(User user) {
		boolean updated = dao.update(user);
		Map<String, Boolean> map = new HashMap<>();
		map.put("updated", updated);
		return map;
	}
	

//	삭제

	@PostMapping("/delete")
	@ResponseBody
	public Map<String, Boolean> delete(@RequestParam String useruid) {
		boolean deleted = dao.delete(useruid);
		Map<String, Boolean> map = new HashMap<>();
		map.put("deleted", deleted);
		return map;

	}
	
//	중복 확인
	
	@PostMapping("/idcheck")
	@ResponseBody
	public Map<String, Boolean> idcheck(@RequestParam String uid) {
		//System.out.println("dddddddddddd"+user);
		boolean idchecked = dao.idcheck(uid);
		Map<String, Boolean> map = new HashMap<>();
		map.put("idchecked", !idchecked);
		return map;
	}
	
	
}
