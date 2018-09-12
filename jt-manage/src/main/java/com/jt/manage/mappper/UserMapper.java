package com.jt.manage.mappper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.jt.manage.pojo.User;

public interface UserMapper {
	
	@Select(value="select * from user")
	List<User> findAll();

}
