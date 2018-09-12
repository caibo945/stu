package com.jt.manage.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.manage.pojo.User;

@Controller
public class JSONPController {
	//http://manage.jt.com/web/testJSONP
	@RequestMapping(value="/web/testJSONP",produces="html/text;charset=utf-8")
	@ResponseBody
	public String jsonp(String callback){
		User user= new User();
		user.setId(100);
		user.setName("tomcat猫");
		user.setAge(18);
		ObjectMapper objectMapper = new ObjectMapper();
		String json=null;
		try {
			json = objectMapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return callback+"("+json+")";
	}
}
