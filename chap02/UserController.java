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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.tjoeun.vo.User;
import com.tjoeun.svc.UserSVC;

@Controller
@SessionAttributes("uid")  /* uid 라는 파라미터변수나 ModelAtttribute 속성은 세션에 저장 (키를 사용하여 세션에 저장 선언) */
@RequestMapping("/user") // 유저라는 이름을 쓰려면 
public class UserController 
{
	@Autowired
	private UserSVC svc;
	
	@GetMapping("/login")
	public String form() 
	{
		return "user/loginForm";
	}

	@PostMapping("/login")
	@ResponseBody
	/* 
	만약 이메소드의 피라미터에 SessionAttrubute("uid")에 선언된
	uid 변수가 있다면 자동으로 세션에 저장됨
	Model에 uid를 저장하기 전에라도 uid 피라미터에 값이 전달되면 자동으로 세션에 uid가 저장됨
	*/
	public Map<String, Boolean> login(User user, Model model)
	{
		Map<String, Boolean> map = new HashMap<>();
		boolean ok = svc.login(user);
		if(ok) { 
			model.addAttribute("uid", user.getUid()); /* 세션에 저장*/
		}
		map.put("ok", ok);
		return map;
	}
	
	@GetMapping("/logout")
	public String logout(SessionStatus status)
	{
		status.setComplete();
		return "redirect:/user/login"; // response.sendRedirect("/user/iogin"); 
	}
	
	@GetMapping("/list") /* 세션에 user가 저장되어 있지 않으면 오류가 발생하므로 required=false 설정함 */
	 public String list(@SessionAttribute(name="uid", required=false) String uid,
				Model model)
	 {   
		if(uid==null) {
			return "redirect:/user/login";  // 로그인 폼으로 ...
		}else {
			List<User> list = svc.getList();
			model.addAttribute("suid", uid);
			model.addAttribute("list", list);
			return "user/userList";
		}
	 }
	
	@GetMapping("/add")
	public String write() 
	{
		return "user/addForm";
	}
	
	@PostMapping("/add")
	@ResponseBody
	public Map<String, Boolean> addUser(User user)
	{
		boolean added = svc.add(user);
		Map<String, Boolean> map = new HashMap<>();
		map.put("added", added);
		return map;
	}
	
	
	@GetMapping("/detail")
	//@ResponseBody
	public String detail(@RequestParam("useruid") String useruid, Model model) 
		{
//		System.out.println(useruid+"확인");
//		System.out.println(uid+","+useruid);
		User user = svc.detail(useruid);
		model.addAttribute("user",user);
		return "user/userDetail";

	}
	
	
	@GetMapping("/edit")
	public String edit(@RequestParam String useruid, Model model)
	{
		User user = svc.detail(useruid);
		model.addAttribute("user", user);
		return "user/userEdit";
	}
	
	
	@PostMapping("/update")
	@ResponseBody
	public Map<String, Boolean> update(User user)
	{
		boolean updated = svc.update(user);
		Map<String, Boolean> map = new HashMap<>();
		map.put("updated", updated);
		return map;
	}
	
	
	@PostMapping("/delete")
	@ResponseBody
	public Map<String, Boolean> delete(User user)
	{
		boolean deleted = svc.delete(user);
		Map<String, Boolean> map = new HashMap<>();
		map.put("deleted", deleted);
		return map;
		
		
	}
	
	
	
	
}	




	

//  http://localhost/user/login








/*
@PostMapping("")
@ResponseBody // 응답의 본체가 이거다아~
public Map<String, Boolean> login(@RequestParam("uid") String uid,@RequestParam("pwd") String pwd)
{
	Map<String, Boolean> map = new HashMap<>();
	boolean ok = false;
	if(uid.equals("smith") && pwd.equals("1111")) ok = true;
	map.put("ok", ok);
	return map;
}
*/

/*
@ModelAttribute("user")
public User user()
{
   return new User();
}

@GetMapping("")
public String form() 
{	
	return "user/loginForm";
}

@PostMapping("")
@ResponseBody // 응답의 본체가 이거다아~
public Map<String, Boolean> login(User user)
{
	Map<String, Boolean> map = new HashMap<>();
	map.put("ok", svc.login(user));
	return map;
}

 */