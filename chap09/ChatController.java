package com.tjoeun.ws;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {
	
	@GetMapping("/chat")
	public String chat(HttpSession session) {
		session.setAttribute("uid", "Smith");
		return "ws/chat";
	}
	
}


/*
@GetMapping("/chat")
public String chat(Locale locale, Model model) {
	return "ws/chat";
}
*/