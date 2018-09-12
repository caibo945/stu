package com.jt.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	 @RequestMapping("/index")
	 public String index(){
		 return "index";
	 }
	 /**
	  * get请求
	  * 	localhost:8091/addUser?id=1&name=tom
	  * restFul
	  * 	localhost:8091/addUser/1/tom
	  * 
	  * 参数格式要求 使用{}包裹参数
	  * 使用@PathVariable 动态获取参数
	  * @return url:/page/item-add
	  */
	 @RequestMapping("/page/{moduleName}")
	 public String toCreate(@PathVariable String moduleName){
		 return moduleName;
	 }

}
