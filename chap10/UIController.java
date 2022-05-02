package com.tjoeun.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ui")
public class UIController {
	
	@GetMapping("/form/select")
	public String select() {
		return "layout/select";
	}
	
	@PostMapping("/form/select")
	@ResponseBody
	public Map<String,String> procSelect(@RequestParam String hobby){
		String msg = hobby+"를 선택했군요...";
		Map<String,String> map = new HashMap<>();
		map.put("msg", msg);
		return map;
	}
	
	@PostMapping("/form/arr")
	@ResponseBody
	public Map<String,String> procArr(@RequestParam(value="delfile[]") List<String> delfile){
		Map<String,String> map = new HashMap<>();
		for(int i=0; i<delfile.size(); i++) {
			map.put(""+1,delfile.get(i));
		}
		return map;
	}
	
}
