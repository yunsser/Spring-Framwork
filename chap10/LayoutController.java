package com.tjoeun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
//@RestController // 바로 브라우저로 나감
@RequestMapping("/layout")
public class LayoutController {
	
	@GetMapping("/1")
	public String layout01() {
		return "layout/1";
	}
	
	@GetMapping("/float01")
	public String layout02() {
		return "layout/float01";
	}
	
	@GetMapping("/display_table")
	public String display_table() {
		return "layout/display_table";
	}
	
	@GetMapping("/flex")
	public String flex() {
		return "layout/flex";
	}
}
