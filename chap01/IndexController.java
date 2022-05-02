package com.tjoeun.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // 브라우저로 바로안감
public class IndexController {
	@Autowired
	ResourceLoader resourceLoader;

	
	@GetMapping("/") // jsp 파일을 쓰겠다는 의미
	public String index() {
		return "index"; // 요청에 대한 응답이 브라우저에 바로 표시 // 뷰의 경로 뷰패스으!
	}

	@GetMapping("/staticpath")
	@ResponseBody
	public String getStaticPath() {
		Resource resource = (Resource) resourceLoader.getResource("upload");
		String absolutePath = null;
		try {
			absolutePath = resource.getFile().getAbsolutePath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return absolutePath;
	}

	@GetMapping("/json/test")
	@ResponseBody
	public String resString() {
		return "{\"key\":\"value\"}";
	}

	// Map을 이용한 JSON 문자열 전송하기
	@GetMapping("/map/test")
	@ResponseBody
	private Map<String, Object> mapTest() {
		Map<String, Object> map = new HashMap<>();
		map.put("smith", "smith");
		map.put("level", 7);
		map.put("birth", java.sql.Date.valueOf("2000-01-21"));
		map.put("married", true);
		return map; // 리스폰스바디 > 바로 웹에 제이슨문으로 출력
	}

	// View 연결
	@GetMapping("/view/test")
	public String viewTest() {
		return "index";
	}

}

/*
 * @GetMapping("/gugu") //요청 //응답 public String gugu(HttpServletRequest request,
 * Model m) { int dan = Integer.parseInt(request.getParameter("dan"));
 * GuguService gs = new GuguService(); String gStr = gs.getGugu(dan);
 * m.addAttribute("gugu", gStr); return "gugu"; // 리퀘스트 영역에 저장하기 }
 */