package com.jt.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.filter.AutoLoad;
import com.jt.manage.mappper.UserMapper;
import com.jt.manage.pojo.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired		//依赖注入
	private UserMapper userMapper;

	@Override
	public List<User> findAll() {
		
		return userMapper.findAll();
	}
	
	
}
