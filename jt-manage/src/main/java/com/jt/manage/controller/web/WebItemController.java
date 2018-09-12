package com.jt.manage.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;
import com.jt.manage.service.ItemService;

@Controller
@RequestMapping("/web/item")
public class WebItemController {
	
	@Autowired
	private ItemService itemService;
	//http://manage.jt.com/web/item/findItemById
	@RequestMapping("/findItemById")
	@ResponseBody
	public Item findItemById(Long itemId){//springmvc会将字符串自动转化成Long类型
		return itemService.findItemById(itemId);
	}
	@RequestMapping("/findItemDescById")
	@ResponseBody
	public ItemDesc findItemDescById(Long itemId){//springmvc会将字符串自动转化成Long类型
		
		return itemService.findItemDescById(itemId);
	}
}
