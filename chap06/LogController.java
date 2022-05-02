package com.tjoeun.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tjoeun.dao.LogDAO;
import com.tjoeun.vo.User;

@RestController // 테스트용이라 사용 나중에 지워주기..
//@Controller
@RequestMapping("/log")
public class LogController {

	@Autowired
	private LogDAO dao;

//	테스트
	/*
	 * @GetMapping("/test") public String test() { return "/log/test"; }
	 */

//	추가기능

	// log 테이블에 저장할 데이터 준비 num, contents
	@GetMapping("/add")
	public String add() {
		boolean added = dao.addLog("contents1");
		return "added=" + added;
	}

//	수정저장

	@PostMapping("/update") // 어떤 vo를 써야할지 모를때 쓴다 // 여러가지의 테이블을 넣기 힘든경우
	@ResponseBody
	public Map<String, Boolean> update() {

		Map<String, Object> pMap = new HashMap<>();
		pMap.put("num", 1);
		pMap.put("contents", "contents1");
		boolean updated = dao.updateLog(pMap);

		Map<String, Boolean> map = new HashMap<>();
		map.put("updated", updated);
		return map;
	}

//	목록보기
	@GetMapping("/list")
	public String list() {
		List<Map<String, Object>> list = dao.logList();
		return list.toString();
	}

//	한행보기
	@GetMapping("/get")
	public String getLog(@RequestParam int num) {
		Map<String, Object> map = dao.getLog(num);
		return map.toString();
	}

//	삭제기능
	@GetMapping("/delete")
	public String delete(@RequestParam int num) {
		boolean deleted = dao.delete(num);
		return "deleted=" + deleted;
	}

//	필드값
	@GetMapping("/add_get_ai")
	public String addAndGetAI() {
		Map<String, Object> map = new HashMap();
		map.put("contents", "AI 필드 추출 테스트2");
		boolean added = dao.add_get_ai(map);
		return "added=" + added + ", AI=" + map.get("num");
	}

}
