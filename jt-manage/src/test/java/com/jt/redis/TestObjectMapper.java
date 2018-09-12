package com.jt.redis;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.manage.pojo.User;

public class TestObjectMapper {

	//将对象转化为JSON
	@Test
	public void test01(){
		ObjectMapper objectMapper = new ObjectMapper();
		User user = new User();
		user.setId(1);
		user.setName("tomcat");
		user.setAge(18);
		user.setSex("女");
		try {
			String json=
			objectMapper.writeValueAsString(user);
			System.out.println(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		
	}
}
