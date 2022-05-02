package com.tjoeun.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.mysql.cj.x.protobuf.MysqlxCrud.Insert;
import com.tjoeun.dao.UserMybatisDAO;
import com.tjoeun.vo.User;

@Controller
@RequestMapping("/mb")
public class UserMybatisController {
	
	@Autowired
	private UserMybatisDAO dao;
	
//	테스트용 
	/* 
	@GetMapping("/user")     http://localhost/mb/user?uid=din       
	public String getUser(@RequestParam String uid) {
		
		User user = dao.selectById(uid);
		return user.toString();
	}
	*/
	
//	로그인

	@GetMapping("/login")
	public String form() {
		return "mybatis/login";
	}
	
	@PostMapping("/login")
	@ResponseBody
	public Map<String, Boolean> login(User user) {
		Map<String, Boolean> map = new HashMap<>();
		boolean ok = dao.login(user);
		map.put("ok", ok);
		return map;
	}
	
//	리스트
	
	@GetMapping("/list")
	public String list(Model model) {
		
		List<User> list = dao.getUserList();
		model.addAttribute("list", list);
		return "mybatis/list";
	}
	
	@GetMapping("/list2")
	public String list2(Model model) {
		
		List<Map<String, String>> list = dao.getUserMap();
		model.addAttribute("list", list);
		return "mybatis/list";
	}
	
//	페이지 불러오기
	
	@GetMapping("/page/{pageNum}")
	//@ResponseBody
	public String getPage(@PathVariable int pageNum, Model model) {
		PageInfo<User> pageInfo = dao.getListPage(pageNum);
		pageInfo.getNavigatepageNums(); // pageInfo.navigatepageNums
		model.addAttribute("pageInfo", pageInfo);
		return "mybatis/page";
	}
	
	
//	사용자추가

	@GetMapping("/add")
	public String addForm() {
		return "mybatis/add";
	}

	@PostMapping("/add")
	@ResponseBody
	public Map<String, Boolean> addUser(User user) {
		boolean added = dao.insert(user);
		Map<String, Boolean> map = new HashMap<>();
		map.put("added", added);
		return map;
		
	}	
	
//	상세보기
	
	@GetMapping("/detail")
	// @ResponseBody
	public String detail(@RequestParam("useruid") String useruid, Model model) {
		User user = dao.selectById(useruid);
		model.addAttribute("user", user);
		return "mybatis/detail";

	}	
	
//	수정

	@GetMapping("/edit")
	public String edit(@RequestParam String useruid, Model model) {
		User user = dao.selectById(useruid);
		model.addAttribute("user", user);
		return "mybatis/edit";
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
	
	@PostMapping("/update2") // 어떤 vo를 써야할지 모를때 쓴다 // 여러가지의 테이블을 넣기 힘든경우
	@ResponseBody
	public Map<String, Boolean> update2(User user) {
		
		Map<String, String> pMap = new HashMap<>();
		pMap.put("uid", user.getUid());
		pMap.put("phone", user.getPhone());
		boolean updated = dao.updateByMap(pMap);
		
		Map<String, Boolean> map = new HashMap<>();
		map.put("updated", updated);
		return map;
	}
	

//	삭제

	@PostMapping("/delete")
	@ResponseBody
	public Map<String, Boolean> delete(@RequestParam String uid) {
		System.out.println(uid+"나와랏");
		boolean deleted = dao.delete(uid);
		Map<String, Boolean> map = new HashMap<>();
		map.put("deleted", deleted);
		return map;

	}
	
//	중복 확인
	
	@PostMapping("/idcheck")
	@ResponseBody
	public Map<String, Boolean> idcheck(@RequestParam String uid) {
		boolean idchecked = dao.selectById(uid) != null;
		Map<String, Boolean> map = new HashMap<>();
		map.put("idchecked", !idchecked);
		return map;
	}
	
}




/*
Map<String, Object> testMap = new HashMap<>();
testMap.put("1", 5); // 오브젝트는 뭐든지 담을수있다
testMap.put("2", true);
testMap.put("3", 3.14);
testMap.put("4", "Smith");
testMap.put("5", user);
//-----------------------
Object obj = testMap.get("2");
boolean bool = (Boolean) obj;
*/