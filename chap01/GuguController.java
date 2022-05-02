package com.tjoeun.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tjoeun.svc.GuguService;

@Controller
@RequestMapping("/gugu")
public class GuguController 
{
	@Autowired
	private GuguService svc;
	
	@GetMapping("") 
	public String getGugu(Model m) 
	{
		m.addAttribute("gugu", svc.getGugu());
		
		return "gugu"; 
	}
	
	@GetMapping("/{dan}") /*    /gugu/5    */
	public ModelAndView getGugu2(@PathVariable("dan") int dan) 
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject("gugu", svc.getGugu(dan));
		mv.setViewName("gugu");
		return mv;
	}
	

	
}
