package com.tjoeun.svc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class GuguService {
	private HttpServletRequest request;

	@Autowired // 자동으로 생성자만들어줌
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	//========================================================================
	
	public String getGugu() 
	{
		String sDan = request.getParameter("dan");
		
		if(sDan==null || sDan.equals("")) {
			sDan = "2";
		}
		int dan = Integer.parseInt(sDan);
		return getGugu(dan);
	}
	
	public String getGugu(int dan) {
		String gStr = "";
		for(int i=1; i<=9; i++) {
			gStr += String.format("%d * %d = %d <br>", dan, i, dan*i);
		}
		return gStr;
		
	}
	
/*
	public String getGugu(int dan) {
		String strGugu = "";
		for(int i=1; i<=9; i++) {                // br 줄바꿈
			strGugu += String.format("%d * %d = %d <br>", dan, i, dan*i);
		}        // 누적해서 값을 낸다
		
		return strGugu;
	}
*/
	
}
